package ast;
import symbol.*;

public class BinOpExpr extends Expr {
  public static String[] compareOps = {"<", ">", "<=", ">=", "==", "!="};
  public static String[] arithOps = {"+", "-", "*", "/"};
  private Expr l;
  String op;
  private Expr r;

  public BinOpExpr(Expr l, String op, Expr r) {
    this.l = l;
    this.op = op;
    this.r = r;
  }

  public void dumpAST(int n) {
    tree("("); l.dumpAST(n); tree(" " + op + " "); r.dumpAST(0); tree(")");
  }

  private boolean isCompare() {
    for (String x : compareOps) {
      if (x.equals(op))
        return true;
    }
    return false;
  }
  public void compile(Scope scope) {
    l.compile(scope);
    r.compile(scope);

    /* Cast to float if operands' types are different. */
    TypeInfo intTy = new TypeInfo(TypeInfo.INT);
    TypeInfo floatTy = new TypeInfo(TypeInfo.FLOAT);
    if (l.ty.equals(r.ty)) {
      this.ty = l.ty;
    }
    else {
      if (l.ty.equals(intTy))
        l = new TypeCast(floatTy, l);
      else
        r = new TypeCast(floatTy, r);
      this.ty = floatTy;
    }
    if (isCompare())
      this.ty = intTy;
  }
}

