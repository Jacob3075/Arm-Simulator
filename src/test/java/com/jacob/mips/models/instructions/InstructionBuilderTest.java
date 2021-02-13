package com.jacob.mips.models.instructions;

import com.jacob.mips.models.BitSet;
import com.jacob.mips.models.ProgramInstructions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InstructionBuilderTest {

	@Test
	void addInstructions() {
		InstructionBuilder instructionBuilder = new InstructionBuilder(null);


		LoadWord loadWord1 = new LoadWord.Builder()
				                     .setSourceRegister(BitSet.fromInt(5))
				                     .setDestinationRegister(BitSet.fromInt(2))
				                     .setImmediate(BitSet.fromInt(10)).build();
		var loadWord2 = new LoadWord.Builder()
				                .setSourceRegister(BitSet.fromInt(5))
				                .setDestinationRegister(BitSet.fromInt(2))
				                .setImmediate(BitSet.fromInt(10))
				                .build();
		var loadWord3 = new LoadWord.Builder()
				                .setSourceRegister(BitSet.fromInt(5))
				                .setDestinationRegister(BitSet.fromInt(2))
				                .setImmediate(BitSet.fromInt(10))
				                .build();

		ProgramInstructions instructions = instructionBuilder.addInstruction(loadWord1)
		                                                     .addInstruction(loadWord2)
		                                                     .addInstruction(loadWord3)
		                                                     .getInstructions();

		Optional<Instruction> instruction1 = instructions.getInstructionAt(0);
		var                   instruction2 = instructions.getInstructionAt(1);
		var                   instruction3 = instructions.getInstructionAt(2);
		var                   instruction4 = instructions.getInstructionAt(3);

		assertTrue(instruction1.isPresent());
		assertEquals(InstructionTypes.I_TYPE, instruction1.get().getInstructionType());

		assertTrue(instruction2.isPresent());
		assertEquals(InstructionTypes.I_TYPE, instruction2.get().getInstructionType());

		assertTrue(instruction3.isPresent());
		assertEquals(InstructionTypes.I_TYPE, instruction3.get().getInstructionType());

		assertTrue(instruction4.isEmpty());
	}
}
