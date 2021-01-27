package com.jacob.mips.executors.sub_instruction;

import com.jacob.mips.executors.ExecutionEnvironment;
import com.jacob.mips.executors.InstructionExecutor;
import com.jacob.mips.executors.InstructionExecutorBuilder;
import com.jacob.mips.models.BitSet;
import com.jacob.mips.models.MemoryArray;
import com.jacob.mips.models.RegisterFile;

import static com.jacob.mips.executors.sub_instruction.SubInstructionTypes.COMPUTE_MEMORY_ADDRESS;

public class ComputeMemoryAddress implements SubInstruction {

	@Override
	public SubInstructionTypes getSubInstructionType() {
		return COMPUTE_MEMORY_ADDRESS;
	}

	@Override
	public ExecutionEnvironment run(ExecutionEnvironment executionEnvironment) {

		MemoryArray         memoryArray         = executionEnvironment.getMemoryArray();
		RegisterFile        registerFile        = executionEnvironment.getRegisterFile();
		InstructionExecutor instructionExecutor = executionEnvironment.getInstructionExecutor();

		BitSet destinationRegister = instructionExecutor.getSourceRegister1()
		                                                .add(instructionExecutor.getSignExtendedImmediate());
		return new ExecutionEnvironment(
				new InstructionExecutorBuilder()
						.using(instructionExecutor)
						.setDestinationRegister(destinationRegister)
						.build(),
				registerFile,
				memoryArray
		);
	}
}
