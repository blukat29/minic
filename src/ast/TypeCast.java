package ast;
import symbol.*;

public class TypeCast extends Expr {
  private TypeInfo destTy;
  private Expr body;

  public TypeCast(TypeInfo destTy, Expr body) {
    this.destTy = destTy;
    this.body = body;
  }

  public void dumpAST(int n) {
    indent(n); tree("TypeCast(" + destTy + ")\n");
    body.dumpAST(n+1);
  }

  public void analyse(Scope scope) {
    this.ty = destTy;
  }

  public void codegen() {
    code("// typecast");
  }
}
