package com.jacob.core_lib.parser

import org.junit.jupiter.api.Test
import java.io.File

internal class ParserTest {
    @Test
    internal fun name() {
        val filePath = File("src/test/resources/InputFile.txt").toURI()
        println(filePath)
//        Parser.parseInstructionsFromFile(filePath)
    }
}
