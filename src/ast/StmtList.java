package ast;
import java.util.*;
import symbol.*;

public class StmtList extends Node {
  protected boolean hasReturned = false;
  private List<Stmt> stmtList;

  public StmtList() {
    stmtList = new ArrayList<Stmt>();
  }
  public StmtList append(Stmt stmt) {
    stmtList.add(stmt);
    return this;
  }

  public void dumpAST(int n) {
    indent(n); tree("StmtList\n");
    for (Stmt stmt : stmtList)
      stmt.dumpAST(n+1);
  }

  public void analyse(Scope scope) {
    for (Stmt stmt : stmtList) {
      stmt.analyse(scope);
      if (stmt.hasReturned)
        this.hasReturned = true;
    }
  }
}
