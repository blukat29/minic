package ast;
import java.util.*;
public class ParamList extends Node {
  private List<TypeInfo> tyList;
  private List<Identifier> idList;
  public ParamList() {
    tyList = new ArrayList<TypeInfo>();
    idList = new ArrayList<Identifier>();
  }
  public ParamList append(TypeInfo ty, Identifier id) {
    tyList.add(ty);
    idList.add(id);
    return this;
  }
  public String toString() {
    String s = "Params[";
    for (int i=0; i<tyList.size(); i++) {
      s += tyList.get(i) + " " + idList.get(i) + ",";
    }
    s += "]";
    return s;
  }
}
