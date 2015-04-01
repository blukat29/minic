package ast;

public class IntExp extends Exp {
  public int value;
  public IntExp(int v) { value = v; }
  public String toString() {
    return "IntExp(" + value + ")";
  }
}

