import com.jacob.core_lib.core.Core
import com.jacob.core_lib.core.Program
import com.jacob.core_lib.parser.Parser
import java.io.File

fun main() {
    val file = File("./InputFile.txt")
    val instructionsFromFile = Parser.parseInstructionsFromFile(file)

    val core = Core(program = Program(instructionsFromFile))
    core.runProgram()
    println(core.registerArray)
    println(core.memoryArray)
}
