package ie.wit.main

import android.app.Application
import android.util.Log
import ie.wit.models.BugTrackingJSONStore
import ie.wit.models.BugTrackingMemStore
import ie.wit.models.BugTrackingStore


class BugTrackingApp : Application() {

    lateinit var bugs: BugTrackingStore


    override fun onCreate() {
        super.onCreate()
        bugs = BugTrackingJSONStore(applicationContext)
        Log.v("BugTracking","Bug Tracking App started")
    }
}