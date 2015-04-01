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

