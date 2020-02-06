package ie.wit.main

import android.app.Application
import android.util.Log
import ie.wit.models.BugTrackingMemStore
import ie.wit.models.BugTrackingStore


class BugTrackingApp : Application() {

    lateinit var bugTrackingsStore: BugTrackingStore


    override fun onCreate() {
        super.onCreate()
        bugTrackingsStore = BugTrackingMemStore()
        Log.v("BugTracking","Bug Tracking App started")
    }
}