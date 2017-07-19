package com.cxd.groovy.integrating.integrationMechanisms

import com.cxd.groovy.syntax.User

/**
 * Created by childe on 2017/7/19.
 */
class EvalTest {
    static void main(String[] args) {
        def user = new User(id: 001,books: ['book1','book2','book2']);
        println Eval.me("user",user,"user.id != null && user.name == null")

        user.books.each{
            println(it)
        }
    }
}
