
# =========== Configurables start ==================

LIBDIR=lib/

# Lex & Yacc files
FLEX_SRC=src/parser/lexer.flex
FLEX_OUT=bin/Lexer.java
JFLEX=$(LIBDIR)/jflex/bin/jflex -d bin/

CUP_SRC=src/parser/parser.cup
CUP_OUT=bin/Parser.java bin/Symbols.java
#CUP_OPT=-locations -interface -parser Parser -symbols Symbols -xmlactions -genericlabels
CUP_OPT=-parser Parser -symbols Symbols -locations
CUP=java -jar $(LIBDIR)/cup/java-cup-11b.jar -destdir bin/

# =========== Configurables end ==================

CLASSPATH=".:$(LIBDIR)/cup/java-cup-11b-runtime.jar:bin/"
CCOPT=
CC=javac $(CCOPT) -cp $(CLASSPATH) -d bin/

AST_SRC=$(shell find src/ast -name "*.java")
SYMBOL_SRC=$(shell find src/symbol -name "*.java")
PARSER_SRC=$(shell find src/parser -name "*.java")
AST_CLS=$(patsubst src/%.java, bin/%.class, $(AST_SRC))
SYMBOL_CLS=$(patsubst src/%.java, bin/%.class, $(SYMBOL_SRC))
PARSER_CLS=$(patsubst src/%.java, bin/%.class, $(PARSER_SRC))

all: bin classes

bin:
	mkdir -p bin

$(FLEX_OUT): $(FLEX_SRC)
	$(JFLEX) $(FLEX_SRC)

$(CUP_OUT): $(CUP_SRC)
	$(CUP) $(CUP_OPT) $(CUP_SRC)

classes: $(PARSER_CLS)

$(AST_CLS) $(SYMBOL_CLS): $(AST_SRC) $(SYMBOL_SRC)
	$(CC) $(AST_SRC) $(SYMBOL_SRC)

$(PARSER_CLS): $(FLEX_OUT) $(CUP_OUT) $(AST_CLS) $(SYMBOL_CLS) $(PARSER_SRC)
	$(CC) $(PARSER_SRC) $(FLEX_OUT) $(CUP_OUT)

run:
	java -cp $(CLASSPATH) parser.Driver

install:
	./install-lib.sh

test:
	tests/run.py

clean:
	rm -rf bin/ tree.txt table.txt code.T

