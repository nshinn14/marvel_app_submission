package com.nshinn.marvellimited.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import com.nshinn.marvellimited.persistence.model.Event

class EventTest {
    private lateinit var event: Event

    @Before
    fun setUp() {
        event = Event(1, "uid", "Big Awesome Event")
    }

    @Test
    fun testEvent() {
        Assert.assertEquals(1, event.id)
        Assert.assertEquals("uid", event.uid)
        Assert.assertEquals("Big Awesome Event", event.title)
    }
}