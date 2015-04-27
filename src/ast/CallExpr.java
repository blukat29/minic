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
      args.get(0).dumpAST(0);
      for (int i=1; i<args.size(); i++) {
        ASTWriter.write(", ");
        args.get(i).dumpAST(0);
      }
    }
    ASTWriter.write(")");
  }
}
