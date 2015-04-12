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

### How to use
`make install` to install libraries.
`make` to build.
`make run < tests/inputs/sample_avg.c` to compile specific file.
`make test` to run predefined tests.
`make clean` to clear build directory.
`cd utils && python view.py` to view generated parse tree.

### Depends on
- JFlex
- CUP

