package ast;
import java.util.*;
public class IdentList extends Node{
  private List<Identifier> idList;
  public IdentList() {
    idList = new ArrayList<Identifier>();
  }
  public IdentList append(Identifier id) {
    idList.add(id);
    return this;
  }
  public String toString() {
    String s = "IdentList[";
    for (Identifier id : idList)
      s += id + " , ";
    return s + "]";
  }
}
