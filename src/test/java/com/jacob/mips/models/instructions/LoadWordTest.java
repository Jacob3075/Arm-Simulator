package com.jacob.mips.models.instructions;

import com.jacob.mips.models.BitSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoadWordTest {

	@Test
	void builderCreatesCorrectInstruction() {
		BitSet sourceRegister      = BitSet.fromInt(10);
		BitSet destinationRegister = BitSet.fromInt(5);
		BitSet immediate           = BitSet.fromInt(20);

		LoadWord loadWord = new LoadWord.Builder()
				                    .setSourceRegister(sourceRegister)
				                    .setDestinationRegister(destinationRegister)
				                    .setImmediate(immediate)
				                    .build();

		assertEquals(immediate.toInt(), loadWord.getImmediate().toInt());
		assertEquals(sourceRegister.toInt(), loadWord.getRS().toInt());
		assertEquals(destinationRegister.toInt(), loadWord.getRT().toInt());
		assertEquals(35, loadWord.getOpCode().toInt());
	}
}

