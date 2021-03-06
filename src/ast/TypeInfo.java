package ast;
public class TypeInfo extends Node {
  public static final int INT = 1;
  public static final int FLOAT = 2;
  public static final int ANY = 3;
  private int ty;

  public TypeInfo(int ty) {
    this.ty = ty;
  }

  public void dumpAST(int n) {
    indent(n);
    if (ty == INT)
      tree("int ");
    else if (ty == FLOAT)
      tree("float ");
  }

  public String toString() {
    if (ty == INT)
      return "int";
    else if (ty == FLOAT)
      return "float";
    return "";
  }

  public boolean equals(TypeInfo other) {
    if (this.ty == ANY || other.ty == ANY)
      return true;
    else
      return this.ty == other.ty;
  }
}
