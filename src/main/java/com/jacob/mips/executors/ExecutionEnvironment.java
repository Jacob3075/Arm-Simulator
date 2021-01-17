package com.jacob.mips.executors;

import com.jacob.mips.models.BitSet;
import com.jacob.mips.models.MemoryArray;
import com.jacob.mips.models.RegisterFile;
import lombok.Getter;

@Getter
public class ExecutionEnvironment {
	private final InstructionExecutor instructionExecutor;
	private final MemoryArray         memoryArray;
	private final RegisterFile        registerFile;
	private       BitSet              programCounterOffset;

	public ExecutionEnvironment(
			InstructionExecutor instructionExecutor,
			RegisterFile registerFile,
			MemoryArray memoryArray
	) {
		this.instructionExecutor = instructionExecutor;
		this.memoryArray = memoryArray;
		this.registerFile = registerFile;
	}

	public ExecutionEnvironment(
			InstructionExecutor instructionExecutor,
			RegisterFile registerFile,
			MemoryArray memoryArray,
			BitSet programCounterOffset
	) {
		this.instructionExecutor = instructionExecutor;
		this.memoryArray = memoryArray;
		this.registerFile = registerFile;
		this.programCounterOffset = programCounterOffset;
	}
}
