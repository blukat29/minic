package ast;
import java.util.*;
import symbol.*;

public class StmtList extends Node {
  private List<Stmt> stmtList;

  public StmtList() {
    stmtList = new ArrayList<Stmt>();
  }
  public StmtList append(Stmt stmt) {
    stmtList.add(stmt);
    return this;
  }

  public void dumpAST(int n) {
    for (Stmt stmt : stmtList)
      stmt.dumpAST(n);
  }

  public void compile(Scope scope) {
    for (Stmt stmt : stmtList)
      stmt.compile(scope);
  }
}
