.data 
n:
.word 3
.word -1
.word 3
.word -3
.word 9 
.word 6
.word 0
.word 0
.word 10
y:
.word -1 # random initial value

.text
# Initialize n 
# n in t1
#addi t1, x0, 3
lui     t0,%hi(n)
addi    t0, t0,%lo(n)
lw t1, 0(t0)
# Initialize sum in t3 = 0
add t3, x0, x0
#Exit if negative
bltz t1, EXIT
#backup t1 into t4, will be required later for division
add t4, t1, x0
addi t2, x0, 1
sll t2, t2, t1 
#Now t2 has # of entries to add
add t1, t2, x0
#Now t1 has # of entries to add
#load data into t2 sum is already in t3

# Now sum in t3 = 0
# Goto next number to load
NEXT:
addi t0, t0, 4
#LOad the number into t2
lw t2, 0(t0)
# Add t2 to sum in t3
add t3, t3, t2
# Dec t1
addi t1, t1, -1
#check and repeat
bgtz t1, NEXT

# Now sum is done, divide by shift right arithmetic based on saved t4
sra t3, t3, t4

#Now store
addi t0, t0, 4
#LOad the number into t2
sw t3, 0(t0)
EXIT :


