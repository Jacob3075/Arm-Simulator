package com.jacob.core_lib.parser.instructions

import com.jacob.core_lib.common.regex.InstructionRegex.Add
import com.jacob.core_lib.common.regex.InstructionRegex.Branch
import com.jacob.core_lib.common.regex.InstructionRegex.Compare
import com.jacob.core_lib.common.regex.InstructionRegex.LABEL
import com.jacob.core_lib.common.regex.InstructionRegex.Load
import com.jacob.core_lib.common.regex.InstructionRegex.Move
import com.jacob.core_lib.common.regex.InstructionRegex.Multiply
import com.jacob.core_lib.common.regex.InstructionRegex.Store
import com.jacob.core_lib.common.regex.InstructionRegex.Sub
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.InstructionString
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

    override fun parse(): Instruction {
        return when {
            instruction.contains(Add.MNEMONIC) -> AddInstructionParser.from(InstructionString(instruction))
            instruction.contains(Sub.MNEMONIC) -> SubInstructionParser.from(InstructionString(instruction))
            instruction.contains(Multiply.MNEMONIC) -> MultiplyInstructionParser.from(InstructionString(instruction))
            instruction.contains(Move.MNEMONIC) -> MoveInstructionParser.from(InstructionString(instruction))
            instruction.contains(Load.MNEMONIC) -> LoadInstructionParser.from(InstructionString(instruction))
            instruction.contains(Compare.MNEMONIC) -> CompareInstructionParser.from(InstructionString(instruction))
            instruction.contains(Store.MNEMONIC) -> StoreInstructionParser.from(InstructionString(instruction))
            instruction.contains(Branch.MNEMONIC) -> BranchInstructionParser.from(InstructionString(instruction))
            instruction.contains(LABEL) -> LabelParser.from(instruction)
            else -> throw IllegalArgumentException("Cannot parse instruction: $instruction")
        }
    }
}
