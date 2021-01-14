package com.jacob.mips.models.instructions;

import com.jacob.mips.models.Word32;

public interface Instruction {

	public InstructionTypes getInstructionType();

	public void getSubInstructionSequence();

	public Word32 getInstructionWord();

}
