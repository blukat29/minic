package symbol;
import ast.*;
public class Symbol {
  private Scope scope;
  private TypeInfo ty;
  private String name;
  private boolean isArray;
  private int arraySize;

  public Symbol(Scope scope, TypeInfo ty, String name) {
    this(scope, ty, name, false, 0);
  }

  public Symbol(Scope scope, TypeInfo ty, String name, int size) {
    this(scope, ty, name, true, size);
  }

  private Symbol(Scope scope, TypeInfo ty, String name, boolean isArray, int arraySize) {
    this.scope = scope.clone();
    this.ty = ty;
    this.name = name;
    this.isArray = isArray;
    this.arraySize = arraySize;
  }

  public Scope getScope() {
    return scope;
  }

  public String getName() {
    return name;
  }

  public String toString() {
    if (!isArray)
      return String.format("Symbol(%s %s %s)", scope, ty, name);
    else
      return String.format("Symbol(%s %s %s[%d])", scope, ty, name, arraySize);
  }
}
