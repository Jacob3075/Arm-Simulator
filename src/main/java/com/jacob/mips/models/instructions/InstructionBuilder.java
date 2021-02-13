package com.jacob.mips.models.instructions;

import com.jacob.mips.models.Core;
import com.jacob.mips.models.ProgramInstructions;

import java.util.function.Function;

public class InstructionBuilder {

	private final Function<ProgramInstructions, Core> createCoreWithInstructions;
	private final ProgramInstructions                 instructions;

	public InstructionBuilder(Function<ProgramInstructions, Core> createCoreWithInstructions) {
		instructions = new ProgramInstructions();
		this.createCoreWithInstructions = createCoreWithInstructions;
	}

	private InstructionBuilder(
			ProgramInstructions instructions,
			Function<ProgramInstructions, Core> createCoreWithInstructions) {
		this.createCoreWithInstructions = createCoreWithInstructions;
		this.instructions = instructions;

	}

	public Core build() {
		return createCoreWithInstructions.apply(instructions);
	}

	public InstructionBuilder addInstruction(Instruction instruction) {
		return new InstructionBuilder(
				instructions.addInstruction(instruction),
				this.createCoreWithInstructions
		);
	}

	public ProgramInstructions getInstructions() {
		return instructions;
	}
}
