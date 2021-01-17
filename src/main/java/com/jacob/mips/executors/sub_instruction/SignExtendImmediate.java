package com.jacob.mips.executors.sub_instruction;

import com.jacob.mips.executors.InstructionExecutor;
import com.jacob.mips.executors.InstructionExecutorBuilder;
import com.jacob.mips.models.BitSet;
import com.jacob.mips.models.MemoryArray;
import com.jacob.mips.models.RegisterFile;
import com.jacob.mips.models.Word32;

public class SignExtendImmediate implements SubInstruction {
	private final BitSet immediate;

	public SignExtendImmediate(BitSet immediate) {
		this.immediate = immediate;
	}

	@Override
	public InstructionExecutor run(
			InstructionExecutor instructionExecutor, RegisterFile registerFile, MemoryArray memoryArray) {

		Word32 signExtendedImmediate = new Word32(immediate.signExtendTo(32));
		return new InstructionExecutorBuilder()
				       .using(instructionExecutor)
				       .setSignExtendedImmediate(signExtendedImmediate)
				       .build();
	}
}
