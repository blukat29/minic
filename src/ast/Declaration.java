package ast;
import java.util.*;
import symbol.*;

public class Declaration extends Node {
  private TypeInfo ty;
  private List<Identifier> identList;

  public Declaration(TypeInfo ty, List<Identifier> il) {
    this.ty = ty;
    this.identList = il;
  }

  public void dumpAST(int indent) {
    ty.dumpAST(indent);

    identList.get(0).dumpAST(indent);
    for (int i=1; i<identList.size(); i++) {
      ASTWriter.write(", ");
      identList.get(i).dumpAST(indent);
    }

    ASTWriter.write(";\n");
  }

  public void compile(Scope scope) {
    SymbolTable table = SymbolTable.getInstance();
    for (Identifier id : identList) {
      table.addSymbol(id.toSymbol(scope, ty));
    }
  }
}
