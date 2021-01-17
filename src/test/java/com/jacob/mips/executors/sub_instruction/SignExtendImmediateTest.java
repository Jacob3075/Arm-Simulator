package com.jacob.mips.executors.sub_instruction;

import com.jacob.mips.executors.ExecutionEnvironment;
import com.jacob.mips.executors.InstructionExecutorBuilder;
import com.jacob.mips.models.BitSet;
import com.jacob.mips.models.MemoryArray;
import com.jacob.mips.models.RegisterFile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class SignExtendImmediateTest {

	@Test
	void run() {
		MemoryArray  mockMemoryArray  = mock(MemoryArray.class);
		RegisterFile mockRegisterFile = mock(RegisterFile.class);

		int    intValue            = -12757;
		BitSet bitSet              = BitSet.fromInt(intValue);
		var    signExtendImmediate = new SignExtendImmediate(bitSet);

		var executionEnvironment = new ExecutionEnvironment(
				new InstructionExecutorBuilder().build(),
				mockRegisterFile,
				mockMemoryArray
		);
		executionEnvironment = signExtendImmediate.run(executionEnvironment);

		assertEquals(
				intValue,
				executionEnvironment.getInstructionExecutor().getSignExtendedImmediate().toInt()
		);
	}
}
