package com.weblee.net.nio;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月6日下午3:47:08

 *************  function description  ***************
 *
 ****************************************************
 */

import java.nio.channels.SocketChannel;

public class ChangeRequest {
    public static final int REGISTER = 1;
    public static final int CHANGEOPS = 2;

    public SocketChannel socket;
    public int type;
    public int ops;

    public ChangeRequest(SocketChannel socket, int type, int ops) {
	this.socket = socket;
	this.type = type;
	this.ops = ops;
    }
}
