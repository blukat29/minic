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
    body.codegen();
    if (destTy.equals(new TypeInfo(TypeInfo.INT)))
      code(String.format("F2I VR(%d)@ VR(%d)", body.reg, body.reg));
    else
      code(String.format("I2F VR(%d)@ VR(%d)", body.reg, body.reg));
    this.reg = body.reg;
  }
}
