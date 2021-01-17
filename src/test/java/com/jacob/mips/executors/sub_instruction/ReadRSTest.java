package com.jacob.mips.executors.sub_instruction;

import com.jacob.mips.executors.ExecutionEnvironment;
import com.jacob.mips.executors.InstructionExecutorBuilder;
import com.jacob.mips.models.BitSet;
import com.jacob.mips.models.MemoryArray;
import com.jacob.mips.models.RegisterFile;
import com.jacob.mips.models.Word32;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ReadRSTest {
	@Test
	void name() {
		MemoryArray  mockMemoryArray  = mock(MemoryArray.class);
		RegisterFile mockRegisterFile = mock(RegisterFile.class);

		Word32 value         = new Word32();
		BitSet sourceAddress = new BitSet();

		when(mockRegisterFile.getWordAt(sourceAddress)).thenReturn(value);

		ReadRS readRS = new ReadRS(sourceAddress);

		var executionEnvironment = new ExecutionEnvironment(
				new InstructionExecutorBuilder().build(),
				mockRegisterFile,
				mockMemoryArray
		);
		executionEnvironment = readRS.run(executionEnvironment);

		assertEquals(value, executionEnvironment.getInstructionExecutor().getSourceRegister1());
	}
}
