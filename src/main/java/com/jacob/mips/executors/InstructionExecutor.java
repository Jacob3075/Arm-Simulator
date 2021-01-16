package com.jacob.mips.executors;

import com.jacob.mips.executors.sub_instruction.SubInstruction;
import com.jacob.mips.models.BitSet;
import com.jacob.mips.models.MemoryArray;
import com.jacob.mips.models.RegisterFile;
import com.jacob.mips.models.Word32;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class InstructionExecutor {

	private final Word32 newWordToWrite;
	private final Word32 sourceRegister1;
	private final Word32 sourceRegister2;
	private final Word32 signExtendedImmediate;
	private final BitSet destinationRegister;

	private final ArrayList<SubInstruction> subInstructions;

	public InstructionExecutor(InstructionExecutorBuilder builder) {
		this.subInstructions = builder.subInstructions;
		this.sourceRegister1 = builder.sourceRegister1;
		this.sourceRegister2 = builder.sourceRegister2;
		this.destinationRegister = builder.destinationRegister;
		this.newWordToWrite = builder.newWordToWrite;
		this.signExtendedImmediate = builder.signExtendedImmediate;
	}

	public InstructionExecutor executeNextSubInstruction(MemoryArray memoryArray, RegisterFile registerFile) {
		SubInstruction subInstruction = subInstructions.remove(0);

		return subInstruction.run(this, registerFile, memoryArray);
	}

}
