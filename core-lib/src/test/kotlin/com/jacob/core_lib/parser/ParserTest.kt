package com.jacob.core_lib.parser

import com.jacob.core_lib.common.*
import com.jacob.core_lib.getFile
import com.jacob.core_lib.instructions.Branch
import com.jacob.core_lib.instructions.Label
import com.jacob.core_lib.instructions.OffsetTypes
import com.jacob.core_lib.instructions.add.Add
import com.jacob.core_lib.instructions.load.Load
import com.jacob.core_lib.instructions.move.Move
import com.jacob.core_lib.instructions.store.Store
import com.jacob.core_lib.instructions.sub.Sub
import com.jacob.core_lib.parser.data.ParsedData
import com.jacob.core_lib.parser.data.toVariables
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be in`
import org.junit.jupiter.api.Test

internal class ParserTest {
    @Test
    internal fun `test file 1`() {
        val file = getFile("/TestInputFile1.s")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` 4
        variables.size `should be equal to` 0

        Move.of(1.DR, 5.I) `should be in` instructions
        Add.of(3.DR, 1.SR, 2.SR) `should be in` instructions
        Sub.of(4.DR, 3.SR, 5.I)
    }

    @Test
    internal fun `test file 2`() {
        val file = getFile("/TestInputFile2.s")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` 3
        variables.size `should be equal to` 0

        Move.of(1.DR, 5.I) `should be in` instructions
        Add.of(3.DR, 1.SR, 2.I) `should be in` instructions
        Sub.of(4.DR, 1.SR, 3.I)
    }

    @Test
    internal fun `test file 3`() {
        val file = getFile("/TestInputFile3.s")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` 12
        variables.size `should be equal to` 0

        Move.of(1.DR, 5.I) `should be in` instructions
        Move.of(2.DR, 10.I) `should be in` instructions
        Branch("LABEL_1") `should be in` instructions
        Branch("LABEL_2") `should be in` instructions
        Label("LABEL_1") `should be in` instructions
        Label("LABEL_2") `should be in` instructions

        Add.of(3.DR, 1.SR, 2.I) `should be in` instructions
        Add.of(3.DR, 1.SR, 2.SR) `should be in` instructions
        Move.of(3.DR, 15.I) `should be in` instructions
        Move.of(4.DR, 1.I) `should be in` instructions
    }

    @Test
    internal fun `test file 4`() {
        val file = getFile("/TestInputFile4.s")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` 10
        variables.size `should be equal to` 2

        Move.of(1.DR, 5.I) `should be in` instructions
        Move.of(2.DR, 10.I) `should be in` instructions
        Load.of(3.DR, "A")
        Load.of(4.DR, "BCD")

        Load.of(5.DR, 3.SR)
        Load.of(6.DR, 4.SR)

        Add.of(7.DR, 1.SR, 5.SR) `should be in` instructions
        Sub.of(8.DR, 2.SR, 6.SR) `should be in` instructions

        Store.of(7.SR, 5.DR)
        Store.of(8.SR, "BCD")

        ParsedData("A", 10) `should be in` parseDataFromFile.second
        ParsedData("BCD", 15) `should be in` parseDataFromFile.second
    }

    @Test
    internal fun `test file 5`() {
        val file = getFile("/TestInputFile5.s")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` 16
        variables.size `should be equal to` 4

        Load.of(1.DR, "A") `should be in` instructions
        Load.of(2.DR, "B") `should be in` instructions
        Load.of(3.DR, "C") `should be in` instructions
        Load.of(4.DR, "D") `should be in` instructions

        Load.of(5.DR, 1.SR) `should be in` instructions
        Load.of(6.DR, 2.SR) `should be in` instructions
        Load.of(7.DR, 3.SR) `should be in` instructions
        Load.of(8.DR, 4.SR) `should be in` instructions

        Add.of(5.DR, 5.SR, 5.SR, 1.LS) `should be in` instructions
        Add.of(6.DR, 6.SR, 6.SR, 4.RS) `should be in` instructions
        Sub.of(7.DR, 7.SR, 7.SR, 1.LS) `should be in` instructions
        Sub.of(8.DR, 8.SR, 8.SR, 4.RS) `should be in` instructions.filterIsInstance<Sub>()

        Store.of(5.SR, 1.DR) `should be in` instructions
        Store.of(6.SR, 2.DR) `should be in` instructions
        Store.of(7.SR, 3.DR) `should be in` instructions
        Store.of(8.SR, 4.DR) `should be in` instructions

        ParsedData("A", 10) `should be in` parseDataFromFile.second
        ParsedData("B", 20) `should be in` parseDataFromFile.second
        ParsedData("C", 30) `should be in` parseDataFromFile.second
        ParsedData("D", 40) `should be in` parseDataFromFile.second
    }

    @Test
    internal fun `test file 6`() {
        val file = getFile("/TestInputFile6.s")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` 10
        variables.size `should be equal to` 2

        Move.of(0.DR, 3.I) `should be in` instructions
        Move.of(1.DR, (-8).I) `should be in` instructions
        Move.of(2.DR, 11.I) `should be in` instructions

        Load.of(3.DR, "A") `should be in` instructions
        Load.of(4.DR, "B") `should be in` instructions

        Load.of(5.DR, 3.SR, 1.I, OffsetTypes.IMMEDIATE) `should be in` instructions
        Load.of(6.DR, 4.SR, (-1).I, OffsetTypes.IMMEDIATE) `should be in` instructions
        Load.of(7.DR, 0.SR, (-3).I, OffsetTypes.PRE) `should be in` instructions
        Load.of(8.DR, 1.SR, 9.I, OffsetTypes.PRE) `should be in` instructions
        Load.of(9.DR, 2.SR, (-10).I, OffsetTypes.POST) `should be in` instructions

        ParsedData("A", 10) `should be in` parseDataFromFile.second
        ParsedData("B", 20) `should be in` parseDataFromFile.second
    }
}
