package com.jacob.mips.executors.sub_instruction;

import com.jacob.mips.executors.ExecutionEnvironment;
import com.jacob.mips.executors.InstructionExecutor;
import com.jacob.mips.models.BitSet;
import com.jacob.mips.models.MemoryArray;
import com.jacob.mips.models.RegisterFile;
import com.jacob.mips.models.Word32;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WriteToRegisterFileTest {

	@Test
	void run() {
		MemoryArray         mockMemoryArray         = mock(MemoryArray.class);
		InstructionExecutor mockInstructionExecutor = mock(InstructionExecutor.class);

		Word32 writtenWord        = Word32.fromInt(10);
		BitSet destinationAddress = BitSet.fromInt(20);

		when(mockInstructionExecutor.getNewWordToWrite()).thenReturn(writtenWord);

		var writeToRegisterFile = new WriteToRegisterFile(destinationAddress);
		var executionEnvironment = new ExecutionEnvironment(
				mockInstructionExecutor,
				new RegisterFile(),
				mockMemoryArray
		);
		executionEnvironment = writeToRegisterFile.run(executionEnvironment);

		assertEquals(
				writtenWord,
				executionEnvironment.getRegisterFile().getWordAt(destinationAddress)
		);
	}
}
