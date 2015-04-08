package ast;
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
    return "The Program(" + declList + "\n" + funcList + ")";
  }
}
