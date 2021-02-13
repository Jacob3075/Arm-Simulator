package com.jacob.mips.executors;

import com.jacob.mips.models.BitSet;
import com.jacob.mips.models.MemoryArray;
import com.jacob.mips.models.RegisterFile;
import com.jacob.mips.models.Word32;
import com.jacob.mips.models.instructions.LoadWord;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstructionExecutorTest {

	@Test
	void loadWordExecution() {
		LoadWord loadWord = new LoadWord.Builder()
				                    .setImmediate(BitSet.fromInt(0))
				                    .setSourceRegister(BitSet.fromInt(0))
				                    .setDestinationRegister(BitSet.fromInt(0))
				                    .build();

		InstructionExecutor executionSequence = loadWord.getExecutionSequence();

		assertEquals(5, executionSequence.getSubInstructions().size());

		MemoryArray  memoryArray  = new MemoryArray(List.of(new Word32()));
		RegisterFile registerFile = new RegisterFile();

		ExecutionEnvironment executionEnvironment = executionSequence.executeNextSubInstruction(
				memoryArray,
				registerFile
		);

		assertEquals(4, executionEnvironment.getInstructionExecutor().getSubInstructions().size());

		executionEnvironment = executionEnvironment.getInstructionExecutor()
		                                           .executeNextSubInstruction(memoryArray, registerFile)
		                                           .getInstructionExecutor()
		                                           .executeNextSubInstruction(memoryArray, registerFile)
		                                           .getInstructionExecutor()
		                                           .executeNextSubInstruction(memoryArray, registerFile);

		assertEquals(2, executionEnvironment.getInstructionExecutor().getSubInstructions().size());
	}
}
