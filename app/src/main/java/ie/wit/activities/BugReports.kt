package ie.wit.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.Adapters.BugTrackingAdapter
import ie.wit.R
import ie.wit.main.BugTrackingApp
import kotlinx.android.synthetic.main.activity_bug_reports.*

class BugReports : AppCompatActivity() {
    lateinit var app: BugTrackingApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bug_reports)

        app = this.application as BugTrackingApp
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.adapter = BugTrackingAdapter(app.bugTrackingsStore.findAll())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_bugtrackingreport, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_bugForms -> { startActivity(Intent(this, BugTrackingActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
