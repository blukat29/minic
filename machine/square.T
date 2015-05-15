AREA  R     // 16 general purpose registers.
// R0: N
// R1: N * N
// R2: m = 1 2 .. N * N
// R3: For comparison.
// R4: row
// R5: column
// R6: next row
// R7: next column
// R8: For 2d-array addressing.
// R9: For matrix element.
// R10: N - 1
// R11~: temporary.

AREA  Mem   // Main memory.
// The square is stored in row-major order from index 0.

LAB   START

      READI R(0)
      MUL   R(0)@ R(0)@ R(1)
      SUB   R(0)@ 1 R(10)

// Initialize the square.
      MOVE  0 R(2)
LAB   ZERO
      MOVE  0 Mem(R(2)@)
      ADD   R(2)@ 1 R(2)
      SUB   R(2)@ R(1)@ R(3)
      JMPN  R(3)@ ZERO

      // m = 1, i = 0, j = N / 2
      MOVE  1 R(2)
      MOVE  0 R(4)
      DIV   R(0)@ 2 R(5)

LAB   LOOP

      // square[i][j] = m
      MUL   R(4)@ R(0)@ R(8)
      ADD   R(5)@ R(8)@ R(8)
      MOVE  R(2)@ Mem(R(8)@)

      // ni = (i - 1) % N
      JMPZ  R(4)@ ROW_ZERO
      SUB   R(4)@ 1 R(6)
      JMP   ROW_DONE
LAB   ROW_ZERO
      MOVE  R(10)@ R(6)
LAB   ROW_DONE

      // nj = (j + 1) % N
      SUB   R(5)@ R(10)@ R(3)
      JMPZ  R(3)@ COL_END
      ADD   R(5)@ 1 R(7)
      JMP   COL_DONE
LAB   COL_END
      MOVE  0 R(7)
LAB   COL_DONE

      // square[ni][nj] == 0?
      MUL   R(6)@ R(0)@ R(8)
      ADD   R(7)@ R(8)@ R(8)
      MOVE  Mem(R(8)@)@ R(9)
      JMPZ  R(9)@ ARR_ZERO

      // if not, i = (i + 1) % N
      SUB   R(4)@ R(10)@ R(3)
      JMPZ  R(3)@ DOWN_ZERO
      ADD   R(4)@ 1 R(4)
      JMP   ARR_DONE
LAB   DOWN_ZERO
      MOVE  0 R(7)
      JMP   ARR_DONE

      // if true, i = ni, j = nj
LAB   ARR_ZERO
      MOVE  R(6)@ R(4)
      MOVE  R(7)@ R(5)

LAB   ARR_DONE

      // while m <= N * N
      SUB   R(2)@ R(1)@ R(3)
      JMPZ  R(3)@ ENDLOOP

      // m ++
      ADD   R(2)@ 1 R(2)
      JMP   LOOP

LAB   ENDLOOP

      MOVE  0 R(2)
      MOVE  R(0)@ R(10)
LAB   PRINT

      // Print empty line every N lines.
      JMPZ  R(10)@ PRINT_LINE
      JMP   PRINT_INCR
LAB   PRINT_LINE
      WRITE ""
      MOVE  R(0)@ R(10)
LAB   PRINT_INCR
      SUB   R(10)@ 1 R(10)

      WRITE Mem(R(2)@)@

      ADD   R(2)@ 1 R(2)
      SUB   R(2)@ R(1)@ R(3)
      JMPN  R(3)@ PRINT

LAB   END

