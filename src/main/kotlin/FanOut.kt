import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val numbers = produceNumbers()
        repeat(5) { launchProcessor(it, numbers)}
        delay(500L)
        numbers.cancel()
    }
}

private fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1
    while(true) {
        send(x++)
        delay(100L)
    }
}

fun CoroutineScope.launchProcessor(id:Int, channel: ReceiveChannel<Int>) = launch {
    for (message in channel)
        println("Processor #$id receive $message")
}