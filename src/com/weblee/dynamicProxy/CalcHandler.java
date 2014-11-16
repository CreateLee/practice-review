package com.weblee.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月6日下午2:20:32
 * 
 *************        function description ***************
 *
 ****************************************************
 */

// 实现调用处理器接口
public class CalcHandler implements InvocationHandler {
    private Object objOriginal;

    public CalcHandler(Object object) {
	// TODO Auto-generated constructor stub
	this.objOriginal = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
	    throws Throwable {
	// TODO Auto-generated method stub
	// before proxy
	System.out.println("before proxy!" + "\t" + new Date().toString());

	Object result = method.invoke(this.objOriginal, args);

	// after proxy
	System.out.println("after proxy!" + "\t" + new Date().toString());

	return result;
    }

}
