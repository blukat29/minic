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

  public void dumpAST(int indent) {
    if (idx == null) {
      id.dumpAST(indent);
      ASTWriter.write(" = ");
      val.dumpAST(0);
    }
    else {
      id.dumpAST(indent);
      ASTWriter.write("[");
      idx.dumpAST(0);
      ASTWriter.write("] = ");
      val.dumpAST(0);
    }
  }
}
