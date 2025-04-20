import kotlin.system.exitProcess

class System {
    private val loadEmployee = LoadEmployeeRepository()
    private val show = ShowMenu()
    private val input = InputEmployeeId()

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
            "1" -> Record().recordToday()
            "2" -> Record().supRecord()
            "3" -> Record().showRecord()
            "0" -> return
            else -> {
                println("无效输入")
                return showMainMenu(employee)
            }
        }
    }
}
