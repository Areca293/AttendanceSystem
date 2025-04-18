class ShowMenu {
    fun showEmployee(employees: List<Employee>) {
        println(
            """
            /---------------------\
            |     员工列表 (共${employees.size}人）  |
            +----+----------------+
            | ID |     姓名        |
            +----+----------------+
            """.trimIndent(),
        )
        employees.forEach {
            println("|${it.id.padEnd(4)}|${it.name.padEnd(14)}|")
        }
        println("+----+----------------+")
    }

    fun showWelcome() {
        println("\u001B[33m")
        println(
            """
            *********************************
            *   问鹄考勤管理系统 - 员工登录     *
            *********************************
            """.trimIndent(),
        )
        println("\u001B[0m")
    }
}
