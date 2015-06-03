package ast;
import symbol.*;

public class Program extends Node {
  private DeclList declList;
  private FuncList funcList;
  private Scope scope;

  public Program() {
    this(null, null);
  }
  public Program(DeclList declList) {
    this(declList, null);
  }
  public Program(FuncList funcList) {
    this(null, funcList);
  }
  public Program(DeclList declList, FuncList funcList) {
    this.declList = declList;
    this.funcList = funcList;
  }

  public void dumpAST(int n) {
    if (declList != null)
      declList.dumpAST(n);
    if (funcList != null)
      funcList.dumpAST(n);
  }

  public void analyse() {
    this.scope = new Scope();
    if (declList != null)
      declList.analyse(this.scope);
    if (funcList != null)
      funcList.analyse(this.scope);
  }

  public void codegen() {
    code("AREA SP");
    code("AREA FP");
    code("AREA VR");
    code("AREA MEM\n");
    code("LAB START");

    /* Set initial SP and FP. */
    code(String.format("MOVE %d SP", this.scope.nextGlobalOffset(0) + 1));
    code("MOVE 0 FP");

    /* Push final return address. */
    code("MOVE EXIT MEM(SP@)");
    code("ADD SP@ 1 SP");

    code("WRITE \"Hello world!\"");

    code("JMP function_main");

    funcList.codegen();

    code("\nLAB EXIT");
    code("WRITE \"Bye world!\"");
    code("WRITE VR(0)@");
    code("LAB END");
  }
}
