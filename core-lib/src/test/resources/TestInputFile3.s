.TEXT
    MOV R1, #5
    MOV R2, #10
    B Label_1
    ADD R3, R1, #2
    ADD R4, R1, R2
    Label_1:
    SUB R4, R1, R3
    B Label_2
    MOV R3, #15
    MOV R4, #1
    Label_2:
    MOV R5, #2
.END
