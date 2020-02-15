package ie.wit.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import ie.wit.helpers.exists

import ie.wit.helpers.read
import ie.wit.helpers.write



import java.util.*



val JSON_FILE = "bugTrackings.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<BugTrackingModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class BugJsonStore : BugTrackingStore {

    var bugTrackings = mutableListOf<BugTrackingModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<BugTrackingModel> {
        return bugTrackings
    }

    override fun findById(id: Long): BugTrackingModel? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun create(placemark: BugTrackingModel) {
        placemark.id = generateRandomId()
        bugTrackings.add(placemark)
        serialize()
    }

    override fun update(bugTracking: BugTrackingModel) {
        var foundBug: BugTrackingModel? = bugTrackings.find { p -> p.id == bugTracking.id }
        if (foundBug != null) {
            foundBug.title = bugTracking.title

            foundBug.bugimportance= bugTracking.bugimportance



        }
        serialize()
    }

    override fun delete(bugTracking: BugTrackingModel) {
        bugTrackings.remove(bugTracking)

        serialize()
    }



    private fun serialize() {
        val jsonString = gsonBuilder.toJson(bugTrackings, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        bugTrackings = Gson().fromJson(jsonString, listType)
    }
}