package com.weblee.net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月6日下午3:35:14
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public class EchoServer {
    static private int bufferSize = 1024 * 2;
    private int servicePort = 7777;
    private Selector selector = null;
    private byte[] welcomeMsg = null;
    private ByteBuffer echo = null;
    private ByteBuffer bye = null;

    public EchoServer() {
	this(7777);
    }

    public EchoServer(int port) {
	this.servicePort = port;
    }

    public void initialize() throws IOException {
	ServerSocketChannel server = null;

	server = ServerSocketChannel.open();
	server.configureBlocking(false);
	server.socket().bind(new InetSocketAddress(servicePort), bufferSize);

	selector = Selector.open();
	server.register(selector, SelectionKey.OP_ACCEPT);

	String msg = "Welcome to MyOS(v1.0).\r\n";
	welcomeMsg = msg.getBytes();
	echo = ByteBuffer.wrap("$ ".getBytes());
	bye = ByteBuffer.wrap("bye.\r\n".getBytes());
    }

    public void startup() throws IOException {
	System.out.println("服务启动,正在监听端口 " + servicePort);
	Set<SelectionKey> keys = null;
	Iterator<SelectionKey> iter = null;
	SelectionKey key = null;

	while (selector.select() > 0) {
	    keys = selector.selectedKeys();
	    iter = keys.iterator();

	    while (iter.hasNext()) {
		key = iter.next();
		iter.remove();

		try {
		    if (key.isAcceptable()) {
			accept(key);
			continue;
		    }

		    if (key.isReadable()) {
			receive(key);
			continue;
		    }

		    if (key.isWritable()) {
			if (send(key)) {
			    try {
				if (null != key)
				    key.cancel();
			    } catch (Exception e) {
			    }

			    try {
				if (null != key)
				    key.channel().close();
			    } catch (Exception e) {
			    }

			    break;
			}
			continue;
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();

		    try {
			if (null != key)
			    key.cancel();
		    } catch (Exception e) {
		    }

		    try {
			if (null != key)
			    key.channel().close();
		    } catch (Exception e) {
		    }
		}
	    }
	}
    }

    void accept(SelectionKey key) throws IOException {
	ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
	SocketChannel sc = ssc.accept();
	sc.configureBlocking(false);
	System.out.println("有新客户连接 " + sc.socket().getInetAddress() + ":"
		+ sc.socket().getPort());

	ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
	buffer.position(0);
	buffer.put(welcomeMsg);
	sc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE,
		buffer);
    }

    /**
     * 接收数据
     * 
     * @param key
     * @throws IOException
     */
    void receive(SelectionKey key) throws IOException {
	SocketChannel sc = (SocketChannel) key.channel();
	ByteBuffer buffer = (ByteBuffer) key.attachment();
	ByteBuffer readBuffer = ByteBuffer.allocate(128);
	// byte[] cmd = null;
	// String cmdStr = null;
	int readLen = sc.read(readBuffer);
	readBuffer.flip();

	// readBuffer.position(0);
	// if(readBuffer.limit()<16){
	// cmd = new byte[16];
	// readBuffer.get(cmd, 0, readLen);
	// cmdStr = new String(cmd, 0, readLen);
	//
	// if(cmdStr.startsWith("exit")){
	// System.out.println("exit.");
	// buffer.put("bye.\r\n".getBytes());
	// return true;
	// }
	// }

	readBuffer.position(0);
	buffer.put(readBuffer);
	// byte[] bys = new byte[128];
	// readBuffer.get(bys, 0, readLen);
	// System.out.println("|"+new String(bys,0,readLen)+"|");

	// buffer.flip();
	// byte[] bys = new byte[1024];
	// int limit = buffer.limit();
	// buffer.get(bys,0,limit);
	// System.out.println("|"+new String(bys,0,limit)+"|");
	// buffer.limit(buffer.capacity());
    }

    boolean send(SelectionKey key) throws IOException {
	SocketChannel sc = (SocketChannel) key.channel();
	ByteBuffer buffer = (ByteBuffer) key.attachment();
	// ByteBuffer sendBuffer = ByteBuffer.wrap("echo:".getBytes());
	ByteBuffer sendBuffer1 = null;
	// byte[] bys = new byte[buffer.capacity()];
	byte[] bys = null;
	String cmd = null;

	// buffer.flip();
	int bl = buffer.position();

	if (bl > 0) {
	    byte end = buffer.get(bl - 1);

	    if (end == '\n') {
		// bys[0] = '{';
		// buffer.get(bys, 1, bl-1);
		// bys[bl] = '}';
		// bys[bl+1] = '\n';
		// String str = new String(bys,0,bl+2);
		// System.out.println(str);

		// sendBuffer1 = ByteBuffer.wrap(buffer.);
		// buffer.position(0);

		buffer.flip();
		buffer.position(0);

		bys = new byte[buffer.limit()];
		buffer.get(bys, 0, bl);
		cmd = new String(bys, 0, bl);

		if (cmd.startsWith("exit")) {
		    sc.write(bye);
		    return true;
		}
		buffer.position(0);
		// sendBuffer1 = buffer.slice();
		sc.write(buffer);

		echo.position(0);
		sc.write(echo);
		//
		// sendBuffer1.clear();
		buffer.clear();
	    }
	}
	buffer.limit(buffer.capacity());
	return false;
	// byte end = buffer.get(-1);
    }

    public void setBufferSize(int _bufferSize) {
	bufferSize = _bufferSize;
    }
}
