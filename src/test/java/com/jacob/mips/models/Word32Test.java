package com.jacob.mips.models;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Word32Test {
	@Test
	void basicOperationsOn32BitWord() {
		Word32            word32       = new Word32();
		Supplier<Integer> numberOfOnes = () -> word32.getWord().cardinality();

		assertEquals(0, numberOfOnes.get());

		word32.getWord().set(5);
		assertEquals(1, numberOfOnes.get());

		word32.getWord().clear(5);
		assertEquals(0, numberOfOnes.get());

		word32.getWord().flip(0, 32);
		assertEquals(32, numberOfOnes.get());

	}

	@Test
	public void getBitValuesInRange() {

	}
}
