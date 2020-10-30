import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val channel = produce<Int> {
            for(x in 1..5) {
                send(x*x)
            }
        }
        //or
        for(y in channel) {
            println(y)
        }
        val channel2 = produceSquares().consumeEach { println(it) }
    }
}

fun CoroutineScope.produceSquares() = produce<Int> {
    for(x in 1..10) {
        send(x*x)
    }
}