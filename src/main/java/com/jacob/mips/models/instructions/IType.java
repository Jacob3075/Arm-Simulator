package com.jacob.mips.models.instructions;

import java.util.BitSet;

public interface IType extends Instruction{

	default InstructionTypes getInstructionType() {
		return InstructionTypes.I_TYPE;
	}

	default BitSet getOpCode() {
		return getInstructionWord().getBitsInRange(0, 6);
	}

	default BitSet getRS() {
		return this.getInstructionWord().getBitsInRange(6, 11);
	}

	default BitSet getRT() {
		return getInstructionWord().getBitsInRange(11, 16);
	}

	default BitSet getImmediate() {
		return getInstructionWord().getBitsInRange(16, 32);
	}

}
