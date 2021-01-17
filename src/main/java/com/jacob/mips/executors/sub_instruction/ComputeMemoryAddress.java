package com.jacob.mips.executors.sub_instruction;

import com.jacob.mips.executors.InstructionExecutor;
import com.jacob.mips.executors.InstructionExecutorBuilder;
import com.jacob.mips.models.BitSet;
import com.jacob.mips.models.MemoryArray;
import com.jacob.mips.models.RegisterFile;

import static com.jacob.mips.executors.sub_instruction.SubInstructionTypes.COMPUTE_MEMORY_ADDRESS;

public class ComputeMemoryAddress implements SubInstruction {
	private final BitSet immediate;

	public ComputeMemoryAddress(BitSet immediate) {
		this.immediate = immediate;
	}

	@Override
	public SubInstructionTypes getSubInstructionType() {
		return COMPUTE_MEMORY_ADDRESS;
	}

	@Override
	public InstructionExecutor run(
			InstructionExecutor instructionExecutor, RegisterFile registerFile, MemoryArray memoryArray) {

		BitSet destinationRegister = instructionExecutor.getSourceRegister1().add(immediate);
		return new InstructionExecutorBuilder()
				       .using(instructionExecutor)
				       .setDestinationRegister(destinationRegister)
				       .build();

	}
}
