package top.yxf.miaosha.controller;


import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yxf.miaosha.comon.Result;
import top.yxf.miaosha.domian.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *@author wuxiangsheng
 *@description 测试专用
 *@className HelloController
 *@date 2019/10/10 10:25
 *
 **/
@Api(tags = "测试相关接口",value = "HelloController",description = "测试专用")
@Controller
@RequestMapping(value = "test")
public class HelloController {

    private static final String channel1 = "fullDataUpload";
    private static final String channel2 = "analysis";

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "test thymeleaf",notes = "test thymeleaf success" )
    @ResponseBody
    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public Result<String> hello(Model model){

        model.addAttribute("uid","hello");
        model.addAttribute("uname","world");

        return Result.success(JSONObject.toJSONString(model));
    }

    @ApiOperation(value = "test redis",notes = "test redis set value success" )
    @RequestMapping(value = "set",method = RequestMethod.GET)
    @ResponseBody
    public Result<String> set(){
//        List<User> users = new ArrayList<>(4);
//        User user = new User(1,"呼呼·");
//        User user2 = new User(1,"嘿嘿·");
//        User user3 = new User(1,"拉拉·");
//        User user4 = new User(1,"哈哈·");
//
//        users.add(user);
//        users.add(user2);
//        users.add(user3);
//        users.add(user4);
//
//        redisTemplate.opsForValue().set("isOk",user);
//        redisTemplate.opsForValue().set("list",users);
//        System.out.println(JSONObject.toJSONString(redisTemplate.opsForValue().get("isOk")));
//        User user1 = (User) redisTemplate.opsForValue().get("isOk");

        // 设置过期时间
        // redisTemplate.expire("isOk",10, TimeUnit.SECONDS);
//
//        System.out.println(user1.getUserId() + "0---->" + user1.getUserName());
//        List<User> userList = ( List<User>)redisTemplate.opsForValue().get("list");
//
//        for (User u : userList) {
//            System.out.println(u);
//        }
        redisTemplate.opsForValue().set("isOk",1);
        return Result.success("success");
    }
    @ApiOperation(value = "test redis increment",notes = "Point praise mode" )
    @RequestMapping(value = "increment",method = RequestMethod.GET)
    @ResponseBody
    public Result<String> increment(){
        redisTemplate.boundValueOps("isOk").increment(1);
        return Result.success("success");
    }

    @ApiOperation(value = "test redis pubAndSub",notes = "Publish subscribe" )
    @RequestMapping(value = "pubAndSub",method = RequestMethod.GET)
    @ResponseBody
    public Result<String> pubAndSub(){

        User user = new User();
        user.setUserId(123);
        user.setUserName("刘大");

        User user2 = new User();
        user2.setUserId(456);
        user2.setUserName("李二");

        redisTemplate.convertAndSend(channel1,user2);
        redisTemplate.convertAndSend(channel2,user);

        return Result.success("success");
    }
    @ApiOperation(value = "test redis pubAndSubMessage",notes = "Publish subscribe" )
    @RequestMapping(value = "pubAndSubMessage",method = RequestMethod.GET)
    @ResponseBody
    public Result<String> pubAndSubMessage(){

        redisTemplate.convertAndSend(channel1,"订阅了");

        return Result.success("success");
    }
}
