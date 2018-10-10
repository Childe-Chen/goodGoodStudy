TODO-基准测试

http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/
http://openjdk.java.net/projects/code-tools/jmh/


### 基准测试中建议：
- 测试前后较重的处理放在@Setup和@TearDown中
- 注意DCE（死代码消除）
- 不出现内部循环
- 测试必须为fork，fork是分离出来子进程进行测试，@fork(2)含义为顺次（one-by-one）fork出子进程来测试
- 使用@fork多次fork测试，减少运行间差异


### 知识点记录：

1. 编译优化
常见的编译器优化包括（[更多参见](http://www.importnew.com/2009.html)）：死代码消除（DCE）、方法内敛（method inline）、循环优化。

以下记录方法内敛。

许多优化手段都试图消除机器级跳转指令（例如，x86架构的JMP指令）。跳转指令会修改指令指针寄存器，因此而改变了执行流程。
相比于其他汇编指令，跳转指令是一个代价高昂的指令，这也是为什么大多数优化手段会试图减少甚至是消除跳转指令。
内联是一种家喻户晓而且好评如潮的优化手段，这是因为跳转指令代价高昂，而内联技术可以将经常调用的、具有不容入口地址的小方法整合到调用方法中。
Listing 3到Listing 5中的Java代码展示了使用内联的用法。

Listing 3. Caller method
``` java
int whenToEvaluateZing(int y) {
  return daysLeft(y) + daysLeft(0) + daysLeft(y+1);
}
```
Listing 4. Called method
``` java
int daysLeft(int x){
  if (x == 0)
     return 0;
  else
     return x - 1;
}
```
Listing 5. Inlined method
``` java
int whenToEvaluateZing(int y){
  int temp = 0;

  if(y == 0) temp += 0; else temp += y - 1;
  if(0 == 0) temp += 0; else temp += 0 - 1;
  if(y+1 == 0) temp += 0; else temp += (y + 1) - 1;

  return temp;
}
```
在Listing 3到Listing 5的代码中，展示了将调用3次小方法进行内联的示例，这里我们认为使用内联比跳转有更多的优势。

如果被内联的方法本身就很少被调用的话，那么使用内联也没什么意义，但是对频繁调用的“热点”方法进行内联在性能上会有很大的提升。
此外，经过内联处理后，就可以对内联后的代码进行进一步的优化，正如Listing 6中所展示的那样。

Listing 6. After inlining, more optimizations can be applied
```java
int whenToEvaluateZing(int y){
  if(y == 0) return y;
  else if (y == -1) return y - 1;
  else return y + y - 1;
}
```