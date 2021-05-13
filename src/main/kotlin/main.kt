import com.jacob.core_lib.core.Core
import com.jacob.core_lib.core.Program
import com.jacob.core_lib.parser.Parser
import com.jacob.core_lib.parser.data.toVariables
import java.io.File

fun main() {
    val file = File("./InputFile.txt")
    val dataFromFile = Parser.parseDataFromFile(file)
    val variables = dataFromFile.second
        .toVariables()

    val core = Core(program = Program(dataFromFile.first, variables))
    core.runProgram()

    println(core.registerArray)
    println(core.memoryArray)
}
