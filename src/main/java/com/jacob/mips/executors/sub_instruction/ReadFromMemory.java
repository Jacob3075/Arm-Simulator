package com.jacob.mips.executors.sub_instruction;

import com.jacob.mips.executors.InstructionExecutorBuilder;
import com.jacob.mips.executors.InstructionExecutor;
import com.jacob.mips.models.MemoryArray;
import com.jacob.mips.models.RegisterFile;
import com.jacob.mips.models.Word32;

import static com.jacob.mips.executors.sub_instruction.SubInstructionTypes.READ_FROM_MEMORY;

public class ReadFromMemory implements SubInstruction {

	@Override
	public SubInstructionTypes getSubInstructionType() {
		return READ_FROM_MEMORY;
	}

	@Override
	public InstructionExecutor run(
			InstructionExecutor instructionExecutor, RegisterFile registerFile, MemoryArray memoryArray) {

		Word32 newWordToWrite = memoryArray.readWordAt(instructionExecutor.getDestinationRegister());
		return new InstructionExecutorBuilder()
				       .using(instructionExecutor)
				       .setNewWordToWrite(newWordToWrite)
				       .build();
	}
}
