package com.jacob.mips.models.instructions;

import com.jacob.mips.executors.InstructionExecutorBuilder;
import com.jacob.mips.executors.InstructionExecutor;
import com.jacob.mips.executors.sub_instruction.*;
import com.jacob.mips.models.Word32;

public class LoadWord extends IType {

	public LoadWord(Word32 instruction) {
		super(instruction);
	}

	@Override
	public InstructionExecutor getExecutionSequence() {
		return new InstructionExecutorBuilder()
				       .addInstruction(new ReadRS(getRS()))
				       .addInstruction(new SignExtendImmediate(getImmediate()))
				       .addInstruction(new ComputeMemoryAddress(getImmediate()))
				       .addInstruction(new ReadFromMemory())
				       .addInstruction(new WriteToRegisterFile(getRT()))
				       .build();
	}
}
