package ast;
import java.util.*;
public class StmtList extends Node {
  private List<Stmt> stmtList;

  public StmtList() {
    stmtList = new ArrayList<Stmt>();
  }
  public StmtList append(Stmt stmt) {
    stmtList.add(stmt);
    return this;
  }

  public void dumpAST(int indent) {
    for (Stmt stmt : stmtList)
      stmt.dumpAST(indent);
  }
}
