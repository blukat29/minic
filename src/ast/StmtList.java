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
  public String toString() {
    String s = "";
    for (Stmt stmt : stmtList)
      s += stmt.toString();
    return s;
  }
}
