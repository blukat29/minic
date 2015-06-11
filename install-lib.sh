#!/bin/sh
mkdir -p lib
mkdir -p lib/jflex
mkdir -p lib/cup
cd lib/

# install jflex
wget http://jflex.de/jflex-1.6.1.tar.gz
tar xzf jflex-1.6.1.tar.gz
rm jflex-1.6.1.tar.gz

# install cup
cd cup
wget http://www2.cs.tum.edu/projects/cup/releases/java-cup-bin-11b-20150226.tar.gz
tar xzf java-cup-bin-11b-20150226.tar.gz
rm java-cup-bin-11b-20150226.tar.gz

cd ../..

# install T-machine interpreter
echo "Creating symlink ./machine/run-T"
rm -f machine/run-T
rm -f machine/run-T.exe
if [ "$(uname)" == "Darwin" ]; then
  echo "Hi Mac"
  ln -s mac-T machine/run-T
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
  echo "Hi Linux"
  ln -s linux-T machine/run-T
elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW32_NT" ]; then
  echo "Please put windows T-machine interpeter at ./machine/run-T.exe"
fi
