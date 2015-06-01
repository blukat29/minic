package ast;
import symbol.*;

public class UnOpExpr extends Expr {
  private String op;
  private Expr r;

  public UnOpExpr(String op, Expr r) {
    this.op = op;
    this.r = r;
  }

  public void dumpAST(int n) {
    indent(n); tree("UnOpExpr(" + op + ")\n");
    r.dumpAST(n+1);
  }

  public void analyse(Scope scope) {
    r.analyse(scope);
    if (r.isArray) {
      error("Cannot operate on array", r);
      return;
    }
    this.ty = r.ty;
  }

  public void codegen() {
    code("// UnOpExpr");
  }
}
