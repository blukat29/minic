Mini-C compiler
===============

KAIST CS420 Compiler Design 2015 Spring
20130598 Jeong Yunjong

## How to use

### Requirements
- java
- javac
- make
- wget
- tar

### Setup
```
make install
make
make test
```

### Run

```
./mcc tests/inputs/sample_avg.c
./mcc < input_file.c
```

### Depends on
- JFlex
- CUP

## Semantic checks

These are considered errors:
- Using undeclared variable.
- Declaring variables of the same name in the same scope.
- non-integer array index.
- indexing non-array variable.
- using array without index.
- Function not returning a value.
- Function call with arguments of incompatible types (e.g. int for int[]).

These generate warnings:
- Assigning a value of different type.
- Function call with arguments of convertible types (e.g. float for int).
- Returning a value of different type.
- Arithmetic / comparison operations between expressions of different types.

Note that the result of comparison (>, <, >=, <=, ==, !=) is int type.

## Code generation

This compiler generates assembly code for T-machine.

### Registers and memory

The machine will have SP(stack pointer), FP(frame pointer), VR(virtual registers), MEM(Main memory)
SP always points to the top element of the stack.
FP always points to the part of stack that has old_fp.
There are infinite number of virtual registers.
The main memory has global variables and stack.

```
Mem[0]            initial SP
v                     v
[  Global variables  ][  Runtime stack ...
```

### Call convention

All registers are caller-save. Callers must save their registers for local variables themselves.
Arguments are pushed into stack, from right to left. Then the return address (label) is pushed.
The caller clears all the stack.
Return value is passed via register VR(0).

### Stack structure

The stack is used for passing arguments and storing return addresses and local variables.
Local variable addresses are assigned at semantic analysis time.
The stack grows upward (low address to high address)

```
; Low address
param2         FP-3
param1         FP-2
retn_addr      FP-1
old_fp     <-- FP
local_vars     FP+ofs
saved_regs     stored via push/pop
param3
param2
param1     <-- SP
; High address
```

