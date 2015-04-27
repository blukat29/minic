package ast;
import java.util.*;
public class DeclList extends Node {
  private List<Declaration> declList;
  public DeclList() {
    declList = new ArrayList<Declaration>();
  }
  public DeclList append(Declaration decl) {
    declList.add(decl);
    return this;
  }
  public String toString() {
    String s = "";
    for (Declaration decl : declList)
      s += decl.toString();
    return s;
  }
}
