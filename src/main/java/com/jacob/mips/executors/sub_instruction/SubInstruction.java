package com.jacob.mips.executors.sub_instruction;

import com.jacob.mips.executors.ExecutionEnvironment;

public interface SubInstruction {
	default SubInstructionTypes getSubInstructionType() {
		return SubInstructionTypes.READ_RS;
	}

	default ExecutionEnvironment run(ExecutionEnvironment executionEnvironment) {
		return executionEnvironment;
	}
}
