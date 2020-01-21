package com.nshinn.marvellimited.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import com.nshinn.marvellimited.persistence.model.Series

class SeriesTest {
    private lateinit var series: Series

    @Before
    fun setUp() {
        series = Series(1, "uid", "The Avengers: Endgame")
    }

    @Test
    fun testSeries() {
        Assert.assertEquals(1, series.id)
        Assert.assertEquals("uid", series.uid)
        Assert.assertEquals("The Avengers: Endgame", series.title)
    }
}