package com.jacob.mips.models.instructions;

public interface RType extends Instruction {

	default InstructionTypes getInstructionType() {
		return InstructionTypes.R_TYPE;
	}

}
