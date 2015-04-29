package ast;
public class Assign extends Node {
  private String id;
  private Expr idx;
  private Expr val;

  public Assign(String id, Expr val) {
    this(id, null, val);
  }
  public Assign(String id, Expr idx, Expr val) {
    this.id = id;
    this.idx = idx;
    this.val = val;
  }

  public void dumpAST(int indent) {
    if (idx == null) {
      ASTWriter.write(id, indent);
      ASTWriter.write(" = ");
      val.dumpAST(0);
    }
    else {
      ASTWriter.write(id, indent);
      ASTWriter.write("[");
      idx.dumpAST(0);
      ASTWriter.write("] = ");
      val.dumpAST(0);
    }
  }
}
