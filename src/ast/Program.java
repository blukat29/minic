package ast;
import symbol.*;

public class Program extends Node {
  private DeclList declList;
  private FuncList funcList;
  private Scope scope;
  private static String library = "" +
    "LAB function_printf\n" +
    "WRITE MEM(SP@(-2))@\n" +
    "MOVE 0 VR(0)\n" +
    "SUB SP@ 1 SP\n" +
    "JMP MEM(SP@)@\n";

  public Program() {
    this(null, null);
  }
  public Program(DeclList declList) {
    this(declList, null);
  }
  public Program(FuncList funcList) {
    this(null, funcList);
  }
  public Program(DeclList declList, FuncList funcList) {
    this.declList = declList;
    this.funcList = funcList;
  }

  public void dumpAST(int n) {
    if (declList != null)
      declList.dumpAST(n);
    if (funcList != null)
      funcList.dumpAST(n);
  }

  public void analyse() {
    this.scope = new Scope();

    ParamList printfParams = (new ParamList()).append(
        new TypeInfo(TypeInfo.ANY),
        new Identifier(new Pos(), "printf_var"));
    Function printf = new Function(
        new Pos(),
        new TypeInfo(TypeInfo.INT),
        "printf",
        printfParams,
        null);
    SymbolTable.addFunction(printf);

    if (declList != null)
      declList.analyse(this.scope);
    if (funcList != null)
      funcList.analyse(this.scope);
  }

  public void codegen() {
    code("AREA SP");
    code("AREA FP");
    code("AREA VR");
    code("AREA MEM\n");

    code(library);

    code("LAB START");

    /* Set initial SP and FP. */
    code(String.format("MOVE %d SP", this.scope.nextGlobalOffset(0) + 1));
    code("MOVE 0 FP");

    /* Push final return address. */
    code("MOVE EXIT MEM(SP@)");
    code("ADD SP@ 1 SP");

    code("WRITE \"Hello world!\"");

    code("JMP function_main");

    funcList.codegen();

    code("\nLAB EXIT");
    code("WRITE \"Bye world!\"");
    code("WRITE VR(0)@");
    code("LAB END");
  }
}
