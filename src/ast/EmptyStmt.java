package ast;
public class EmptyStmt extends Stmt {
  public EmptyStmt() {
  }
  public void dumpAST(int indent) {
    ASTWriter.write(";\n", indent);
  }
}
