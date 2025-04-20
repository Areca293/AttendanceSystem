fun recordToday(employee: Employee?): Employee? {
    println(
        """
        请输入工作时间段：
        格式：MON 13-21
        """.trimIndent(),
    )

    val input = readlnOrNull()?.trim()
    when {
        input.isNullOrBlank() -> {
            println("输入不能为空")
            return recordToday(employee)
        }
        !input.matches(Regex("""^(MON|TUE|WED|THU|FRI|SAT|SUN)\s([01]\d|2[0-3])-([01]\d|2[0-3])${'$'}""")) -> {
            println("格式不正确")
            return recordToday(employee)
        }
        else -> {
            employee?.workPeriod = input
            return employee?.apply {
                println("$name 打卡成功")
            }
        }
    }
}
