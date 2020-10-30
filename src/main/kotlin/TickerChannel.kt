import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val tickerChannel = ticker(100, 0)
        launch {
            val startTime = System.currentTimeMillis()
            tickerChannel.consumeEach {
                println("Received @ ${System.currentTimeMillis() - startTime}")
            }

        }
        delay(1000L)
        println("Stopping!")
        coroutineContext.cancelChildren()
    }
}