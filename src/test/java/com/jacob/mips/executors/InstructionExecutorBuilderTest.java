package com.jacob.mips.executors;

import com.jacob.mips.executors.sub_instruction.ReadRS;
import com.jacob.mips.models.BitSet;
import com.jacob.mips.models.MemoryArray;
import org.junit.jupiter.api.Test;


class InstructionExecutorBuilderTest {

	@Test
	void creatingInstructionExecutorInstance() {

		MemoryArray memoryArray = new MemoryArray();


		var instructionExecutor = new InstructionExecutorBuilder()
				                          .addInstruction(new ReadRS(new BitSet()))
				                          .build();

	}

}
