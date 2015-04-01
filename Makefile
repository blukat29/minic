
# =========== Configurables start ==================

# Directories
# Make sure they have a slash at the end.
LIBDIR=lib/

# Lex & Yacc files
FLEX_SRC=src/parser/lexer.flex
FLEX_OUT=bin/Lexer.java
JFLEX=$(LIBDIR)/jflex/bin/jflex -d bin/

CUP_SRC=src/parser/calc.cup
CUP_OUT=bin/Parser.java bin/Symbols.java
CUP=java -jar $(LIBDIR)/cup/java-cup-11b.jar -destdir bin/

PARSER_CLASSES=$(addsuffix .class,$(addprefix bin/parser/,Symbols Lexer Parser))

# =========== Configurables end ==================

CLASSPATH=".:$(LIBDIR)/cup/java-cup-11b-runtime.jar:bin/"
CC=javac -cp $(CLASSPATH) -d bin/

SOURCES=$(addsuffix .java,$(addprefix src/,parser/Driver))
CLASSES=$(patsubst src/%.java, bin/%.class, $(SOURCES))

all: bin $(CUP_OUT) $(FLEX_OUT) $(PARSER_CLASSES) $(CLASSES)

bin:
	mkdir -p bin

$(FLEX_OUT): $(FLEX_SRC)
	$(JFLEX) $(FLEX_SRC)

$(CUP_OUT): $(CUP_SRC)
	$(CUP) -parser Parser -symbols Symbols $(CUP_SRC)

$(PARSER_CLASSES): bin/parser/%.class: bin/%.java
	$(CC) $<

bin/%.class: src/%.java
	$(CC) $<

run:
	java -cp $(CLASSPATH) parser.Driver

install:
	./install-lib.sh

clean:
	rm -rf bin/

