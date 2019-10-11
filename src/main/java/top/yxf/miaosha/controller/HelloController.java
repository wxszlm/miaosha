package top.yxf.miaosha.controller;


import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yxf.miaosha.comon.Result;

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

    @ApiOperation(value = "test thymeleaf",notes = "test thymeleaf success" )
    @ResponseBody
    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public Result<String> hello(Model model){

        model.addAttribute("uid","hello");
        model.addAttribute("uname","world");

        return Result.success(JSONObject.toJSONString(model));
    }
}
