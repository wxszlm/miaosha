package top.yxf.miaosha.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yxf.miaosha.socket.SocketServer;

import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestSocket {

    @GetMapping("socket/{sid}")
    public void getSocket(String message, @PathVariable String sid)throws IOException {
        SocketServer server=new SocketServer();
        server.onMessage(message,sid);
    }

}
