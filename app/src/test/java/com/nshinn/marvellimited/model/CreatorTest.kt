package com.nshinn.marvellimited.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import com.nshinn.marvellimited.persistence.model.Creator

class CreatorTest {
    private lateinit var creator: Creator

    @Before
    fun setUp() {
        creator = Creator(1, "uid", "Stan Lee")
    }

    @Test
    fun testCreator() {
        Assert.assertEquals(1, creator.id)
        Assert.assertEquals("uid", creator.uid)
        Assert.assertEquals("Stan Lee", creator.fullName)
    }
}