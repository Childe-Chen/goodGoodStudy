### 有意思的测试
配合自定义的java.lang.String可以猜测idea编译时，classLoader应该时自己定义的，并且没有向上委派，
因为删掉target后去编译工程，有关String引用的类全部报错。