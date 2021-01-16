package com.jacob.mips.executors.sub_instruction;

import com.jacob.mips.executors.InstructionExecutor;
import com.jacob.mips.executors.InstructionExecutorBuilder;
import com.jacob.mips.models.BitSet;
import com.jacob.mips.models.MemoryArray;
import com.jacob.mips.models.RegisterFile;
import com.jacob.mips.models.Word32;

import static com.jacob.mips.executors.sub_instruction.SubInstructionTypes.READ_RS;

public class ReadRS implements SubInstruction {
	private final BitSet sourceAddress;

	public ReadRS(BitSet sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	@Override
	public SubInstructionTypes getSubInstructionType() {
		return READ_RS;
	}

	@Override
	public InstructionExecutor run(
			InstructionExecutor instructionExecutor,
			RegisterFile registerFile, MemoryArray memoryArray) {

		Word32 sourceRegister = registerFile.getWordAt(sourceAddress);

		return new InstructionExecutorBuilder()
				       .using(instructionExecutor)
				       .setSourceRegister1(sourceRegister)
				       .build();

	}
}
