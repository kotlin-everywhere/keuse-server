package com.minek.kotlin.everywhere

import com.minek.kotlin.everywhere.keuse.Crate
import com.minek.kotlin.everywehre.keuson.convert.Converters.int
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class testCreate {
    @Test
    fun testFindBox() {
        val crate = object : Crate() {
            val echo by e(int, int)
            val sub by c {
                object : Crate() {
                    @Suppress("unused")
                    val doubleIt by e(int, int)
                }
            }

            init {
                echo { it }
                sub.doubleIt { it + it }
            }
        }

        // Test NotFound
        assertEquals(crate.echo, crate.findBox("echo"))
        assertNull(crate.findBox("notExist"))

        // Test Sub
        // Test SubNotFound
        assertEquals(crate.sub.doubleIt, crate.findBox("sub/doubleIt"))
        assertNull(crate.findBox("sub/notExist"))
    }
}


