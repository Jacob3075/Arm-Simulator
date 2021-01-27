package com.jacob.mips.models;

import lombok.ToString;

@ToString
public class Word32 {

	private final BitSet word;

	public Word32() {
		this.word = new BitSet(32);
	}

	public Word32(BitSet word) {
		if (word.size() > 32) {
			this.word = new BitSet(32);
		} else {
			this.word = word;
		}
	}

	public static Word32 fromInt(int value) {
		return new Word32(BitSet.fromInt(value));
	}

	public BitSet getWord() {
		return word;
	}

	public BitSet getBitsInRange(int start, int end) {
		return word.get(start, end);
	}

	public BitSet add(Word32 immediateValue) {
		return BitSet.fromInt(word.toInt() + immediateValue.toInt());
	}

	public int toInt() {
		return this.word.toInt();
	}
}
