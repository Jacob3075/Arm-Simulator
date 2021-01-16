package com.jacob.mips.models;

import java.util.ArrayList;
import java.util.List;

public class MemoryArray {
	private final List<Word32> memory;

	public MemoryArray(List<Word32> memory) {
		this.memory = memory;
	}

	public MemoryArray() {
		this.memory = new ArrayList<>();
	}

	public Word32 readWordAt(int location) {
		return memory.get(location);
	}

	public Word32 readWordAt(BitSet location) {
		return this.readWordAt(location.toInt());
	}

	public MemoryArray writeWordTo(int location, Word32 word32) {
		memory.set(location, word32);
		return new MemoryArray(memory);
	}
}
