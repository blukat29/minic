package ast;
import symbol.*;

public class Program extends Node {
  private DeclList declList;
  private FuncList funcList;
  private Scope scope;
  private static String printf_minic = "" +
    "LAB function_printf\n" +
    "WRITE MEM(SP@(-2))@\n" +
    "MOVE 0 VR(0)\n" +
    "SUB SP@ 1 SP\n" +
    "JMP MEM(SP@)@\n";
  private static String readi_minic = "" +
    "LAB function_readi_minic\n" +
    "READI VR(0)\n" +
    "SUB SP@ 1 SP\n" +
    "JMP MEM(SP@)@\n";
  private static String readf_minic = "" +
    "LAB function_readf_minic\n" +
    "READF VR(0)\n" +
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

    ParamList params = (new ParamList()).append(
        new TypeInfo(TypeInfo.ANY),
        new Identifier(new Pos(), "var"));
    TypeInfo intTy = new TypeInfo(TypeInfo.INT);
    TypeInfo floatTy = new TypeInfo(TypeInfo.FLOAT);

    Function printf = new Function(new Pos(), intTy, "printf", params, null);
    Function scanf = new Function(new Pos(), intTy, "scanf", params, null);
    Function readi = new Function(new Pos(), intTy, "readi_minic", null);
    Function readf = new Function(new Pos(), floatTy, "readf_minic", null);
    SymbolTable.addFunction(printf);
    SymbolTable.addFunction(scanf);
    SymbolTable.addFunction(readi);
    SymbolTable.addFunction(readf);

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

    code("LAB START");

    /* Set initial SP and FP. */
    code(String.format("MOVE %d SP", this.scope.nextGlobalOffset(0) + 1));
    code("MOVE 0 FP");

    /* Push final return address. */
    code("MOVE EXIT MEM(SP@)");
    code("ADD SP@ 1 SP");
    code("JMP function_main");

    if (funcList != null)
      funcList.codegen();
    code(printf_minic);
    code(readi_minic);
    code(readf_minic);

    code("\nLAB EXIT");
    code("WRITE VR(0)@");
    code("LAB END");
  }
}
