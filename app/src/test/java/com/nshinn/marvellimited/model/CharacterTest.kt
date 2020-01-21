package com.nshinn.marvellimited.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import com.nshinn.marvellimited.persistence.model.Character

class CharacterTest {
    private lateinit var character: Character

    @Before
    fun setUp() {
        character = Character(1, "uid", "Spiderman")
    }

    @Test
    fun testCharacter() {
        Assert.assertEquals(1, character.id)
        Assert.assertEquals("uid", character.uid)
        Assert.assertEquals("Spiderman", character.name)
    }
}