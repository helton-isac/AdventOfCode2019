package com.hitg.adventofcode.repository

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class TxtRepo {

    companion object {
        fun readTextFile(txtInputStream: InputStream): String {
            val bufferedReader = BufferedReader(InputStreamReader(txtInputStream))
            return bufferedReader.use { it.readText() }
        }
    }
}