package ast;
import symbol.*;

public class SwitchStmt extends Stmt {
  private Expr value;
  private CaseList caseList;

  public SwitchStmt(Expr value, CaseList caseList) {
    this.value = value;
    this.caseList = caseList;
  }

  public void dumpAST(int n) {
    indent(n); tree("SwitchStmt\n");
    value.dumpAST(n+1);
    caseList.dumpAST(n+1);
  }

  public void analyse(Scope scope) {
    value.analyse(scope);
    if (value.ty == null) return;
    if (value.isArray) {
      error("An array cannot be value", value);
      return;
    }
    TypeInfo intTy = new TypeInfo(TypeInfo.INT);
    if (!value.ty.equals(intTy)) {
      warn("Implicitly casting float to int", value);
      value = new TypeCast(intTy, value);
      value.analyse(scope);
    }
    caseList.analyse(scope);
  }

  public void codegen() {
    code("// ------ SwitchStmt");
    value.codegen();
    caseList.codegen(value.reg);
  }
}
