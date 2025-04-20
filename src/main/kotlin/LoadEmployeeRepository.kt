import java.nio.file.Paths

class LoadEmployeeRepository {
    private val loadFile = Paths.get("employees.dat").toFile()

    fun loadAll(): List<Employee> =
        loadFile
            .takeIf { it.exists() }
            ?.readLines()
            ?.map {
                val parts = it.split("|")
                Employee(parts[0], parts[1])
            }
            ?: emptyList()
}
