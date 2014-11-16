package com.weblee.net.nio;

import java.io.IOError;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @Author: weblee
 * @Email: likaiweb@163.com
 * @Blog: http://www.cnblogs.com/lkzf/
 * @Time: 2014年11月6日下午2:53:51
 * 
 *************        function description ***************
 *
 ****************************************************
 */

public class Test {
    static final int NIO_BUFFER_LIMIT = 8 * 1024;

    public int channelWrite(WritableByteChannel channel, ByteBuffer buffer)
	    throws IOException {
	return (buffer.remaining() <= NIO_BUFFER_LIMIT) ? channel.write(buffer)
		: channelIO(null, channel, buffer);
    }

    private int channelIO(Object object, WritableByteChannel channel,
	    ByteBuffer buffer) throws IOException {
	// TODO Auto-generated method stub
	int originalLimit = buffer.limit();
	int initialRemaining = buffer.remaining();
	int ret = 0;

	while (buffer.remaining() > 0) {
	    try {
		int ioSize = Math.min(buffer.remaining(), NIO_BUFFER_LIMIT);
		buffer.limit(buffer.position() + ioSize);
/*
		ret = (readCh == null) ? writeCh.write(buffer) : readCh
			.read(buffer);
			*/

		if (ret < ioSize) {
		    break;
		}
	    } catch (Exception e) {
		// TODO: handle exception
	    } finally {
		buffer.limit(originalLimit);
	    }
	}

	int nBytes = initialRemaining - buffer.remaining();

	return (nBytes > 0) ? nBytes : ret;
    }

}
