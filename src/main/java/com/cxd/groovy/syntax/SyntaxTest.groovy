package com.cxd.groovy.syntax
/**
 * Created by childe on 2017/7/18.
 */
class SyntaxTest {
    static void main(String[] args) {
//        println(Eval.me('33*3'))
//        println(Eval.me('"foo".toUpperCase()'))
//        println( 'ab' == 'a' + 'b')
//
//        def name = 'Guillaume' // a plain string
//        def greeting = "Hello ${name.toString()}"
//
//        println(greeting)

//        def number = 3.14;
//        println "${number.toString()}"

//        def sParameterLessClosure = "1 + 2 == ${-> 3}"
//        println(sParameterLessClosure)
//
//        def sOneParamClosure = "1 + 2 == ${ w -> w << 32}"
//        println(sOneParamClosure)

        Map map = new HashMap()
//
        def number = 1
        def eagerGString = "value == ${number}"
        def lazyGString = "value == ${ -> number }"
User u = new User()
//        println(eagerGString)
//        println(lazyGString)

        map.put(eagerGString,eagerGString)
        map.put(lazyGString,lazyGString)

//        number = 2
        println(map.get(eagerGString))
        println(map.get('value == 1'))

//        println "one: ${1}" != "one: 1"
//        println "one: ${1}".hashCode() != "one: 1".hashCode()

//        def name = 'Groovy'
//        def template = """
//    Dear Mr ${name},
//
//    You're the winner of the lottery!
//
//    Yours sincerly,
//
//    Dave
//"""
//
//        println template.toString().contains('Groovy')



//        def action = this.&describe
//        def list = [
//                new User(name: 'Bob',   age: 42),
//                null,
//                new User(name: 'Julia', age: 35)]
//        println transform(list, action) == ['Bob is 42', 'Julia is 35']
//
//        SyntaxTest syntaxTest = new SyntaxTest();
//        def reference = syntaxTest.&doSomething
//        assert reference('foo') == 'FOO'
//        assert reference(123)   == 246

//        assert list*.age == [42,null,35]

//        assert function(4,5,6) == 26
//        def args1 = [4,5]
////        assert function(*args1,5,6) == 26
//        assert function(*args1,6) == 26

//        def list1 = ['Groovy 1.8','Groovy 2.0','Groovy 2.3']
//        def list2 = ['Groovy 1.8','Groovy 2.0','Groovy 2.3']
//        assert list1 == list2
//        assert !list1.is(list2)
    }

    static int function(int x, int y, int z) {
        x*y+z
    }

    def doSomething(String str) { str.toUpperCase() }
    def doSomething(Integer x) { 2*x }

    static def transform(List elements, Closure action) {
        def result = []
        elements.each {
            result << action(it)
        }
        result
    }

    static String describe(User p) {
        "$p.name is $p.age"
    }
}
