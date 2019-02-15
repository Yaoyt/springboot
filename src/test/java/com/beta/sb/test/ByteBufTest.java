package com.beta.sb.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.nio.charset.Charset;

/**
 * Created by yaoyt on 18/12/7.
 *
 * @author yaoyt
 */
public class ByteBufTest {

    @Test
    public void test1(){
        ByteBuf buffer = Unpooled.buffer(24);
        while (buffer.readableBytes() > 4) {
            buffer.writeInt(12);
        }
        System.out.println(buffer.toString());

    }

    @Test
    public void test2(){
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf slice = buf.slice(0, 15);
        System.out.println(slice.toString(utf8));
        buf.setByte(0, (byte) 'J');
        System.out.println(buf.toString(utf8));

    }

    @Test  //todo  set get 方法不会修改 readIndex 和 writeIndex
    public void test3() {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        System.out.println((char) buf.getByte(0));
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        buf.setByte(0, (byte) 'B');
        System.out.println((char) buf.getByte(0));
        assert readerIndex == buf.readerIndex();
        assert writerIndex == buf.writerIndex();
    }


}
