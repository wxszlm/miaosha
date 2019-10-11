package top.yxf.miaosha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *@author wuxiangsheng
 *@description Swagger2 配置类
 *@className Swagger2
 *@date 2019/10/10 11:44
 *
 **/
@Configuration
@EnableSwagger2

public class Swagger2 {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) // 调用apiInfo方法
                .pathMapping("/") //配置访问路径
                .select()
                .paths(PathSelectors.regex("/.*")) //匹配路径下的方法
                .build();

    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("")
//                .contact(new Contact("gracie", "xxxxxxxxxx@qq.com", ""))
//                .contact(new Contact())
                .description("接口文档")
                .version("1.0")
                .build();

    }


}
