package com.jacob.mips.models;

import com.jacob.mips.models.instructions.InstructionBuilder;

import java.util.function.Function;

public class Core {
	private final ProgramCounter programCounter;
	private final MemoryArray    memoryArray;
	private final RegisterFile   registerFile;

	private static final Function<ProgramInstructions, Core> createCoreWithInstructions = Core::new;

	private Core(ProgramInstructions instructions) {
		this.programCounter = new ProgramCounter(instructions);
		this.memoryArray = new MemoryArray();
		this.registerFile = new RegisterFile();
	}

	public static InstructionBuilder newCoreWithInstruction() {
		return new InstructionBuilder(createCoreWithInstructions);
	}
}
