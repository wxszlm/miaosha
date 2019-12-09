package top.yxf.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

//        Message message1 = (Message)msg ;
//
//        System.out.println(message1.getData());
//
//        //此处写接收到客户端请求后的业务逻辑
//        String content="啦啦啦";
//        Header header=new Header((byte)0, (byte)1, (byte)1, (byte)1, (byte)0, "713f17ca614361fb257dc6741332caf2",content.getBytes("UTF-8").length, 1);
//        Message message=new Message(header,content);
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(buf.readBytes(((ByteBuf) msg).readableBytes()));
//        System.out.println(new Date() + ": 客户端开始写数据");
//        // 1. 获取数据
//        ByteBuf buffer = getByteBuf(ctx);
//        // 2. 写数据
//        ctx.channel().writeAndFlush(buffer);

        Scanner scanner = new Scanner(System.in);
        System.out.print("简某某:  ");
        String string = scanner.nextLine();
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer(string + ":  " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), CharsetUtil.UTF_8));

    }

//    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
//        // 1. 获取二进制抽象 ByteBuf
//        ByteBuf buffer = ctx.alloc().buffer();
//        // 2. 准备数据，指定字符串的字符集为 utf-8
//        byte[] bytes = "你好，苍穹盛夏童鞋!".getBytes(Charset.forName("utf-8"));
//        // 3. 填充数据到 ByteBuf
//        buffer.writeBytes(bytes);
//        return buffer;
//    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    //读取完成后处理方法
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        System.out.println("EchoServerHandler.channelReadComplete");
        //ctx.flush();
    }

    //异常捕获处理方法
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

}
