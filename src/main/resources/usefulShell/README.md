###脚本加单介绍：
**https://github.com/oldratlee/useful-scripts**
1. application-all-dump.sh
应用出现问题，常见的dump内容：
- io
- 堆栈
- 堆栈使用情况
- gc状况等
2. show-busy-java-threads.sh
用于快速排查Java的CPU性能问题(top us值过高)，自动查出运行的Java进程中消耗CPU多的线程，并打印出其线程栈，从而确定导致性能问题的方法调用。