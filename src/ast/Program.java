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
  public void dumpAST(int indent) {
    if (declList != null)
      declList.dumpAST(indent);
    if (funcList != null)
      funcList.dumpAST(indent);
  }

  public void compile() {
    Scope scope = new Scope();
    if (declList != null)
      declList.compile(scope);
    if (funcList != null)
      funcList.compile(scope);
  }
}
