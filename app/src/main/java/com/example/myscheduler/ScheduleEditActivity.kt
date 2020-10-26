package com.example.myscheduler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_schedule_edit.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton
import java.lang.IllegalArgumentException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ScheduleEditActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        save.setOnClickListener {
//            setContentView(R.layout.activity_schedule_edit)
            realm.executeTransaction {
                val maxId = realm.where<Schedule>().max("id")
                val nextId = (maxId?.toLong() ?: 0L) + 1
                val schedule = realm.createObject<Schedule>(nextId)
                dateEdit.text.toString().toDate("yyyy/MM/dd")?.let{
                    schedule.date = it
                }
                schedule.title = titleEdit.text.toString()
                schedule.detail = detailEdit.text.toString()
            }
            alert("追加しました") {
                yesButton { finish() }
            }.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun String.toDate(pattern: String = "yyyy/MM/dd HH:mm"): Date? {
        val sdFormat = try{
            SimpleDateFormat(pattern)
        } catch (e: IllegalArgumentException){
            null
        }
        val date = sdFormat?.let {
            try {
                it.parse(this)
            }catch (e:ParseException){
                null
            }
        }
        return date
    }
}