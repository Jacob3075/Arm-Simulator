package com.jacob.core_lib.core

import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.RA
import com.jacob.core_lib.common.addresses.DestinationRegister
import com.jacob.core_lib.common.addresses.SourceRegister
import com.jacob.core_lib.instructions.Branch
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.Label
import com.jacob.core_lib.instructions.add.Add
import com.jacob.core_lib.instructions.load.Load
import com.jacob.core_lib.instructions.move.Move
import com.jacob.core_lib.instructions.store.Store
import com.jacob.core_lib.instructions.sub.Sub
import com.jacob.core_lib.parser.data.ParsedData

val test1Data = Pair<List<Instruction>, List<ParsedData>>(
    listOf(
        Move.of(DestinationRegister(1.RA), 5.I),
        Move.of(DestinationRegister(2.RA), 10.I),
        Add.of(DestinationRegister(3.RA), SourceRegister(1.RA), SourceRegister(2.RA)),
        Sub.of(DestinationRegister(4.RA), SourceRegister(3.RA), 5.I),
    ),
    emptyList()
)

val test2Data = Pair<List<Instruction>, List<ParsedData>>(
    listOf(
        Move.of(DestinationRegister(1.RA), 5.I),
        Add.of(DestinationRegister(3.RA), SourceRegister(1.RA), 2.I),
        Sub.of(DestinationRegister(4.RA), SourceRegister(1.RA), SourceRegister(3.RA)),
    ),
    emptyList()
)

val test3Data = Pair<List<Instruction>, List<ParsedData>>(
    listOf(
        Move.of(DestinationRegister(1.RA), 5.I),
        Move.of(DestinationRegister(2.RA), 10.I),
        Branch("LABEL_1"),
        Add.of(DestinationRegister(3.RA), SourceRegister(1.RA), 2.I),
        Add.of(DestinationRegister(4.RA), SourceRegister(1.RA), SourceRegister(2.RA)),
        Label("LABEL_1"),
        Sub.of(DestinationRegister(4.RA), SourceRegister(1.RA), SourceRegister(3.RA)),
        Branch("LABEL_2"),
        Move.of(DestinationRegister(3.RA), 15.I),
        Move.of(DestinationRegister(4.RA), 1.I),
        Label("LABEL_2"),
        Move.of(DestinationRegister(5.RA), 2.I),
    ),
    emptyList()
)

val test4Data = Pair(
    listOf(
        Move.of(DestinationRegister(1.RA), 5.I),
        Move.of(DestinationRegister(2.RA), 10.I),
        Load.of(DestinationRegister(3.RA), "A"),
        Load.of(DestinationRegister(4.RA), "BCD"),
        Load.of(DestinationRegister(5.RA), SourceRegister(3.RA)),
        Load.of(DestinationRegister(6.RA), SourceRegister(4.RA)),
        Add.of(DestinationRegister(7.RA), SourceRegister(1.RA), SourceRegister(5.RA)),
        Sub.of(DestinationRegister(8.RA), SourceRegister(2.RA), SourceRegister(6.RA)),
        Store.of(SourceRegister(7.RA), DestinationRegister(3.RA)),
        Store.of(SourceRegister(8.RA), "BCD"),
    ),
    listOf(
        ParsedData("A", 10),
        ParsedData("BCD", 15)
    )
)

// TODO: ADD TESTS WITH SHIFT OPERATIONS
val test5Data = Pair<List<Instruction>, List<ParsedData>>(emptyList(), emptyList())
