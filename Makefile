
# =========== Configurables start ==================

# Directories
# Make sure they have a slash at the end.
LIBDIR=lib/

# Lex & Yacc files
FLEX_SRC=src/parser/lexer.flex
FLEX_OUT=bin/Lexer.java
JFLEX=$(LIBDIR)/jflex/bin/jflex -d bin/

CUP_SRC=src/parser/parser.cup
CUP_OUT=bin/Parser.java bin/Symbols.java
CUP=java -jar $(LIBDIR)/cup/java-cup-11b.jar -destdir bin/

# =========== Configurables end ==================

CLASSPATH=".:$(LIBDIR)/cup/java-cup-11b-runtime.jar:bin/"
CC=javac -cp $(CLASSPATH) -d bin/

SOURCES=$(shell find src -name "*.java")
CLASSES=$(patsubst src/%.java, bin/%.class, $(SOURCES))

all: bin classes

bin:
	mkdir -p bin

$(FLEX_OUT): $(FLEX_SRC)
	$(JFLEX) $(FLEX_SRC)

$(CUP_OUT): $(CUP_SRC)
	$(CUP) -locations -interface -parser Parser -symbols Symbols -xmlactions $(CUP_SRC)
#	$(CUP) -parser Parser -symbols Symbols $(CUP_SRC)

classes: $(FLEX_OUT) $(CUP_OUT) $(SOURCES)
	$(CC) $(SOURCES) $(FLEX_OUT) $(CUP_OUT)

run:
	java -cp $(CLASSPATH) parser.Driver

install:
	./install-lib.sh

test:
	tests/run.py

clean:
	rm -rf bin/

