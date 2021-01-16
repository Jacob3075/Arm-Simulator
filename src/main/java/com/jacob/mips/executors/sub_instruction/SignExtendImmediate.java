package com.jacob.mips.executors.sub_instruction;

import com.jacob.mips.executors.InstructionExecutor;
import com.jacob.mips.executors.InstructionExecutorBuilder;
import com.jacob.mips.models.BitSet;
import com.jacob.mips.models.MemoryArray;
import com.jacob.mips.models.RegisterFile;
import com.jacob.mips.models.Word32;

public class SignExtendImmediate implements SubInstruction {
	private final Word32 immediate;
	public SignExtendImmediate(BitSet immediate) {
		this.immediate = new Word32(immediate.set(16, 32, false));
	}

	@Override
	public InstructionExecutor run(
			InstructionExecutor instructionExecutor, RegisterFile registerFile, MemoryArray memoryArray) {
		return new InstructionExecutorBuilder()
				.using(instructionExecutor)
				.setSignExtendedImmediate(immediate)
				.build();
	}
}
