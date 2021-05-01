package tw.idv.jew.coroutinesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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

        //Flow
        fun demoFlow(): Flow<Int> = flow {
            for (i in 1..3){
                delay(100)
                emit(i) //emit new value
            }
        }.flowOn(Dispatchers.Default)
        runBlocking {
            //Collect the flow inside coroutine
            demoFlow().collect { println(it) }
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

    //Dispatchers
    suspend fun getResultSuspend(){
        //api call here
        val api = Api()
        val result = api.getData("para1", "para2")
        //update UI here
        updateUI(result)
    }
    suspend fun Api.getData(para1: String, para2: String) =
        withContext(Dispatchers.IO){
            delay(500)
            return@withContext listOf<String>()
        }
    suspend fun updateUI(list: List<String>) =
        withContext(Dispatchers.Main){
            //update view here
        }
}