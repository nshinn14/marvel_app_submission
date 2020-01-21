package com.nshinn.marvellimited.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import com.nshinn.marvellimited.persistence.model.Story

class StoryTest {
    private lateinit var story: Story

    @Before
    fun setUp() {
        story = Story(1, "uid", "Spiderman's Reveal")
    }

    @Test
    fun testStory() {
        Assert.assertEquals(1, story.id)
        Assert.assertEquals("uid", story.uid)
        Assert.assertEquals("Spiderman's Reveal", story.title)
    }
}