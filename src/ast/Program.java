package ast;
public class Program extends Node {
  private DeclList declList;
  public Program() {
    this(null);
  }
  public Program(DeclList declList) {
    this.declList = declList;
  }
  public String toString() {
    return "The Program(" + declList + ")";
  }
}
