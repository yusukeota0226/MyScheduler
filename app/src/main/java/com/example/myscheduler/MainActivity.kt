package com.example.myscheduler

import ScheduleAdapter
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        realm = Realm.getDefaultInstance()
        val schedules = realm.where<Schedule>().findAll()
        listView.adapter = ScheduleAdapter(schedules)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            startActivity<ScheduleEditActivity>()
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        fab.setOnClickListener { view ->
            startActivity<ScheduleEditActivity>()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}