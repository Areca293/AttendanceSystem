import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class ServerApi {
    fun sendRequest(
        host: String,
        port: Int,
        message: String,
    ): String {
        Socket(host, port).use { socket ->
            PrintWriter(socket.outputStream, true).use { writer ->
                BufferedReader(InputStreamReader(socket.inputStream)).use { reader ->
                    // 发送消息
                    writer.println(message)

                    // 读取响应
                    return reader.readLine()
                }
            }
        }
    }
}
