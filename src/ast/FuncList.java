package ast;
import symbol.*;
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

  public void dumpAST(int n) {
    for (Function func : funcList) {
      func.dumpAST(n); tree("\n");
    }
  }

  public void analyse(Scope scope) {
    for (Function func : funcList)
      func.analyse(scope);
  }
}
