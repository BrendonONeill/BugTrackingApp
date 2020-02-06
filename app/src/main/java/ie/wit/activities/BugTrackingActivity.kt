package ie.wit.activities

import android.content.Intent
import android.icu.text.CaseMap
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

class BugTrackingActivity : AppCompatActivity() {
    lateinit var app: BugTrackingApp





    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        app = this.application as BugTrackingApp


        saveButton.setOnClickListener {

            val title = titlebug.text.toString()

            val bugNumber = if(BugRadio.checkedRadioButtonId == R.id.Bug1) "1" else "5"



            app.bugTrackingsStore.create(BugTrackingModel(title = title , bugimportance = bugNumber))



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

