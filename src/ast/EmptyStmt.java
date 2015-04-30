package ast;
import symbol.*;

public class EmptyStmt extends Stmt {
  public EmptyStmt() {
  }
  public void dumpAST(int n) {
    indent(n); tree(";\n");
  }
  public void compile(Scope scope) {
  }
}
