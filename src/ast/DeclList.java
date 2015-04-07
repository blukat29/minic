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
    return "DeclList of " + declList.size() + " decls.";
  }
}
