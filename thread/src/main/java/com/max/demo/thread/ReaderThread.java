package com.max.demo.thread;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by max on 2017/11/1.
 */
public class ReaderThread extends Thread {
    private static final int BUFSZ = 512;
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    public void interrupt() {
        try {
            // 关闭套接字
            // 此时in.read会抛出异常
            socket.close();
        } catch (IOException ignored) {
        } finally {
            // 正常的中断
            super.interrupt();
        }
    }

    public void run() {
        try {
            byte[] buf = new byte[BUFSZ];
            while (true) {
                int count = in.read(buf);
                if (count < 0)
                    break;
                else if (count > 0)
                    processBuffer(buf, count);
            }
        } catch (IOException e) {
            // 如果socket关闭，in.read方法将会抛出异常
            // 借此机会，响应中断，线程退出
        }
    }

    public void processBuffer(byte[] buf, int count) {
    }
}
