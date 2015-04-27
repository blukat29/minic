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

    identList.get(0).dumpAST(0);
    for (int i=1; i<identList.size(); i++) {
      ASTWriter.write(", ");
      identList.get(i).dumpAST(0);
    }

    ASTWriter.write(";\n");
  }

  public void compile(Scope scope) {
    SymbolTable table = SymbolTable.getInstance();
    for (Identifier id : identList) {
      Symbol symbol = id.toSymbol(scope, ty);
      if (table.lookup(scope, id.getName()) != null) {
        ErrorWriter.error(String.format("variable '%s' is already declared.", id.getName()));
      }
      else {
        table.addSymbol(symbol);
      }
    }
  }
}
