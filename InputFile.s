.data
A: .WORD 8
B: .WORD 14
C: .WORD 0
.text
LDR r0, =A
LDR r1, =B
LDR r2, =C
LDR r4, [r0]
LDR r3, [r1]
ADD r5, r3, r4
STR r5, [r2]
.end
