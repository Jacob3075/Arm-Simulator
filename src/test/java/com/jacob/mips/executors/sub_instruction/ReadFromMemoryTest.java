package com.jacob.mips.executors.sub_instruction;

import com.jacob.mips.executors.InstructionExecutor;
import com.jacob.mips.models.BitSet;
import com.jacob.mips.models.MemoryArray;
import com.jacob.mips.models.RegisterFile;
import com.jacob.mips.models.Word32;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReadFromMemoryTest {

	@Test
	void run() {
		MemoryArray         mockMemoryArray         = mock(MemoryArray.class);
		RegisterFile        mockRegisterFile        = mock(RegisterFile.class);
		InstructionExecutor mockInstructionExecutor = mock(InstructionExecutor.class);

		int intValue2 = 50;

		BitSet bitSet = BitSet.fromInt(10);
		Word32 value = Word32.fromInt(intValue2);

		when(mockInstructionExecutor.getDestinationRegister()).thenReturn(bitSet);
		when(mockMemoryArray.readWordAt(bitSet)).thenReturn(value);

		var readFromMemory = new ReadFromMemory();

		var instructionExecutor = readFromMemory.run(
				mockInstructionExecutor,
				mockRegisterFile,
				mockMemoryArray
		);

		assertEquals(value, instructionExecutor.getNewWordToWrite());
	}
}
