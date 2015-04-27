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
  public String toString() {
    String s = "";
    if(declList != null)
      s += declList + "\n";
    if(funcList != null)
      s += funcList;
    return s;
  }

  public void compile() {
    Scope scope = new Scope();
    if (declList != null)
      declList.compile(scope);
  }
}
