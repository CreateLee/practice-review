package com.weblee.RPC;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月6日下午4:25:32
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public class EchoClient {
    public static void main(String[] args) {
	System.setSecurityManager(new RMISecurityManager());

	try {
	    Echo t = (Echo) Naming.lookup("EchoServer");

	    for (int i = 0; i < 10; i++) {
		System.out.println(t.echo(String.valueOf(i)));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
