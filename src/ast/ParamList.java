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
  public void dumpAST(int indent) {
    ASTWriter.write("(");
    if (tyList.size() > 0) {
      tyList.get(0).dumpAST(0);
      idList.get(0).dumpAST(0);
    }
    for (int i=1; i<tyList.size(); i++) {
      ASTWriter.write(", ");
      tyList.get(i).dumpAST(0);
      idList.get(i).dumpAST(0);
    }
    ASTWriter.write(")");
  }
}
