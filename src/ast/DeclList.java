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
  public void dumpAST(int indent) {
    for (Declaration decl : declList)
      decl.dumpAST(indent);
    ASTWriter.write("\n", indent);
  }

  public void compile(Scope scope) {
    for (Declaration decl : declList)
      decl.compile(scope);
  }
}
