package com.cxd.groovy.integrating.integrationMechanisms

/**
 * Created by childe on 2017/7/19.
 */
class Shell {
    static void main(String[] args) {
//        def shell = new GroovyShell()
//        def result = shell.evaluate '3*5'
//        def result2 = shell.evaluate(new StringReader('3*5'))
//        assert result == result2
//        def script = shell.parse '3*5'
//        assert script instanceof groovy.lang.Script
//        assert script.run() == 15


//        def sharedData = new Binding()
//        def shell = new GroovyShell(sharedData)
//        def now = new Date()
//        sharedData.setProperty('text', 'I am shared data!')
//        sharedData.setProperty('date', now)
//
//        shell.evaluate('text+=11')
//        String result = shell.evaluate('"At $date, $text"')
//        println(result)
//        assert result == "At $now, I am shared data!"


//        def shell = new GroovyShell()
//
//        def b1 = new Binding(x:3)
//        def b2 = new Binding(x:4)
//        def script1 = shell.parse('x = 2*x')
//        def script2 = shell.parse('x = 2*x')
//        assert script1 != script2
//        script1.binding = b1
//        script2.binding = b2
//        def t1 = Thread.start { script1.run() }
//        def t2 = Thread.start { script2.run() }
//        [t1,t2]*.join()
//        assert b1.getProperty('x') == 6
//        assert b2.getProperty('x') == 8
//        assert b1 != b2
        File tmpDir = new File("/Users/childe/Documents/workspace/goodGoodStudy/" +
                "src/main/java/com/cxd/groovy/integrating/integrationMechanisms/");

        def binding = new Binding()
        def engine = new GroovyScriptEngine([tmpDir.toURI().toURL()] as URL[])

        while (true) {
            def greeter = engine.run('Greet.groovy', binding)
            println greeter?.sayHello()
            Thread.sleep(1000)
        }

    }
}
