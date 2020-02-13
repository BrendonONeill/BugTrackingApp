package ie.wit.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.Adapters.BugTrackingAdapter
import ie.wit.Adapters.DeleteListener
import ie.wit.Adapters.EditListener
import ie.wit.R
import ie.wit.main.BugTrackingApp
import ie.wit.models.BugTrackingModel
import kotlinx.android.synthetic.main.activity_bug_reports.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult

class BugReports : AppCompatActivity(), EditListener, DeleteListener {
    lateinit var app: BugTrackingApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bug_reports)

        app = this.application as BugTrackingApp
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.adapter = BugTrackingAdapter(app.bugs.findAll(),this,this)
        loadBugTrackings()
    }

    private fun loadBugTrackings() {
        showBugTracking(app.bugs.findAll())
    }

    fun showBugTracking (bugs: List<BugTrackingModel>) {
        recyclerView.adapter = BugTrackingAdapter(bugs, this, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_bugtrackingreport, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<BugTrackingActivity>(0)

           
        }

        return super.onOptionsItemSelected(item)
    }




    override fun onEditClick(bugTracking: BugTrackingModel) {
        startActivityForResult(intentFor<BugTrackingActivity>().putExtra("edit", bugTracking), 0)

    }

    override fun onDeleteClick(bugTracking: BugTrackingModel) {
        app.bugs.delete(bugTracking.copy())
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadBugTrackings()
        super.onActivityResult(requestCode, resultCode, data)
    }
}
