#!/bin/bash
javac -d ./bin src/*.java
cd bin
jar cf ../JavaWindow.jar *.class