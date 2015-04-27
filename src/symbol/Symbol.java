package symbol;
import ast.*;
public class Symbol {
  private TypeInfo ty;
  private String name;
  private boolean isArray;
  private int arraySize;

  public Symbol(TypeInfo ty, String name) {
    this.ty = ty;
    this.name = name;
    this.isArray = false;
  }

  public Symbol(TypeInfo ty, String name, int size) {
    this.ty = ty;
    this.name = name;
    this.isArray = true;
    this.arraySize = size;
  }

  public String toString() {
    if (!isArray)
      return String.format("Symbol(%s %s)", ty, name);
    else
      return String.format("Symbol(%s %s[%d])", ty, name, arraySize);
  }
}
