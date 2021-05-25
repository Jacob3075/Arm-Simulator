package com.jacob.core_lib.parser.instructions

import com.jacob.core_lib.common.regex.InstructionRegex.*
import com.jacob.core_lib.common.regex.InstructionRegex.Companion.LABEL
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.Line
import com.jacob.core_lib.parser.instructions.add.AddInstructionParser
import com.jacob.core_lib.parser.instructions.branch.BranchInstructionParser
import com.jacob.core_lib.parser.instructions.compare.CompareInstructionParser
import com.jacob.core_lib.parser.instructions.label.LabelParser
import com.jacob.core_lib.parser.instructions.load.LoadInstructionParser
import com.jacob.core_lib.parser.instructions.move.MoveInstructionParser
import com.jacob.core_lib.parser.instructions.multiply.MultiplyInstructionParser
import com.jacob.core_lib.parser.instructions.store.StoreInstructionParser
import com.jacob.core_lib.parser.instructions.sub.SubInstructionParser

class InstructionLine(val instruction: String) : Line {

    //    TODO: WRAP 'INSTRUCTION' STRING IN OBJECT
    override fun parse(): Instruction {
        return when {
            instruction.contains(Add.MNEMONIC) -> AddInstructionParser.from(instruction)
            instruction.contains(Sub.MNEMONIC) -> SubInstructionParser.from(instruction)
            instruction.contains(Multiply.MNEMONIC) -> MultiplyInstructionParser.from(instruction)
            instruction.contains(Move.MNEMONIC) -> MoveInstructionParser.from(instruction)
            instruction.contains(Load.MNEMONIC) -> LoadInstructionParser.from(instruction)
            instruction.contains(Compare.MNEMONIC) -> CompareInstructionParser.from(instruction)
            instruction.contains(Store.MNEMONIC) -> StoreInstructionParser.from(instruction)
            instruction.contains(Branch.MNEMONIC) -> BranchInstructionParser.from(instruction)
            instruction.contains(LABEL) -> LabelParser.from(instruction)
            else -> throw IllegalArgumentException("Cannot parse instruction: $instruction")
        }
    }
}
