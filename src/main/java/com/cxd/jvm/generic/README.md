泛型踩坑。

使用spring框架的BeanUtil拷贝属性时发现目标对象中属性的类型变成了源对象中的类型，举个例子：todo 连接到springUtils包下

最后发现是泛型擦除的问题。

https://www.cnblogs.com/wuqinglong/p/9456193.html

PECS
该原则的主题是集合，根据集合处于的角色来决定使用哪个关键字（`extends`、`super`）来修饰。
https://stackoverflow.com/questions/2723397/what-is-pecs-producer-extends-consumer-super