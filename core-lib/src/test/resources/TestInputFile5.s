.DATA
    A: .WORD 10
    B: .WORD 20
    C: .WORD 30
    D: .WORD 40
.TEXT
    LDR R1, =A
    LDR R2, =B
    LDR R3, =C
    LDR R4, =D
    LDR R5, [R1]
    LDR R6, [R2]
    LDR R7, [R3]
    LDR R8, [R4]
    ADD R5, R5, R5, LSL #1
    ADD R6, R6, R6, LSR #4
    SUB R7, R7, R7, LSL #1
    SUB R8, R8, R8, LSR #4
    STR R5, [R1]
    STR R6, [R2]
    STR R7, [R3]
    STR R8, [R4]
.END
