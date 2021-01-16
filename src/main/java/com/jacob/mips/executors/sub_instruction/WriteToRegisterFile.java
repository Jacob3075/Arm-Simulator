package com.jacob.mips.executors.sub_instruction;

import com.jacob.mips.executors.InstructionExecutor;
import com.jacob.mips.models.BitSet;
import com.jacob.mips.models.MemoryArray;
import com.jacob.mips.models.RegisterFile;

import static com.jacob.mips.executors.sub_instruction.SubInstructionTypes.WRITE_TO_REGISTER_FILE;

public class WriteToRegisterFile implements SubInstruction {
	private final BitSet destinationAddress;

	public WriteToRegisterFile(BitSet destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	@Override
	public SubInstructionTypes getSubInstructionType() {
		return WRITE_TO_REGISTER_FILE;
	}

	@Override
	public InstructionExecutor run(
			InstructionExecutor instructionExecutor, RegisterFile registerFile, MemoryArray memoryArray) {
		registerFile.updateWordAt(destinationAddress, instructionExecutor.getNewWordToWrite());
		return instructionExecutor;
	}
}
