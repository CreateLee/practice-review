package com.weblee.dynamicProxy;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014��11��6������2:19:55
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public class Server implements CalcProtocol {

    @Override
    public int add(int a, int b) {
	// TODO Auto-generated method stub
	return a + b;
    }

    @Override
    public int subtract(int a, int b) {
	// TODO Auto-generated method stub
	return a - b;
    }

}
