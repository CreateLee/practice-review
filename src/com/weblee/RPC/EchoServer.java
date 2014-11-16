package com.weblee.RPC;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014��11��6������4:25:04
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public class EchoServer extends UnicastRemoteObject implements Echo {
    // Ĭ�Ϲ�������ҲҪ��������RemoteExceptionΥ��
    public EchoServer() throws RemoteException {
	super();
    }

    public String echo(String msg) throws RemoteException {
	return "Echo: " + msg;
    }

    public static void main(String[] args) {
	/* �����Ͱ�װһ����ȫ������������֧��RMI����ΪJava��������һ���֣�������RMIΨһһ����RMISecurityManager. */
	System.setSecurityManager(new RMISecurityManager());

	try {
	    /* ����Զ�̶����һ������ʵ����������EchoServer���� */
	    EchoServer es = new EchoServer();

	    /*
	     * ��RMIԶ�̶���ע���ע������һ��Զ�̶���һ��Զ�̶���ӵ�еķ�����������ָ������Զ�̶���ľ�����������ͻ���ע��������һ�Σ�
	     * �õ���һ��Զ�̶��󼴿�.
	     */
	    Naming.rebind("EchoServer", es);

	    System.out.println("Ready to provide echo service...");

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
