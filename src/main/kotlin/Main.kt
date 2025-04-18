import java.io.File

fun main() {
    File("employees.dat").writeText(
        """
        001|冉琦
        002|宋经纬
        003|许艾华
        004|田浩
        005|孙瑛峻
        006|屈建业
        007|侯晓宇
        008|葛江成
        """.trimIndent(),
    )
    val host = "localhost"
    val port = 8080
    val message = "Hello, Server!"
//    val func= ServerApi::sendRequest
//    val response = func(ServerApi(),host, port, message)

    val response = ServerApi().sendRequest(host, port, message)
    println("Server response: $response")
    System().start()
}
