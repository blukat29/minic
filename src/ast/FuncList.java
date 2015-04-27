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
  public void dumpAST(int indent) {
    ASTWriter.write("FuncList", indent);
  }

  public void compile(Scope scope) {
    for (Function func : funcList)
      func.compile(scope);
  }
}
