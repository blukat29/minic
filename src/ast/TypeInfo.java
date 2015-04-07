package ast;
public class TypeInfo extends Node {
  public static final int INT = 1;
  public static final int FLOAT = 2;
  private int ty;
  public TypeInfo(int ty) {
    this.ty = ty;
  }
  public String toString() {
    if (this.ty == INT)
      return "typeinfo:INT";
    else if (this.ty == FLOAT)
      return "typeinfo:FLOAT";
    else
      return "typeinfo:UNKNOWN";
  }
}
