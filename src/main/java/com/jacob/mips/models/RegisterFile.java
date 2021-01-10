package com.jacob.mips.models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RegisterFile {
	private final List<Word32> words;

	public RegisterFile() {
		words = Arrays.stream(new Word32[32])
		              .map(nullObj -> new Word32())
		              .collect(Collectors.toUnmodifiableList());
	}

	public RegisterFile(List<Word32> words) {
		this.words = words;
	}

	public List<Word32> getStoredWords() {
		return words;
	}

	public Word32 getWordAt(int location) {
		return words.get(location);
	}

	public List<Word32> updateWordAt(int location, Word32 newWord) {
		words.set(location, newWord);
		return words;
	}
}
