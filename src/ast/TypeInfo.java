package ast;
public class TypeInfo extends Node {
  public static final int INT = 1;
  public static final int FLOAT = 2;
  private int ty;

  public TypeInfo(int ty) {
    this.ty = ty;
  }

  public void dumpAST(int indent) {
    if (ty == INT)
      ASTWriter.write("int ", indent);
    else if (ty == FLOAT)
      ASTWriter.write("float ", indent);
  }

  public String toString() {
    if (ty == INT)
      return "int";
    else if (ty == FLOAT)
      return "float";
    return "";
  }
}
