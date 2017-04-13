package com.cxd.javanio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {

    public static void main(String[] args) throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("nio-data.txt","rw");
        FileChannel fc = accessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        int bytesRead = fc.read(byteBuffer);
        while(bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            byteBuffer.flip();
            while(byteBuffer.hasRemaining()) {
                System.out.println((char) byteBuffer.get());
            }
            byteBuffer.clear();
            bytesRead = fc.read(byteBuffer);
        }
    }
}
