package com.jacob.mips.models;

import lombok.ToString;

import java.util.BitSet;

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

	public BitSet getWord() {
		return word;
	}
}
