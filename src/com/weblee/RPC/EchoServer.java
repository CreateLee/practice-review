package com.weblee.RPC;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月6日下午4:25:04
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public class EchoServer extends UnicastRemoteObject implements Echo {
    // 默认构件器，也要“掷”出RemoteException违例
    public EchoServer() throws RemoteException {
	super();
    }

    public String echo(String msg) throws RemoteException {
	return "Echo: " + msg;
    }

    public static void main(String[] args) {
	/* 创建和安装一个安全管理器，令其支持RMI。作为Java开发包的一部分，适用于RMI唯一一个是RMISecurityManager. */
	System.setSecurityManager(new RMISecurityManager());

	try {
	    /* 创建远程对象的一个或多个实例，下面是EchoServer对象 */
	    EchoServer es = new EchoServer();

	    /*
	     * 向RMI远程对象注册表注册至少一个远程对象。一个远程对象拥有的方法即可生成指向其他远程对象的句柄，这样，客户到注册表里访问一次，
	     * 得到第一个远程对象即可.
	     */
	    Naming.rebind("EchoServer", es);

	    System.out.println("Ready to provide echo service...");

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
