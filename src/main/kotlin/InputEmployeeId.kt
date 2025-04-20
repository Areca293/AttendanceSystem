import kotlin.system.exitProcess

class InputEmployeeId {
    fun selectEmployee(employee: List<Employee>): Employee? {
        println("请输入员工ID（Q退出）")
        val input = readlnOrNull()?.trim()?.uppercase()

        when {
            input == "Q" -> exitProcess(0)
            input.isNullOrBlank() -> {
                println("输入不能为空")
                return null
            }
            !input.matches(Regex("\\d+")) -> {
                println("请输入数字ID")
                return null
            }
            else -> {
                return employee
                    .find { it.id == input }
                    ?.apply { println("已选择 $id $name") }
                    ?: run {
                        println("未找到该员工")
                        null
                    }
            }
        }
    }
}
