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
    indent(n); tree("switch ( "); value.dumpAST(0); tree(" )\n");
    indent(n); tree("{\n");
    caseList.dumpAST(n + 1);
    indent(n); tree("}\n");
  }

  public void compile(Scope scope) {
    value.compile(scope);
    if (value.isArray) {
      error("An array cannot be value", value);
      return;
    }
    TypeInfo intTy = new TypeInfo(TypeInfo.INT);
    if (!value.ty.equals(intTy)) {
      warn("Implicitly casting float to int", value);
      value = new TypeCast(intTy, value);
      value.compile(scope);
    }
    caseList.compile(scope);
  }
}
