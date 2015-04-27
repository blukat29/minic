package symbol;
import ast.*;
import java.util.*;

class ScopeLevel {
  private static int nextId = 1;
  private String name;
  private int id;

  ScopeLevel(Function func) {
    this.name = "Function:" + func.getName();
    this.id = nextId ++;
  }
  ScopeLevel(CompoundStmt cs) {
    this.name = "Block";
    this.id = nextId ++;
  }
  public String toString() {
    return String.format("%s(%d)", name, id);
  }
}

public class Scope {
  Stack<ScopeLevel> stack;

  public Scope() {
    stack = new Stack<ScopeLevel>();
  }

  public void push(Function func) {
    stack.push(new ScopeLevel(func));
  }
  public void push(CompoundStmt cs) {
    stack.push(new ScopeLevel(cs));
  }

  public void pop() {
    stack.pop();
  }

  @SuppressWarnings("unchecked")
  public Scope clone() {
    Scope n = new Scope();
    n.stack = (Stack<ScopeLevel>)this.stack.clone();
    return n;
  }

  public String toString() {
    if (stack.empty())
      return "global";
    else {
      String s = "";
      for (ScopeLevel level : stack) {
        s += level + " - ";
      }
      return s;
    }
  }
}

