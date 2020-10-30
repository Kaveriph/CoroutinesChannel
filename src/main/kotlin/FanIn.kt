import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext

fun main() {
    runBlocking {
        /*launch { while(true) {delay(200L)
            channel.send("Hey")} }
        launch { while(true) {delay(500)
            channel.send("Hello") }}
        repeat(6) {
            println(channel.receive())
        }
        kotlin.coroutines.coroutineContext.cancelChildren()*/
        launch{
            sendString(channel, 200, "Hey")
        }
        launch {
            sendString(channel, 500 , "Hello")
        }
        repeat(6) {
            println(channel.receive())
        }
        coroutineContext.cancelChildren()
    }

}

suspend fun sendString(channel:SendChannel<String>, time:Long, msg:String) {
    while(true) {
        delay(time)
        channel.send(msg)
    }
}

val channel = Channel<String>()
