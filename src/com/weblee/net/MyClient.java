package com.weblee.net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月6日下午2:40:16

 *************  function description  ***************
 *
 ****************************************************
 */

public class MyClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
	// new client socket
	Socket socket = new Socket("127.0.0.1", 88888);
	// 构建数据输入流，用以接收数据
	DataInputStream in = new DataInputStream(socket.getInputStream());
	// 构建数据输出流，用以发送数据
	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	
	// read
	
	// write
	
	
	System.out.println("the data from server is : ");
	
	socket.close();
    }
}
