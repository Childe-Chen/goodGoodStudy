#!/usr/bin/env bash
gcc -shared -fpic -I$JAVA_HOME/include -I$JAVA_HOME/include/darwin TestJNI.c -o libTestJNI.jnilib