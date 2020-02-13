package ie.wit.models

import android.content.Context
import android.system.Os.read
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import java.nio.file.Files.exists
import java.nio.file.Files.write
import java.util.*
import kotlin.collections.ArrayList

import android.util.Log
import ie.wit.helpers.exists
import ie.wit.helpers.read
import ie.wit.helpers.write


val JSON_FILE = "bugs.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<BugTrackingModel>>() {}.type



fun generateRandomId(): Long {
    return Random().nextLong()
}

class BugTrackingJSONStore : BugTrackingStore, AnkoLogger {

    val context: Context

    var bugTrackings = ArrayList<BugTrackingModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<BugTrackingModel> {
        return bugTrackings
    }

    override fun findById(id:Long) : BugTrackingModel? {
        val foundBugTracking: BugTrackingModel? = bugTrackings.find { it.id == id }
        return foundBugTracking
        serialize()
    }

    override fun create(bugTracking: BugTrackingModel) {
        bugTracking.id = getId()
        bugTrackings.add(bugTracking)
        logAll()
        serialize()
    }

    override fun update(bugTracking: BugTrackingModel) {
        var foundBug: BugTrackingModel? = bugTrackings.find { p -> p.id == bugTracking.id }
        if (foundBug != null) {
            foundBug.title = bugTracking.title
            foundBug.descriptions = bugTracking.descriptions
            foundBug.bugimportance= bugTracking.bugimportance


            logAll()
            serialize()
        }
    }

    override fun delete(bugTracking: BugTrackingModel) {
        bugTrackings.remove(bugTracking)
        logAll()
        serialize()
    }

    fun logAll() {
        Log.v("BugTracking","** Bug Tracking Report List **")
        bugTrackings.forEach { Log.v("BugTracking","${it}") }
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(bugTrackings, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        bugTrackings = Gson().fromJson(jsonString, listType)
    }
}