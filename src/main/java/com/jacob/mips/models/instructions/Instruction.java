package com.jacob.mips.models.instructions;

import com.jacob.mips.executors.InstructionExecutor;

public interface Instruction {

	InstructionTypes getInstructionType();

	InstructionExecutor getExecutionSequence();

}
