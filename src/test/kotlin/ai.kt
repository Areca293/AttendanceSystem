import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

// 工具类
object DateUtils {
    // 中文星期映射
    private val chineseWeekDays =
        mapOf(
            DayOfWeek.MONDAY to "星期一",
            DayOfWeek.TUESDAY to "星期二",
            DayOfWeek.WEDNESDAY to "星期三",
            DayOfWeek.THURSDAY to "星期四",
            DayOfWeek.FRIDAY to "星期五",
            DayOfWeek.SATURDAY to "星期六",
            DayOfWeek.SUNDAY to "星期日",
        )

    // 获取当前日期
    fun currentDate(): LocalDate = LocalDate.now()

    // 获取当前星期（中文）
    fun currentChineseWeek(): String = chineseWeekDays[currentDate().dayOfWeek] ?: "未知"

    // 获取本周日期范围
    fun currentWeekRange(): Pair<LocalDate, LocalDate> {
        val monday = currentDate().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        val sunday = monday.plusDays(6)
        return monday to sunday
    }

    // 带格式的日期字符串
    fun formattedDate(date: LocalDate = currentDate()): String = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    // 带中文的日期显示
    fun formattedDateWithChinese(date: LocalDate = currentDate()): String = "${formattedDate(date)} ${chineseWeekDays[date.dayOfWeek]}"

    // 判断是否在当周
    fun isInCurrentWeek(targetDate: LocalDate): Boolean {
        val (start, end) = currentWeekRange()
        return !targetDate.isBefore(start) && !targetDate.isAfter(end)
    }
}

// 使用示例
fun main() {
    // 获取当前日期
    val today = DateUtils.currentDate()
    println("当前日期: ${DateUtils.formattedDate(today)}")

    // 获取当前星期
    println("当前星期: ${DateUtils.currentChineseWeek()}")

    // 获取本周范围
    val (monday, sunday) = DateUtils.currentWeekRange()
    println("本周范围: ${monday.format(DateTimeFormatter.ISO_DATE)} 至 ${sunday.format(DateTimeFormatter.ISO_DATE)}")

    // 带中文的日期格式
    println("中文格式: ${DateUtils.formattedDateWithChinese()}")

    // 判断日期是否在本周
    val testDate = LocalDate.of(2023, 10, 9)
    println("$testDate 是否在本周: ${DateUtils.isInCurrentWeek(testDate)}")
}
