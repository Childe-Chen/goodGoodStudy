### 有意思的测试
配合自定义的java.lang.String可以猜测idea编译时，classLoader应该时自己定义的，并且没有向上委派，
因为删掉target后去编译工程，有关String引用的类全部报错。

### 总结

总结在[了解ClassLoader](http://childe.net.cn/2017/06/28/%E4%BA%86%E8%A7%A3ClassLoader/)