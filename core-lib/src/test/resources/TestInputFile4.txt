.DATA
    A: .WORD 10
    BCD: .WORD 15
.TEXT
    MOV R1, #5
    MOV R2, #10
    LDR R3, =A
    LDR R4, =BCD
    LDR R5, [R3]
    LDR R6, [R4]
    ADD R7, R1, R5
    SUB R8, R2, R6
    STR R7, [R3]
    STR R8, =BCD
.END
