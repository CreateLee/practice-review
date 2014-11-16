package com.weblee.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月6日下午2:20:59
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public class DynamicProxyExample {
    public static void main(String[] args) {
	// new server
	CalcProtocol server = new Server();

	InvocationHandler handler = new CalcHandler(server);
	// new client
	CalcProtocol client = (CalcProtocol) Proxy.newProxyInstance(server
		.getClass().getClassLoader(),
		server.getClass().getInterfaces(), handler);

	int result = client.add(8, 9);
	System.out.println(result + "\t" + new Date().getTime());

	result = client.subtract(9, 6);
	System.out.println(result + "\t" + new Date().toString());
    }
}
