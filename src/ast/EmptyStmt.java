package ast;
public class EmptyStmt extends Stmt {
  public EmptyStmt() {
  }
  public String toString() {
    return "nop();";
  }
}
