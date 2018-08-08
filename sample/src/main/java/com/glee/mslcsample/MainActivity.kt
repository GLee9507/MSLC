package com.glee.mslcsample


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.glee.mslc.MultiStateLayoutController

class MainActivity : AppCompatActivity() {
    val layoutController by lazy {
        MultiStateLayoutController.setupToViewGroup(findViewById(R.id.content))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item == null) {
            return false
        }

        when (item.itemId) {
            R.id.one -> {
            }
            R.id.two -> {
            }
            R.id.three -> {
            }
            R.id.four -> {
            }

            else -> {
            }
        }



        return super.onOptionsItemSelected(item)
    }
}
