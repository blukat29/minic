package ast;
import java.util.*;
public class ArgList extends Node {
  private List<Expr> funcList;
  public ArgList() {
    funcList = new ArrayList<Expr>();
  }
  public ArgList append(Expr decl) {
    funcList.add(decl);
    return this;
  }
  public String toString() {
    String s = "ArgList[ ";
    for (Expr e : funcList)
      s += e + ",";
    s += "]";
    return s;
  }
}
