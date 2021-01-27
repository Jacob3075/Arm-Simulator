package com.jacob.mips.models;

import com.jacob.mips.models.instructions.Instruction;

import java.util.Optional;

public class ProgramCounter {
	private final ProgramInstructions instructions;
	private int currentInstruction = 0;


	public ProgramCounter(ProgramInstructions instructions) {
		this.instructions = instructions;
	}

	public ProgramCounter() {
		this.instructions = new ProgramInstructions();
	}

	public Optional<Instruction> getNextInstruction() {
		return instructions.getInstructionAt(currentInstruction++);
	}
}
