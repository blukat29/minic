package ast;
import java.util.*;
public class FuncList extends Node {
  private List<Function> funcList;
  public FuncList() {
    funcList = new ArrayList<Function>();
  }
  public FuncList append(Function decl) {
    funcList.add(decl);
    return this;
  }
  public String toString() {
    String s = "FuncList[";
    for (Function func : funcList)
      s += "\n  " + func;
    s += "\n]";
    return s;
  }
}
