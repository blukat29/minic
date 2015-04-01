package ast;

public class BinOpExp extends Exp {
  Exp left;
  Exp right;
  int op;
  public static int PLUS=0;

  public BinOpExp(Exp l, Exp r, int o) { left = l; right = r; op = o; }

  public String toString() {
    return "BinOpExp(" + left + "," + op + "," + right + ")";
  }
}

