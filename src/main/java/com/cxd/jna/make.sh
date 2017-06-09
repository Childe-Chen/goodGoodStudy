#!/usr/bin/env bash
gcc -shared -fpic MyLib.c -o libMyLib.dylib
#-I$JAVA_HOME/include -I$JAVA_HOME/include/darwin