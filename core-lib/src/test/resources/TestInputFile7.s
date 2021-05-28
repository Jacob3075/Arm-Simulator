.TEXT
    MOV R1, #3
    MOV R2, #7
    MOV R3, #5
    MOV R4, #13
    MOV R5, #17

    MOV R6, #4
    MOV R7, #5
    MOV R8, #6
    MOV R9, #7
    MOV R10, #8

    STR R1, [R6, #3]
    STR R2, [R7, #7]!
    STR R3, [R8, #18]
    STR R4, [R9], #-3
    STR R5, [R10], #5
    STR R3, [R6, #13]!
    STR R2, [R7, #-5]!
.END
