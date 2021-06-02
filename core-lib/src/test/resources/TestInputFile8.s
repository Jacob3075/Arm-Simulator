.TEXT
    MOV R0, #5
    MOV R1, #5
    MOV R2, #10
    MOV R3, #-5

    CMP R0, R1
    ADDEQ R4, R0, R1

    CMP R1, R2
    ADDEQ R5, R1, R2

    CMP R1, R3
    SUBEQ R6, R1, R3

    CMP R1, R0
    BEQ LABEL
    ADD R7, R1, R0
    LABEL:
    MUL R8, R1, R0
.END
