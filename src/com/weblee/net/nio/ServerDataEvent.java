package com.weblee.net.nio;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月6日下午3:46:31

 *************  function description  ***************
 *
 ****************************************************
 */

import java.nio.channels.SocketChannel;

class ServerDataEvent {
    public NioServer server;
    public SocketChannel socket;
    public byte[] data;

    public ServerDataEvent(NioServer server, SocketChannel socket, byte[] data) {
	this.server = server;
	this.socket = socket;
	this.data = data;
    }
}
