package tw.idv.jew.coroutinesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            updateWithDelay()
        }
        updateWithoutDelay()

        //lifecycleScope
        lifecycleScope.launch {
            //Call suspend function here
        }
    }

    suspend fun updateWithDelay(){
        delay(5000)
        update()
        println("updateWithDelay")
    }

    private fun update() {

    }

    private fun updateWithoutDelay() {
        update()
        println("updateWithoutDelay")
    }
}