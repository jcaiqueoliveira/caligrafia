package com.github.kanda.fonts.compose

import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class CommonGreetingTest {

    @Test
    fun testExample() {
        assertTrue(Greeting().greet().contains("Hello"), "Check 'Hello' is mentioned")
    }

    @Test
    fun testExample2() = runBlocking {
        CaligrafiaInternals.init("AIzaSyBPVKAyPVja9SvXcen4CvPrlq-CnCZ_Fwk")
    }
}