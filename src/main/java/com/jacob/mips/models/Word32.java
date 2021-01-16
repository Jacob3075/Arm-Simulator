package com.jacob.mips.models;

import lombok.ToString;

@ToString
public class Word32 {

	private final BitSet word;

	public Word32() {
		this.word = new BitSet();
	}

	public Word32(BitSet word) {
		if (word.size() > 32) {
			this.word = new BitSet();
		} else {
			this.word = word;
		}
	}

	public BitSet getWord() {
		return word;
	}

	public BitSet getBitsInRange(int start, int end) {
		return word.get(start, end);
	}

	public BitSet add(BitSet immediateValue) {
		return BitSet.fromInt(word.toInt() + immediateValue.toInt());
	}
}
