package ast;
import symbol.*;
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
  public void dumpAST(int n) {
    indent(n); tree("DeclList\n");
    for (Declaration decl : declList)
      decl.dumpAST(n+1);
  }

  public void compile(Scope scope) {
    for (Declaration decl : declList)
      decl.compile(scope);
  }
}
