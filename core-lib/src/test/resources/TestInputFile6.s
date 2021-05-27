.DATA
    A: .WORD 10
    B: .WORD 20
.TEXT
    MOV R0, #3
    MOV R1, #-8
    MOV R2, #11
    LDR R3, =A
    LDR R4, =B
    LDR R5, [R3, #1]
    LDR R6, [R4, #-1]
    LDR R7, [R0, #-3]!
    LDR R8, [R1, #9]!
    LDR R9, [R2], #-10
.END
