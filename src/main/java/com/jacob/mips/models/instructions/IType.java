package com.jacob.mips.models.instructions;

import com.jacob.mips.models.BitSet;
import com.jacob.mips.models.Word32;


public abstract class IType implements Instruction {

	protected final Word32 instruction;

	protected IType(Word32 instruction) {
		this.instruction = instruction;
	}

	public InstructionTypes getInstructionType() {
		return InstructionTypes.I_TYPE;
	}

	protected BitSet getOpCode() {
		return instruction.getBitsInRange(26, 32);
	}

	protected BitSet getRS() {
		return instruction.getBitsInRange(21, 26);
	}

	protected BitSet getRT() {
		return instruction.getBitsInRange(16, 21);
	}

	protected BitSet getImmediate() {
		return instruction.getBitsInRange(0, 16);
	}

}
