package ast;
import symbol.*;

public class Program extends Node {
  private DeclList declList;
  private FuncList funcList;
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
    Scope scope = new Scope();
    if (declList != null)
      declList.analyse(scope);
    if (funcList != null)
      funcList.analyse(scope);
  }

  public void codegen() {
    code("AREA SP");
    code("AREA FP");
    code("AREA VR");
    code("AREA MEM");
    code("\n\n");
    code("LAB START");

    code("WRITE \"Hello world!\"");

    code("LAB END");
  }
}
