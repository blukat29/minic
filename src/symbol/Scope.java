package symbol;
import ast.*;
import java.util.*;

public class Scope {
  Stack<Node> stack;
  public Scope() {
    stack = new Stack<Node>();
  }
  public String toString() {
    if (stack.empty())
      return "global";
    else {
      String s = "";
      for (Node node : stack) {
        s += node.getClass() + " - ";
      }
      return s;
    }
  }
}

