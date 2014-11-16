package com.weblee.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月6日下午2:49:43

 *************  function description  ***************
 *
 ****************************************************
 */

public class MyServer {

    public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub
	ServerSocket serverSocket = new ServerSocket(8888);
	
	Socket socket = serverSocket.accept();
    }

}
