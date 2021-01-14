package com.jacob.mips.models.instructions;

import com.jacob.mips.models.Word32;

public class LoadWord implements IType {

	private final Word32 instruction;

	public LoadWord(Word32 instruction) {
		this.instruction = instruction;
	}

	@Override
	public void getSubInstructionSequence() {


	}

	@Override
	public Word32 getInstructionWord() {
		return instruction;
	}
}
