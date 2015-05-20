package ast;
import java.util.*;
import symbol.*;

public class CallExpr extends Expr {
  private String id;
  private List<Expr> args;

  public CallExpr(Pos pos, String id) {
    this(pos, id, null);
  }
  public CallExpr(Pos pos, String id, List<Expr> args) {
    super(pos);
    this.id = id;
    this.args = args;
  }

  public void dumpAST(int n) {
    indent(n); tree(id + "(");
    if (args != null) {
      args.get(0).dumpAST(0);
      for (int i=1; i<args.size(); i++) {
        tree(", "); args.get(i).dumpAST(0);
      }
    }
    tree(")");
  }

  public void compile(Scope scope) {
    Function func = SymbolTable.lookupFunction(id);
    if (func == null) {
      error(String.format("function '%s' is not defined.", id));
      return;
    }
    this.ty = func.getRetTy();
  }
}
