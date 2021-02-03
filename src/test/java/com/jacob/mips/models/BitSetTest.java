package com.jacob.mips.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BitSetTest {

	@Test
	void simpleCreation() {
		BitSet bitSet = new BitSet();
		assertEquals(0, bitSet.size());

		Boolean[] booleans = {true, false, true, false};
		BitSet    bitSet1  = new BitSet(booleans);

		assertEquals(4, bitSet1.size());
		assertEquals(booleans[1], bitSet1.getBit(1));
		assertEquals(booleans[3], bitSet1.getBit(3));
		assertEquals(booleans[0], bitSet1.getBit(0));
		assertEquals(booleans[2], bitSet1.getBit(2));
	}

	@Test
	void signExtension() {
		Boolean[] booleans = {true, true, true, false};
		BitSet    bitSet   = new BitSet(booleans);

		BitSet signExtendTo8BitSet = bitSet.signExtendTo(8);
		assertEquals(8, signExtendTo8BitSet.size());

		BitSet signExtendTo32BitSet = signExtendTo8BitSet.signExtendTo(32);
		assertEquals(32, signExtendTo32BitSet.size());
	}

	@Test
	void settingBits() {
		Boolean[] booleans = {false, false, true, true, false};
		BitSet    bitSet   = new BitSet(booleans);

		BitSet set1 = bitSet.set(0);
		assertTrue(set1.getBit(0));

		BitSet set2 = bitSet.set(3);
		assertTrue(set2.getBit(3));

		BitSet set3 = bitSet.set(4);
		assertTrue(set3.getBit(4));

		BitSet set4 = bitSet.set(8);
		assertTrue(set4.getBit(8));
		assertFalse(set4.getBit(6));
		assertFalse(set4.getBit(7));
		assertEquals(9, set4.size());
	}

	@Test
	void settingBitRanges() {
		Boolean[] booleans = {true, true, true, false, false, false, true, true, false};
		BitSet    bitSet   = new BitSet(booleans);

		BitSet set1 = bitSet.set(0, 2, true);

		assertTrue(set1.getBit(0));
		assertTrue(set1.getBit(1));

		BitSet set2 = bitSet.set(0, 3, false);

		assertFalse(set2.getBit(0));
		assertFalse(set2.getBit(1));
		assertFalse(set2.getBit(2));

		BitSet set3 = bitSet.set(3, 6, true);

		assertTrue(set3.getBit(3));
		assertTrue(set3.getBit(4));
		assertTrue(set3.getBit(5));

		BitSet set4 = bitSet.set(7, 12, true);

		assertTrue(set4.getBit(7));
		assertTrue(set4.getBit(8));
		assertTrue(set4.getBit(9));
		assertTrue(set4.getBit(10));
		assertTrue(set4.getBit(11));

		BitSet set5 = bitSet.set(7, 12, false);

		assertFalse(set5.getBit(7));
		assertFalse(set5.getBit(8));
		assertFalse(set5.getBit(9));
		assertFalse(set5.getBit(10));
		assertFalse(set5.getBit(11));
	}

	@Test
	void creatingFromInt() {
		BitSet bitSet5 = BitSet.fromInt(5);
		assertTrue(bitSet5.getBit(0));
		assertFalse(bitSet5.getBit(1));
		assertTrue(bitSet5.getBit(2));

		BitSet bitSet11 = BitSet.fromInt(11);
		assertTrue(bitSet11.getBit(0));
		assertFalse(bitSet11.getBit(1));
		assertTrue(bitSet11.getBit(2));
		assertTrue(bitSet11.getBit(3));
	}

	@Test
	void convertingToInt() {
		BitSet bitSet11 = new BitSet(new Boolean[]{true, false, true, true});
		assertEquals(11, bitSet11.toInt());

		BitSet bitSet10 = new BitSet(new Boolean[]{true, false, true, false});
		assertEquals(10, bitSet10.toInt());
	}

	@Test
	void concatMethod() {
		BitSet bitSet1 = new BitSet(Arrays.asList(true, true, false, true, false, false));
		BitSet bitSet2 = new BitSet(Arrays.asList(true, false, false, false, true, false));

		BitSet concatedBitSet = bitSet1.concat(bitSet2);

		assertEquals(bitSet1.getBit(0), concatedBitSet.getBit(0));
		assertEquals(bitSet1.getBit(3), concatedBitSet.getBit(3));
		assertEquals(bitSet2.getBit(7 - 6), concatedBitSet.getBit(7));
		assertEquals(bitSet2.getBit(9 - 6), concatedBitSet.getBit(9));
		assertEquals(bitSet2.getBit(11 - 6), concatedBitSet.getBit(11));
		assertEquals(6, bitSet1.size());
		assertEquals(bitSet1.size() + bitSet2.size(), concatedBitSet.size());
	}
}
