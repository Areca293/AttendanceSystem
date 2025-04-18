import java.time.LocalDate
import kotlin.system.exitProcess

class System {
    private val loadEmployee = LoadEmployeeRepository()
    private val show = ShowMenu()
    private val input = InputHandler()

    fun start() {
        show.showWelcome()

        while (true) {
            val employees =
                loadEmployee.loadAll().takeIf { it.isNotEmpty() }
                    ?: run {
                        println("员工数据未加载")
                        exitProcess(1)
                    }
            show.showEmployee(employees)
            val selected = input.selectEmployee(employees)
            selected?.let { employee ->
                showMainMenu(employee)
            }
        }
    }

    private fun showMainMenu(employee: Employee) {
        println("\n=== 当前用户: ${employee.name} ===")
        println("1. 上班")
        println("2. 补卡")
        println("3. 查看本周每天打卡时间")
        println("0. 切换用户")
        print("请选择操作: ")

        when (readlnOrNull()) {
            "1" -> todayRecord(employee)
            "2" -> supRecord(employee)
            "3" -> showRecord(employee)
            "0" -> return
            else -> {
                println("无效输入")
                return showMainMenu(employee)
            }
        }
    }

    private fun todayRecord(employee: Employee?): Employee? {
        println(
            """
            请输入工作时间段：
            格式：MON 13-21
            """.trimIndent(),
        )
        // TODO 加时间
        val today = LocalDate.now()
        val input = readlnOrNull()?.trim()
        when {
            input.isNullOrBlank() -> {
                println("输入不能为空")
                return todayRecord(employee)
            }
            !input.matches(Regex("""^(MON|TUE|WED|THU|FRI|SAT|SUN)\s([01]\d|2[0-3])-([01]\d|2[0-3])${'$'}""")) -> {
                println("格式不正确")
                return todayRecord(employee)
            }
            else -> {
                employee?.workPeriod = input
                return employee?.apply {
                    println("$name 打卡成功")
                }
            }
        }
    }

    private fun supRecord(employee: Employee) {
    }

    private fun showRecord(employee: Employee) {
    }
}
