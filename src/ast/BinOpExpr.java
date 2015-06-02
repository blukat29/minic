package ast;
import symbol.*;

public class BinOpExpr extends Expr {
  private static String[] compareOps = {"<", ">", "<=", ">=", "==", "!="};
  private static int nextLabel = 0;
  private boolean isFloat = false;
  private Expr l;
  String op;
  private Expr r;

  public BinOpExpr(Expr l, String op, Expr r) {
    this.l = l;
    this.op = op;
    this.r = r;
  }

  public void dumpAST(int n) {
    indent(n); tree("BinOpExpr(" + op + ")\n");
    l.dumpAST(n+1);
    r.dumpAST(n+1);
  }

  private boolean isCompare() {
    for (String x : compareOps) {
      if (x.equals(op))
        return true;
    }
    return false;
  }
  public void analyse(Scope scope) {
    l.analyse(scope);
    r.analyse(scope);
    if (l.ty == null || r.ty == null) return;

    if (l.isArray) {
      error("Cannot operate on array", l);
      return;
    }
    if (r.isArray) {
      error("Cannot operate on array", r);
      return;
    }

    /* Cast to float if operands' types are different. */
    TypeInfo intTy = new TypeInfo(TypeInfo.INT);
    TypeInfo floatTy = new TypeInfo(TypeInfo.FLOAT);
    if (l.ty.equals(r.ty)) {
      this.ty = l.ty;
    }
    else {
      if (l.ty.equals(intTy)) {
        l = new TypeCast(floatTy, l);
        l.analyse(scope);
      }
      else {
        r = new TypeCast(floatTy, r);
        r.analyse(scope);
      }
      this.isFloat = true;
      this.ty = floatTy;
    }
    if (isCompare())
      this.ty = intTy;
  }

  public void codegen() {
    code("// BinOpExpr(" + op + ")");
    l.codegen();
    r.codegen();
    int lreg = l.reg;
    int rreg = r.reg;
    int dst = nextReg();

    String prefix = "";
    if (isFloat) prefix = "F";

    if (op.equals("+"))  /* Arithmetic operations */
      code(String.format("%sADD VR(%d)@ VR(%d)@ VR(%d)", prefix, lreg, rreg, dst));
    else if (op.equals("-"))
      code(String.format("%sSUB VR(%d)@ VR(%d)@ VR(%d)", prefix, lreg, rreg, dst));
    else if (op.equals("*"))
      code(String.format("%sMUL VR(%d)@ VR(%d)@ VR(%d)", prefix, lreg, rreg, dst));
    else if (op.equals("/"))
      code(String.format("%sDIV VR(%d)@ VR(%d)@ VR(%d)", prefix, lreg, rreg, dst));
    else {
      /* Compare operations
       * l > r   (r - l < 0)? 1 : 0
       * l < r   (l - r < 0)? 1 : 0
       * l >= r  (l - r < 0)? 0 : 1
       * l <= r  (r - l < 0)? 0 : 1
       * l != r  (l - r == 0)? 0 : 1
       * l == r  (l - r == 0)? 1 : 0
       */

      if (op.equals(">") || op.equals("<="))
        code(String.format("%sSUB VR(%d)@ VR(%d)@ VR(0)", prefix, rreg, lreg));
      else
        code(String.format("%sSUB VR(%d)@ VR(%d)@ VR(0)", prefix, lreg, rreg));
      if (isFloat)
        code("F2I VR(0)@ VR(0)");

      int labelIdx = ++nextLabel;
      if (op.equals("!=") || op.equals("=="))
        code(String.format("JMPZ VR(0)@ CMP_YES_%d", labelIdx));
      else
        code(String.format("JMPN VR(0)@ CMP_YES_%d", labelIdx));

      if (op.equals(">") || op.equals("<") || op.equals("==")) {
        code(String.format("MOVE 0 VR(%d)", dst));
        code(String.format("JMP CMP_END_%d", labelIdx));
        code(String.format("LAB CMP_YES_%d", labelIdx));
        code(String.format("MOVE 1 VR(%d)", dst));
        code(String.format("LAB CMP_END_%d", labelIdx));
      }
      else {
        code(String.format("MOVE 1 VR(%d)", dst));
        code(String.format("JMP CMP_END_%d", labelIdx));
        code(String.format("LAB CMP_YES_%d", labelIdx));
        code(String.format("MOVE 0 VR(%d)", dst));
        code(String.format("LAB CMP_END_%d", labelIdx));
      }
    }
    this.reg = dst;
  }
}

