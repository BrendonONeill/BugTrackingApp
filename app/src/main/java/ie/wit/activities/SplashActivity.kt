package ie.wit.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import ie.wit.R



class SplashActivity : AppCompatActivity() {
    private val splash:Long=4000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(this,BugReports::class.java))
            finish()
        }, splash)
    }
}