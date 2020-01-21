package com.nshinn.marvellimited

import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nshinn.marvellimited.persistence.model.Comic
import android.widget.ImageView
import com.squareup.picasso.Picasso

class ComicListAdapter(private val dataSet: ArrayList<Comic>) :
    RecyclerView.Adapter<ComicListAdapter.ComicViewHolder>() {

    class ComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var comicCardTitle: TextView
        internal var comicCardSubtitle: TextView
        internal var comicCardImage: ImageView

        init {
            this.comicCardTitle = itemView.findViewById(R.id.comic_card_title)
            this.comicCardSubtitle = itemView.findViewById(R.id.comic_card_subtitle)
            this.comicCardImage = itemView.findViewById(R.id.comic_card_image)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comic_card, parent, false)

        return ComicViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, listPosition: Int) {

        val comicCardTitle = holder.comicCardTitle
        val comicCardSubtitle = holder.comicCardSubtitle
        val comicCardImage = holder.comicCardImage

        comicCardTitle.text = dataSet[listPosition].title
        comicCardSubtitle.text = dataSet[listPosition].description
        Picasso.get().load(dataSet[listPosition].thumbnail).into(comicCardImage)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}