package ie.wit.activities

import android.content.Intent

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import ie.wit.R
import ie.wit.main.BugTrackingApp
import ie.wit.models.BugTrackingModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_bug.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main.view.*

class BugTrackingActivity : AppCompatActivity() {

    var bug = BugTrackingModel()
    var edit = false
    lateinit var app: BugTrackingApp

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        app = this.application as BugTrackingApp





        if (intent.hasExtra("edit")) {
            edit = true
            bug = intent.extras?.getParcelable<BugTrackingModel>("edit")!!
            titlebug.setText(bug.title)
            DescriptionBox.setText(bug.descriptions)


            saveButton.setText(R.string.save_bug)
        }

        saveButton.setOnClickListener {

            val title = titlebug.text.toString()
            val description = DescriptionBox.text.toString()

            val bugNumber = if (BugRadio.checkedRadioButtonId == R.id.Bug1) "1"
            else if (BugRadio.checkedRadioButtonId == R.id.Bug2) "2"
            else if (BugRadio.checkedRadioButtonId == R.id.Bug3) "3"
            else if (BugRadio.checkedRadioButtonId == R.id.Bug4) "4"
            else if (BugRadio.checkedRadioButtonId == R.id.Bug5) "5" else "0"

            app.bugs.create(BugTrackingModel(title = title, descriptions = description, bugimportance = bugNumber))
            setResult(AppCompatActivity.RESULT_OK)
            finish()

        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_bugtrackingforms, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_bugReport -> { startActivity(Intent(this, BugReports::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

