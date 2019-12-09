package top.yxf.test.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("EchoClient.main");


        // Configure the client.
        /*创建一个Bootstrap b实例用来配置启动客户端
         * b.group指定NioEventLoopGroup来处理连接，接收数据
         * b.channel指定通道类型
         * b.option配置参数
         * b.handler客户端成功连接服务器后就会执行
         * b.connect客户端连接服务器
         * b.sync阻塞配置完成并启动
         */
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    //.option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
//                            if (sslCtx != null) {
//                                p.addLast(sslCtx.newHandler(ch.alloc(), HOST, PORT));
//                            }
                            //p.addLast(new LoggingHandler(LogLevel.INFO));
                            p.addLast(new MessageDecoder());
                            p.addLast(new MessageEncoder());
                            //p.addLast("encoder", new MessageEncoder());
                            //p.addLast("decoder", new MessageDecoder());
                            //p.addFirst(new LineBasedFrameDecoder(65535));
                            p.addLast(new EchoClientHandler());
                        }
                    });

            // Start the client.
            ChannelFuture f = b.connect("127.0.0.1", 8888).sync();
            System.out.println("EchoClient.main ServerBootstrap配置启动完成");

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
            System.out.println("EchoClient.end");
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }
}
