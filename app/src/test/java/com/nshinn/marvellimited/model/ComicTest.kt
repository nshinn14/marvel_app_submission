package com.nshinn.marvellimited.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import com.nshinn.marvellimited.persistence.model.Comic
import java.util.*

class ComicTest {
    private lateinit var comic: Comic
    private var modifiedAt = Calendar.getInstance()

    @Before
    fun setUp() {
        comic = Comic(1, "The Amazing SpiderMan", "Comic book description", modifiedAt.toString(), "thumbnail")
    }

    @Test
    fun testComic() {
        Assert.assertEquals(1, comic.id)
        Assert.assertEquals("The Amazing SpiderMan", comic.title)
        Assert.assertEquals("Comic book description", comic.description)
        Assert.assertEquals(modifiedAt.toString(), comic.modified)
        Assert.assertEquals("thumbnail", comic.thumbnail)
    }
}