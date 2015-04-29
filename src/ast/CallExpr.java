package ast;
import java.util.*;
public class CallExpr extends Expr {
  private String id;
  private List<Expr> args;

  public CallExpr(String id) {
    this(id, null);
  }
  public CallExpr(String id, List<Expr> args) {
    this.id = id;
    this.args = args;
  }

  public void dumpAST(int indent) {
    ASTWriter.write(id + "(", indent);
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
