package com.jacob.core_lib.parser.instructions

import arrow.core.ValidatedNel
import arrow.core.invalidNel
import com.jacob.core_lib.common.Errors.InvalidInstruction
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

class InstructionLine(val instruction: InstructionString) : Line {

    override fun parse(): ValidatedNel<InvalidInstruction, Instruction> = with(instruction.mnemonic) {
        when {
            contains(Add.MNEMONIC) -> AddInstructionParser.from(instruction)
            contains(Sub.MNEMONIC) -> SubInstructionParser.from(instruction)
            contains(Multiply.MNEMONIC) -> MultiplyInstructionParser.from(instruction)
            contains(Move.MNEMONIC) -> MoveInstructionParser.from(instruction)
            contains(Load.MNEMONIC) -> LoadInstructionParser.from(instruction)
            contains(Compare.MNEMONIC) -> CompareInstructionParser.from(instruction)
            contains(Store.MNEMONIC) -> StoreInstructionParser.from(instruction)
            contains(Branch.MNEMONIC) -> BranchInstructionParser.from(instruction)
            contains(LABEL) -> LabelParser.from(instruction)
            else -> InvalidInstruction("Cannot parse instruction: $instruction").invalidNel()
        }
    }
}
