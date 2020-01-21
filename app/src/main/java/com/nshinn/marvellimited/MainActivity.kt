package com.nshinn.marvellimited

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator

import kotlinx.android.synthetic.main.main_activity.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nshinn.marvellimited.persistence.model.Comic
import com.nshinn.marvellimited.persistence.repository.ComicRepository
import com.nshinn.marvellimited.view.MarvelViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe
import com.nshinn.marvellimited.eventbus.ComicUpdateCompleteEvent
import com.nshinn.marvellimited.persistence.database.UniverseDatabase

class MainActivity : AppCompatActivity() {
    private val LOG_TAG = "nicc"

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<*>? = null
    private var data: ArrayList<Comic> = ArrayList()

    private lateinit var marvelViewModel: MarvelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        layoutManager = LinearLayoutManager(this)
        marvelViewModel = ViewModelProviders.of(this).get(MarvelViewModel::class.java)
        adapter = ComicListAdapter(data)

        comic_list_view.setHasFixedSize(true)
        comic_list_view.layoutManager = layoutManager
        comic_list_view.itemAnimator = DefaultItemAnimator()
        comic_list_view.adapter = adapter

        queryComicData()

        // Swipe to refresh control
        swipe_refresh.setOnRefreshListener {
            Log.i(LOG_TAG, "Refresh task started from swipe")
            updateComicList()
        }

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    // Options Menu

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Switch statement to add more menu items
        when (item.itemId) {
            R.id.refresh -> {
                Log.i(LOG_TAG, "Refresh task started from menu")
                swipe_refresh.isRefreshing = true
                updateComicList()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateComicList() {
        marvelViewModel.fetchComics()
        marvelViewModel.marvelComics.observe(this, Observer {
            // Insert fetched comics into the Universe DB
            scope.runOnIOThread {
                UniverseDatabase.getInstance(applicationContext).clearAllTables()
                val comicRepo = ComicRepository.createRepository(application)
                it?.forEach {comic ->
                    val thumbnailUrl = (comic.thumbnail.path + "/standard_amazing." + comic.thumbnail.extension).replace("http", "https")
                    comicRepo.insert(Comic(comic.id, comic.title, comic.description, comic.modified, thumbnailUrl))
                }
            } deferOnUIThread { unit, e ->
                if (e != null)
                    Log.d(LOG_TAG, "Fetch comic task failed")
                else
                    queryComicData()
            }
        })
    }

    private fun queryComicData() {
        // Update current adapter
        data.clear()
        scope.runOnIOThread {
            val comicRepo = ComicRepository.createRepository(application)
            comicRepo.getComics()
        } deferOnUIThread { comics, e ->
            if (e != null || comics.isNullOrEmpty()) {
                Log.d(LOG_TAG, "Query for comics task failed")
            } else {
                data.addAll(comics)
                EventBus.getDefault().post(ComicUpdateCompleteEvent())
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ComicUpdateCompleteEvent) {
        completeListRefresh()
    }

    private fun completeListRefresh() {
        swipe_refresh.isRefreshing = false
        swipe_to_refresh_message.visibility = if (data.isEmpty()) View.VISIBLE else View.GONE

        comic_list_view.scheduleLayoutAnimation()
        adapter?.notifyDataSetChanged()
    }

}