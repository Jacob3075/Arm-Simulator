package com.jacob.mips.executors;

import com.jacob.mips.executors.sub_instruction.SubInstruction;
import com.jacob.mips.models.Word32;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class InstructionExecutorBuilder {

	final ArrayList<SubInstruction> subInstructions;
	Word32 sourceRegister1       = null;
	Word32 sourceRegister2       = null;
	Word32 signExtendedImmediate = null;
	BitSet destinationRegister   = null;
	Word32 newWordToWrite        = null;

	public InstructionExecutorBuilder() {
		this.subInstructions = new ArrayList<>();
	}

	public InstructionExecutorBuilder using(InstructionExecutor instructionExecutor) {
		return this.addInstructions(instructionExecutor.getSubInstructions())
		           .setSourceRegister1(instructionExecutor.getSourceRegister1())
		           .setSourceRegister2(instructionExecutor.getSourceRegister2())
		           .setDestinationRegister(instructionExecutor.getDestinationRegister())
		           .setSignExtendedImmediate(instructionExecutor.getSignExtendedImmediate())
		           .setNewWordToWrite(instructionExecutor.getNewWordToWrite());
	}

	public InstructionExecutor build() {
		return new InstructionExecutor(this);
	}

	public InstructionExecutorBuilder addInstruction(SubInstruction instruction) {
		this.subInstructions.add(instruction);
		return this;
	}

	private InstructionExecutorBuilder addInstructions(List<SubInstruction> instructions) {
		this.subInstructions.addAll(instructions);
		return this;
	}

	public InstructionExecutorBuilder setSourceRegister1(Word32 sourceRegister1) {
		this.sourceRegister1 = sourceRegister1;
		return this;
	}

	public InstructionExecutorBuilder setSourceRegister2(Word32 sourceRegister2) {
		this.sourceRegister2 = sourceRegister2;
		return this;
	}

	public InstructionExecutorBuilder setDestinationRegister(BitSet destinationRegister) {
		this.destinationRegister = destinationRegister;
		return this;
	}

	public InstructionExecutorBuilder setNewWordToWrite(Word32 newWordToWrite) {
		this.newWordToWrite = newWordToWrite;
		return this;
	}

	public InstructionExecutorBuilder setSignExtendedImmediate(Word32 signExtendedImmediate) {
		this.signExtendedImmediate = signExtendedImmediate;
		return this;
	}
}
