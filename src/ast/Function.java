package ast;
import symbol.*;

public class Function extends Node {
  private TypeInfo retTy;
  private String name;
  private ParamList params;
  private CompoundStmt body;
  private int stackSize = 1; /* Initially it has old FP. */

  public Function(Pos pos, TypeInfo retTy, String name, CompoundStmt body) {
    this(pos, retTy, name, null, body);
  }
  public Function(Pos pos, TypeInfo retTy, String name, ParamList params, CompoundStmt body) {
    super(pos);
    this.retTy = retTy;
    this.name = name;
    this.params = params;
    this.body = body;
  }
  public String getName() {
    return name;
  }
  public TypeInfo getRetTy() {
    return retTy;
  }
  public ParamList getParams() {
    return params;
  }

  public int nextStackOffset(int size) {
    int result = stackSize;
    stackSize += size;
    return result;
  }

  public void dumpAST(int n) {
    indent(n); tree("Function " + retTy + " " + name + "\n");
    if (params != null) {
      params.dumpAST(n+1);
    } else {
      indent(n+1);
      tree("(void)\n");
    }
    body.dumpAST(n+1);
  }

  public void analyse(Scope scope) {

    if (SymbolTable.lookupFunction(name) != null) {
      error(String.format("function '%s' is already defined.", name));
      return;
    }
    SymbolTable.addFunction(this);

    scope.push(this);
    if (params != null)
      params.analyse(scope);
    body.analyseFunctionBlock(scope);
    scope.pop();
    if (!body.hasReturned) {
      error(String.format("Function '%s' did not return a value", name), this);
      return;
    }
  }

  public String toString() {
    return name;
  }

  public void codegen() {
    code("\n// function " + name);

    /* Save old FP and change FP. */
    code("MOVE FP@ MEM(SP@)");
    code("MOVE SP@ FP");

    /* Move stack pointer for stack size. */
    code(String.format("ADD SP@ %d SP", this.stackSize + 1));

    resetReg();
    body.codegen();

    /* Clear local variables in the stack. */
    code(String.format("SUB SP@ %d SP", this.stackSize + 1));

    /* Restore old FP. */
    code("MOVE MEM(SP@)@ FP");

    /* Pop return address and jump there. */
    code("SUB SP@ 1 SP");
    code("JMP MEM(SP@)@");
  }
}
