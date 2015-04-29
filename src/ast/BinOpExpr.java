package ast;
import symbol.*;

public class BinOpExpr extends Expr {
  private Expr l;
  String op;
  private Expr r;

  public BinOpExpr(Expr l, String op, Expr r) {
    this.l = l;
    this.op = op;
    this.r = r;
  }

  public void dumpAST(int indent) {
    ASTWriter.write("(");
    l.dumpAST(indent);
    ASTWriter.write(" " + op + " ");
    r.dumpAST(0);
    ASTWriter.write(")");
  }

  public void compile(Scope scope) {
    l.compile(scope);
    r.compile(scope);
  }
}

