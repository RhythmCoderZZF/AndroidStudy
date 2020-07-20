package com.example.android_study._android._database._file

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_study.R
import kotlinx.android.synthetic.main.activity_file_ay.*
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder

class FileAy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_ay)
        load()
    }

    override fun onDestroy() {
        super.onDestroy()
        save(edit.text.toString())

    }

    private fun save(str: String) {
        val openFileOutput = openFileOutput("data", Context.MODE_PRIVATE)
        val bufferedWriter = BufferedWriter(OutputStreamWriter(openFileOutput))
        bufferedWriter.use { it.write(str) }
    }

    private fun load(){
        try {
            val openFileInput = openFileInput("data")
            val bufferedReader = BufferedReader(InputStreamReader(openFileInput))
            bufferedReader.use { it.forEachLine { edit.append(it) } }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}