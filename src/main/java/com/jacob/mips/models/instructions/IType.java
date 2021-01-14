package com.jacob.mips.models.instructions;

import com.jacob.mips.models.Word32;

import java.util.BitSet;

public abstract class IType implements Instruction {

	protected final Word32 instruction;

	protected IType(Word32 instruction) {
		this.instruction = instruction;
	}

	public InstructionTypes getInstructionType() {
		return InstructionTypes.I_TYPE;
	}

	protected BitSet getOpCode() {
		return instruction.getBitsInRange(0, 6);
	}

	protected BitSet getRS() {
		return instruction.getBitsInRange(6, 11);
	}

	protected BitSet getRT() {
		return instruction.getBitsInRange(11, 16);
	}

	protected BitSet getImmediate() {
		return instruction.getBitsInRange(16, 32);
	}

}
