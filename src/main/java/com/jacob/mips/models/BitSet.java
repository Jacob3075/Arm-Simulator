package com.jacob.mips.models;

import java.util.Arrays;
import java.util.List;

public class BitSet {
	private final List<Boolean> bits;

	public BitSet(Boolean[] bits) {
		if (bits.length > 32) {
			this.bits = Arrays.asList(new Boolean[32]);
		} else {
			this.bits = Arrays.asList(bits);
		}
	}

	public BitSet() {
		this.bits = Arrays.asList(new Boolean[32]);
	}

	public static BitSet fromInt(int i) {
		return null;
	}

	public int toInt() {
		return 0;
	}

	public int size() {
		return 0;
	}

	public BitSet get(int start, int end) {
		return null;
	}

	public void set(int i, int i1, boolean b) {

	}
}
