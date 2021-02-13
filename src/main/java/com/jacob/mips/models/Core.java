package com.jacob.mips.models;

import com.jacob.mips.models.instructions.InstructionBuilder;

import java.util.function.Function;

public class Core {
    private final ProgramCounter programCounter;
    private final MemoryArray memoryArray;
    private final RegisterFile registerFile;

    private final Function<ProgramInstructions, Core> createCoreWithInstructions = Core::new;

    public Core() {
        this.programCounter = new ProgramCounter();
        this.memoryArray = new MemoryArray();
        this.registerFile = new RegisterFile();
    }

    private Core(ProgramInstructions instructions) {
        this.programCounter = new ProgramCounter(instructions);
        this.memoryArray = new MemoryArray();
        this.registerFile = new RegisterFile();
    }

    public InstructionBuilder addInstructions() {
        return new InstructionBuilder(createCoreWithInstructions);
   }
}
