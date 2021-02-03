package com.jacob.mips.models;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.toBinaryString;

public class BitSet {
	private final ArrayList<Boolean> bits = new ArrayList<>();

	public BitSet() {
	}

	public BitSet(int size) {
		Boolean[] booleans = new Boolean[size];
		Arrays.fill(booleans, false);
		this.bits.addAll(Arrays.asList(booleans));
	}

	public BitSet(Boolean[] bits) {
		List<Boolean> bitList = Arrays.asList(bits);
		this.bits.addAll(bitList);
	}

	public BitSet(List<Boolean> bits) {
		this.bits.addAll(bits);
	}

	public static BitSet fromInt(int intValue) {
		List<Character> characterList = new ArrayList<>();
		for (char c : toBinaryString(intValue).toCharArray()) {
			characterList.add(c);
		}

		List<Boolean> booleanArray = characterList.stream()
		                                          .map(Object::toString)
		                                          .map(charValue -> charValue.equals("1"))
		                                          .collect(Collectors.toList());

		return new BitSet(booleanArray);
	}

	public BitSet concat(BitSet bitSet) {
		ArrayList<Boolean> newBitSet = new ArrayList<>(bits);
		newBitSet.addAll(bitSet.bits);
		return new BitSet(newBitSet);
	}

	public BitSet signExtendTo(int finalLength) {
		int       lastIndex = this.bits.size() - 1;
		Boolean[] integers  = new Boolean[finalLength - lastIndex - 1];
		Arrays.fill(integers, false);
		this.bits.addAll(Arrays.asList(integers));

		return this;
	}

	public int toInt() {
		return new BigInteger(this.toString(), 2).intValue();
	}

	public int size() {
		return this.bits.size();
	}

	public Boolean getBit(int location) {
		return this.bits.get(location);
	}

	public BitSet get(int start, int end) {
		return new BitSet(this.bits.subList(start, end));
	}

	public BitSet set(int location) {
		if (location >= this.bits.size()) {
			Boolean[] booleans = new Boolean[location - this.bits.size() + 1];
			Arrays.fill(booleans, false);
			this.bits.addAll(Arrays.asList(booleans));
		}
		this.bits.set(location, true);
		return this;
	}

	public BitSet set(int start, int end, Boolean value) {
		if (end < this.bits.size()) {
			for (int i = start; i < end; i++) {
				this.bits.set(i, value);
			}
		} else {
			for (int i = start; i < end; i++) {
				if (i < this.bits.size()) {
					this.bits.set(i, value);
				} else {
					this.bits.add(value);
				}
			}
		}
		return this;
	}

	@Override
	public String toString() {
		return this.bits.stream()
		                .map(boolValue -> ((boolean) boolValue) ? "1" : "0")
		                .collect(Collectors.joining(""));
	}
}
