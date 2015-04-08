package ast;
public class Assign extends Node {
  private IdExpr id;
  private Expr idx;
  private Expr val;
  public Assign(IdExpr id, Expr val) {
    this(id, null, val);
  }
  public Assign(IdExpr id, Expr idx, Expr val) {
    this.id = id;
    this.idx = idx;
    this.val = val;
  }
  public String toString() {
    if (idx != null)
      return id + "[" + idx + "] = " + val;
    else
      return id + " = " + val;
  }
}
