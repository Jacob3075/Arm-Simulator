package com.jacob.core_lib.core

import com.jacob.core_lib.common.DR
import com.jacob.core_lib.common.I
import com.jacob.core_lib.common.SR
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
        Move.of(1.DR, 5.I),
        Move.of(2.DR, 10.I),
        Add.of(3.DR, 1.SR, 2.SR),
        Sub.of(4.DR, 3.SR, 5.I),
    ),
    emptyList()
)

val test2Data = Pair<List<Instruction>, List<ParsedData>>(
    listOf(
        Move.of(1.DR, 5.I),
        Add.of(3.DR, 1.SR, 2.I),
        Sub.of(4.DR, 1.SR, 3.SR),
    ),
    emptyList()
)

val test3Data = Pair<List<Instruction>, List<ParsedData>>(
    listOf(
        Move.of(1.DR, 5.I),
        Move.of(2.DR, 10.I),
        Branch("LABEL_1"),
        Add.of(3.DR, 1.SR, 2.I),
        Add.of(4.DR, 1.SR, 2.SR),
        Label("LABEL_1"),
        Sub.of(4.DR, 1.SR, 3.SR),
        Branch("LABEL_2"),
        Move.of(3.DR, 15.I),
        Move.of(4.DR, 1.I),
        Label("LABEL_2"),
        Move.of(5.DR, 2.I),
    ),
    emptyList()
)

val test4Data = Pair(
    listOf(
        Move.of(1.DR, 5.I),
        Move.of(2.DR, 10.I),
        Load.of(3.DR, "A"),
        Load.of(4.DR, "BCD"),
        Load.of(5.DR, 3.SR),
        Load.of(6.DR, 4.SR),
        Add.of(7.DR, 1.SR, 5.SR),
        Sub.of(8.DR, 2.SR, 6.SR),
        Store.of(7.SR, 3.DR),
        Store.of(8.SR, "BCD"),
    ),
    listOf(
        ParsedData("A", 10),
        ParsedData("BCD", 15)
    )
)

// TODO: ADD TESTS WITH SHIFT OPESRTIONS
val test5Data = Pair<List<Instruction>, List<ParsedData>>(emptyList(), emptyList())
