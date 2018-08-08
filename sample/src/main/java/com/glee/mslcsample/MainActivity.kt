package com.glee.mslcsample


import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.INVISIBLE
import com.glee.mslc.MultiStateLayoutController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val layoutController by lazy {
        MultiStateLayoutController.set(content)
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
                val nanoTime = System.nanoTime()

                layoutController.showContent()
                layoutController.addView(null, null)
                layoutController.requestFitSystemWindows()
                layoutController.requestLayout()
                Log.d("glee9507", (System.nanoTime() - nanoTime).toString())
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
