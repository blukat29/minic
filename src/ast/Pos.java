package ast;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class Pos {
  private static SourceManager sm = null;
  public Location left;
  public Location right;

  public Pos() {
    this.left = new Location("?", 0, 0, 0);
    this.right = new Location("?", 0, 0, 0);
  }
  public Pos(Location left, Location right) {
    this.left = left;
    this.right = right;
  }

  public static void setSourceManager(SourceManager sm_) {
    sm = sm_;
  }

  public void showSource() {
    sm.showPart(left.getLine(), left.getColumn());
  }

  public String toString() {
    return String.format("%d:%d", left.getLine(), left.getColumn());
  }
}
