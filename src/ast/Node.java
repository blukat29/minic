package ast;
abstract public class Node {
  private int row;
  private int column;
  public abstract void dumpAST(int indent);
}
