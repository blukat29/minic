package ast;
import java.util.*;
public class CallExpr extends Expr {
  private IdExpr id;
  private List<Expr> args;

  public CallExpr(IdExpr id) {
    this(id, null);
  }
  public CallExpr(IdExpr id, List<Expr> args) {
    this.id = id;
    this.args = args;
  }

  public void dumpAST(int indent) {
    id.dumpAST(indent);
    ASTWriter.write("(");
    if (args != null) {
      ASTWriter.write("args");
    }
    ASTWriter.write(")");
  }

  public String toString() {
    return String.format("call %s(%s)", id, args);
  }
}


