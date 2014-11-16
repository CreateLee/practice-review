package com.weblee.net.nio;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月6日下午3:48:18
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public class RspHandler {
    private byte[] rsp = null;

    public synchronized boolean handleResponse(byte[] rsp) {
	this.rsp = rsp;
	this.notify();
	return true;
    }

    public synchronized void waitForResponse() {
	while (this.rsp == null) {
	    try {
		this.wait();
	    } catch (InterruptedException e) {

	    }
	}

	System.out.println(new String(this.rsp));
    }
}
