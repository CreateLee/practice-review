package com.weblee.RPC;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014��11��6������4:14:56
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public interface Echo extends Remote {
    String echo(String msg) throws RemoteException;
}
