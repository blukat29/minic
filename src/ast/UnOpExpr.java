package ast;
import symbol.*;

public class UnOpExpr extends Expr {
  private String op;
  private Expr r;
  private boolean isFloat;

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
    this.isFloat = r.ty.equals(new TypeInfo(TypeInfo.FLOAT));
    this.ty = r.ty;
  }

  public void codegen() {
    r.codegen();

    String prefix = (isFloat)? "F" : "";
    String zero = (isFloat)? "0.0" : "0";

    if (op.equals("-"))
      code(String.format("%sSUB %s VR(%d)@ VR(%d)", prefix, zero, r.reg, r.reg));

    this.reg = r.reg;
  }
}
