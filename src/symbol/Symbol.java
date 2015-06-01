package symbol;
import ast.*;
public class Symbol {
  private Scope scope;
  private TypeInfo ty;
  private String name;
  private boolean isParam;
  private boolean isArray;
  private int arraySize;
  private int offset; /* Offset in global memory or local stack */

  public Symbol(Scope scope, TypeInfo ty, String name, boolean isParam) {
    this(scope, ty, name, isParam, false, 0);
  }

  public Symbol(Scope scope, TypeInfo ty, String name, boolean isParam, int size) {
    this(scope, ty, name, isParam, true, size);
  }

  private Symbol(Scope scope, TypeInfo ty, String name, boolean isParam, boolean isArray, int arraySize) {
    this.scope = scope.clone();
    this.ty = ty;
    this.name = name;
    this.isParam = isParam;
    this.isArray = isArray;
    this.arraySize = arraySize;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public Scope getScope() {
    return scope;
  }

  public TypeInfo getType() {
    return ty;
  }

  public String getName() {
    return name;
  }

  public boolean isArray() {
    return isArray;
  }

  public String toString() {
    String varClass = isParam? "param" : " var ";
    if (!isArray)
      return String.format("%s%s %s %s @%d", scope, varClass, ty, name, offset);
    else
      return String.format("%s%s %s %s[%d] @%d", scope, varClass, ty, name, arraySize, offset);
  }
}
