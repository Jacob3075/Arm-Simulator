package com.jacob.mips.models.instructions;

import com.jacob.mips.executors.InstructionExecutor;
import com.jacob.mips.executors.InstructionExecutorBuilder;
import com.jacob.mips.executors.sub_instruction.*;
import com.jacob.mips.models.BitSet;
import com.jacob.mips.models.Word32;

public class LoadWord extends IType {

	private LoadWord(Word32 instruction) {
		super(instruction);
	}

	@Override
	public InstructionExecutor getExecutionSequence() {
		return new InstructionExecutorBuilder()
				       .addInstruction(new ReadRS(getRS()))
				       .addInstruction(new SignExtendImmediate(getImmediate()))
				       .addInstruction(new ComputeMemoryAddress())
				       .addInstruction(new ReadFromMemory())
				       .addInstruction(new WriteToRegisterFile(getRT()))
				       .build();
	}

	@Override
	public String toString() {
		return instruction.toString();
	}

	public static class Builder {

		private BitSet rS;
		private BitSet rT;
		private BitSet immediate;

		public LoadWord build() {
			BitSet combinedBitSet = new BitSet().concat(BitSet.fromInt(35).signExtendTo(6))
			                                    .concat(rS.signExtendTo(5))
			                                    .concat(rT.signExtendTo(5))
			                                    .concat(immediate.signExtendTo(16));

			return new LoadWord(new Word32(combinedBitSet));
		}

		public Builder setSourceRegister(BitSet rS) {
			this.rS = rS;
			return this;
		}

		public Builder setDestinationRegister(BitSet rT) {
			this.rT = rT;
			return this;
		}

		public Builder setImmediate(BitSet immediate) {
			this.immediate = immediate;
			return this;
		}
	}
}
