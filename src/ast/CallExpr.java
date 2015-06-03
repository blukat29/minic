package ast;
import java.util.*;
import symbol.*;

public class CallExpr extends Expr {
  private String id;
  private List<Expr> args;
  private Function func;
  private static int nextLabel = 0;

  public CallExpr(Pos pos, String id) {
    this(pos, id, null);
  }
  public CallExpr(Pos pos, String id, List<Expr> args) {
    super(pos);
    this.id = id;
    this.args = args;
  }

  public void dumpAST(int n) {
    indent(n); tree("Call " + id + "\n");
    if (args != null) {
      for (Expr arg : args)
        arg.dumpAST(n+1);
    }
  }

  public void analyse(Scope scope) {
    this.func = SymbolTable.lookupFunction(id);
    if (func == null) {
      error(String.format("function '%s' is not defined.", id));
      return;
    }

    ParamList params = func.getParams();

    int argCount, paramCount;
    if (args == null) argCount = 0;
    else argCount = args.size();
    if (params == null) paramCount = 0;
    else paramCount = params.size();

    if (argCount != paramCount) {
      error(String.format("Argument count mismatch - %d expected, %d given.", paramCount, argCount), this);
      return;
    }

    if (args != null) {
      for (Expr expr : args) {
        expr.analyse(scope);
      }

      List<TypeInfo> tyList = params.getTyList();
      List<Identifier> idList = params.getIdList();
      for (int i=0; i<tyList.size(); i++) {
        Expr expr = args.get(i);
        TypeInfo paramTy = tyList.get(i);
        Identifier paramId = idList.get(i);
        if (expr.ty == null) return;     /* There must be an error. Stop compiling. */
        if (expr.isArray != paramId.isArray()) {
          error("Incompatible argument", expr);
          return;
        }
        if (!expr.ty.equals(paramTy)) {
          warn(String.format("Implicitly casting %s to %s", expr.ty, paramTy), expr);
          expr = new TypeCast(paramTy, expr);
          expr.analyse(scope);
        }
      }
    }
    this.ty = func.getRetTy();
  }

  public void codegen() {
    code("// CallExpr");
    if (args != null) {
      for (Expr expr : args) {
        expr.codegen();
        code(String.format("MOVE VR(%d)@ MEM(SP@)", expr.reg));
        code("ADD SP@ 1 SP");
      }
    }

    String label = "call_" + (++nextLabel);
    code(String.format("MOVE %s MEM(SP@)", label));
    code("ADD SP@ 1 SP");
    code("JMP function_" + func.getName());
    code("LAB " + label);
    if (args != null) {
      code(String.format("SUB SP@ %d SP", args.size()));
    }
  }
}
