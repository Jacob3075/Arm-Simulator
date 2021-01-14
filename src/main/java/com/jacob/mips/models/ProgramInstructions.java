package com.jacob.mips.models;

import com.jacob.mips.models.instructions.Instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgramInstructions {
	private final List<Instruction> instructions;

	public ProgramInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	public ProgramInstructions() {
		this.instructions = new ArrayList<>();
	}

	public Optional<Instruction> getInstructionAt(int index) {
		try {
			Instruction instruction = instructions.get(index);
			return Optional.of(instruction);
		} catch (IndexOutOfBoundsException exception) {
			return Optional.empty();
		}
	}
}
