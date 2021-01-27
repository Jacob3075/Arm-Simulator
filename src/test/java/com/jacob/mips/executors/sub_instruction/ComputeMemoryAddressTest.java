package com.jacob.mips.executors.sub_instruction;

import com.jacob.mips.executors.ExecutionEnvironment;
import com.jacob.mips.executors.InstructionExecutor;
import com.jacob.mips.models.MemoryArray;
import com.jacob.mips.models.RegisterFile;
import com.jacob.mips.models.Word32;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ComputeMemoryAddressTest {

	@Test
	void run() {
		MemoryArray         mockMemoryArray         = mock(MemoryArray.class);
		RegisterFile        mockRegisterFile        = mock(RegisterFile.class);
		InstructionExecutor mockInstructionExecutor = mock(InstructionExecutor.class);

		int intValue1 = 20;
		int intValue2 = 10;
		when(mockInstructionExecutor.getSourceRegister1())
				.thenReturn(Word32.fromInt(intValue1));
		when(mockInstructionExecutor.getSignExtendedImmediate())
				.thenReturn(Word32.fromInt(intValue2));

		var computeMemoryAddress = new ComputeMemoryAddress();

		var executionEnvironment = new ExecutionEnvironment(
				mockInstructionExecutor,
				mockRegisterFile,
				mockMemoryArray
		);
		executionEnvironment = computeMemoryAddress.run(executionEnvironment);

		assertEquals(
				intValue1 + intValue2,
				executionEnvironment.getInstructionExecutor().getDestinationRegister().toInt()
		);

	}
}
