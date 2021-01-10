package com.jacob.mips.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegisterFileTest {
	@Test
	void createRegisterFileWithFixedSize() {

		RegisterFile registerFile = new RegisterFile();
		List<Word32> storedWords  = registerFile.getStoredWords();

		assertEquals(32, storedWords.size());
		assertThrows(UnsupportedOperationException.class, () -> storedWords.add(null));
		assertThrows(UnsupportedOperationException.class, () -> storedWords.remove(0));
	}

}
