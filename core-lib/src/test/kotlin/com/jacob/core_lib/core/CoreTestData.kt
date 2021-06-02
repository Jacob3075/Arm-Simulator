package com.jacob.core_lib.core

import com.jacob.core_lib.common.*
import com.jacob.core_lib.instructions.Branch
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.instructions.Label
import com.jacob.core_lib.instructions.OffsetTypes.*
import com.jacob.core_lib.instructions.add.Add
import com.jacob.core_lib.instructions.comapare.Compare
import com.jacob.core_lib.instructions.conditionals.Conditionals.EQ
import com.jacob.core_lib.instructions.load.Load
import com.jacob.core_lib.instructions.move.Move
import com.jacob.core_lib.instructions.multipy.Multiply
import com.jacob.core_lib.instructions.store.Store
import com.jacob.core_lib.instructions.sub.Sub

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
        Branch.of("LABEL_1"),
        Add.of(3.DR, 1.SR, 2.I),
        Add.of(4.DR, 1.SR, 2.SR),
        Label("LABEL_1"),
        Sub.of(4.DR, 1.SR, 3.SR),
        Branch.of("LABEL_2"),
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

val test5Data = Pair(
    listOf(
        Load.of(1.DR, "A"),
        Load.of(2.DR, "B"),
        Load.of(3.DR, "C"),
        Load.of(4.DR, "D"),

        Load.of(5.DR, 1.SR),
        Load.of(6.DR, 2.SR),
        Load.of(7.DR, 3.SR),
        Load.of(8.DR, 4.SR),

        Add.of(5.DR, 5.SR, 5.SR, 1.LS),
        Add.of(6.DR, 6.SR, 6.SR, 4.RS),
        Sub.of(7.DR, 7.SR, 7.SR, 1.LS),
        Sub.of(8.DR, 8.SR, 8.SR, 4.RS),

        Store.of(5.SR, 1.DR),
        Store.of(6.SR, 2.DR),
        Store.of(7.SR, 3.DR),
        Store.of(8.SR, 4.DR),
    ),
    listOf(
        ParsedData("A", 10),
        ParsedData("B", 20),
        ParsedData("C", 30),
        ParsedData("D", 40),
    )
)

val test6Data = Pair(
    listOf(
        Move.of(0.DR, 3.I),
        Move.of(1.DR, (-8).I),
        Move.of(2.DR, 11.I),

        Load.of(3.DR, "A"),
        Load.of(4.DR, "B"),

        Load.of(5.DR, 3.SR, 1.I, IMMEDIATE),
        Load.of(6.DR, 4.SR, (-1).I, IMMEDIATE),
        Load.of(7.DR, 0.SR, (-3).I, PRE),
        Load.of(8.DR, 1.SR, 9.I, PRE),
        Load.of(9.DR, 2.SR, (-10).I, POST),
    ),
    listOf(
        ParsedData("A", 10),
        ParsedData("B", 20),
    )
)

val test7Data = Pair<List<Instruction>, List<ParsedData>>(
    listOf(
        Move.of(1.DR, 3.I),
        Move.of(2.DR, 7.I),
        Move.of(3.DR, 5.I),
        Move.of(4.DR, 13.I),
        Move.of(5.DR, 17.I),

        Move.of(6.DR, 4.I),
        Move.of(7.DR, 5.I),
        Move.of(8.DR, 6.I),
        Move.of(9.DR, 7.I),
        Move.of(10.DR, 8.I),

        Store.of(1.SR, 6.DR, 3.I, IMMEDIATE),
        Store.of(2.SR, 7.DR, 7.I, PRE),
        Store.of(3.SR, 8.DR, 18.I, IMMEDIATE),
        Store.of(4.SR, 9.DR, (-3).I, POST),
        Store.of(5.SR, 10.DR, 5.I, POST),
        Store.of(3.SR, 6.DR, 13.I, PRE),
        Store.of(2.SR, 7.DR, (-5).I, PRE),
    ),
    emptyList()
)

val test8Data = Pair<List<Instruction>, List<ParsedData>>(
    listOf(
        Move.of(0.DR, 5.I),
        Move.of(1.DR, 5.I),
        Move.of(2.DR, 10.I),
        Move.of(3.DR, (-5).I),

        Compare.of(0.SR, 1.SR),
        Add.of(4.DR, 0.SR, 1.SR, conditional = EQ),

        Compare.of(1.SR, 2.SR),
        Add.of(5.DR, 1.SR, 2.SR, conditional = EQ),

        Compare.of(1.SR, 3.SR),
        Sub.of(6.DR, 1.SR, 3.SR, conditional = EQ),

        Compare.of(1.SR, 0.SR),
        Branch.of("LABEL", conditional = EQ),
        Add.of(7.DR, 1.SR, 0.SR),
        Label("LABEL"),
        Multiply.of(8.DR, 1.SR, 0.SR),
    ),
    emptyList()
)
