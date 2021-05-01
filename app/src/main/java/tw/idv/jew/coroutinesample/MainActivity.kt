package tw.idv.jew.coroutinesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

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

        //Cancel
        val job = GlobalScope.launch {
            updateWithDelay()
        }
        //Call to stop the Coroutine
        job.cancel()
        //用isActive檢查Coroutine是否停止
        GlobalScope.launch {
            if (isActive){
                someCalculation()
            }
        }
        //用try/catch避免Coroutine停止造成Exception
        GlobalScope.launch {
            try {
                someCalculation()
            }finally {
                //release your resource here
            }
        }

        //CoroutineScope Builder
        //Launch
        GlobalScope.launch { delay(500) }
        //Async
        val deferred = GlobalScope.async {
            delay(500)
            return@async listOf<String>()
        }
//        val list = deferred.await()   //Should inside suspend function
        //WithContext本身是suspend function，需放在Coroutine裡面
        /*val list = withContext(Dispatchers.IO){
            delay(500)
            return@withContext listOf<String>()
        }*/
        //coroutineScope本身是suspend function，需放在Coroutine裡面
        /*val list = coroutineScope {
            delay(500)
            return@coroutineScope listOf<String>()
        }*/
        //RunBlocking
        fun getList(): List<String> = runBlocking {
            delay(500)
            return@runBlocking listOf<String>()
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

    private fun someCalculation() {
        println("someCalculation")
    }
}