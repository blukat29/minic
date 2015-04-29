package ast;
import symbol.*;

public class EmptyStmt extends Stmt {
  public EmptyStmt() {
  }
  public void dumpAST(int indent) {
    ASTWriter.write(";\n", indent);
  }
  public void compile(Scope scope) {
  }
}
