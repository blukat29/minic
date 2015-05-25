Mini-C compiler
===============

KAIST CS420 Compiler Design 2015 Spring
20130598 Jeong Yunjong

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

### Semantic checks
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

