package com.jacob.mips.executors.sub_instruction;

import com.jacob.mips.executors.InstructionExecutor;
import com.jacob.mips.models.MemoryArray;
import com.jacob.mips.models.RegisterFile;

public interface SubInstruction {
	default SubInstructionTypes getSubInstructionType() {
		return SubInstructionTypes.READ_RS;
	}

	default InstructionExecutor run(
			InstructionExecutor instructionExecutor, RegisterFile registerFile,
			MemoryArray memoryArray) {
		return instructionExecutor;
	}
}
