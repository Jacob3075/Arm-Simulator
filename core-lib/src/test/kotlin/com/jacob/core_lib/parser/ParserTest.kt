package com.jacob.core_lib.parser

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.getFile
import com.jacob.core_lib.instructions.Branch
import com.jacob.core_lib.instructions.Label
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

        Move.of(DestinationRegister(1.RA), 5.I) `should be in` instructions
        Add.of(DestinationRegister(3.RA), SourceRegister(1.RA), SourceRegister(2.RA)) `should be in` instructions
        Sub.of(DestinationRegister(4.RA), SourceRegister(3.RA), 5.I)
    }

    @Test
    internal fun `test file 2`() {
        val file = getFile("/TestInputFile2.s")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` 3
        variables.size `should be equal to` 0

        Move.of(DestinationRegister(1.RA), 5.I) `should be in` instructions
        Add.of(DestinationRegister(3.RA), SourceRegister(1.RA), 2.I) `should be in` instructions
        Sub.of(DestinationRegister(4.RA), SourceRegister(1.RA), 3.I)
    }

    @Test
    internal fun `test file 3`() {
        val file = getFile("/TestInputFile3.s")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` 12
        variables.size `should be equal to` 0

        Move.of(DestinationRegister(1.RA), 5.I) `should be in` instructions
        Move.of(DestinationRegister(2.RA), 10.I) `should be in` instructions
        Branch("LABEL_1") `should be in` instructions
        Branch("LABEL_2") `should be in` instructions
        Label("LABEL_1") `should be in` instructions
        Label("LABEL_2") `should be in` instructions

        Add.of(DestinationRegister(3.RA), SourceRegister(1.RA), 2.I) `should be in` instructions
        Add.of(DestinationRegister(3.RA), SourceRegister(1.RA), SourceRegister(2.RA)) `should be in` instructions
        Move.of(DestinationRegister(3.RA), 15.I) `should be in` instructions
        Move.of(DestinationRegister(4.RA), 1.I) `should be in` instructions
    }

    @Test
    internal fun `test file 4`() {
        val file = getFile("/TestInputFile4.s")

        val parseDataFromFile = Parser.parseDataFromFile(file)

        val instructions = parseDataFromFile.first
        val variables = parseDataFromFile.second.toVariables()

        instructions.size `should be equal to` 10
        variables.size `should be equal to` 2

        Move.of(DestinationRegister(1.RA), 5.I) `should be in` instructions
        Move.of(DestinationRegister(2.RA), 10.I) `should be in` instructions
        Load.of(DestinationRegister(3.RA), "A")
        Load.of(DestinationRegister(4.RA), "BCD")

        Load.of(DestinationRegister(5.RA), SourceRegister(3.RA))
        Load.of(DestinationRegister(6.RA), SourceRegister(4.RA))

        Add.of(DestinationRegister(7.RA), SourceRegister(1.RA), SourceRegister(5.RA)) `should be in` instructions
        Sub.of(DestinationRegister(8.RA), SourceRegister(2.RA), SourceRegister(6.RA)) `should be in` instructions

        Store.of(SourceRegister(7.RA), DestinationRegister(5.RA))
        Store.of(SourceRegister(8.RA), "BCD")

        ParsedData("A", 10) `should be in` parseDataFromFile.second
        ParsedData("BCD", 15) `should be in` parseDataFromFile.second
    }
}
