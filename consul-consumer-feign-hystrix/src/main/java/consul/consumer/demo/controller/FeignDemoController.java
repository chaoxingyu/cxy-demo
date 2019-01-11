package consul.consumer.demo.controller;

import consul.consumer.demo.feign.HelloRemote4Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ConsumerController
 * @Description TODO
 * @auther chaoxingyu
 * @Date 2019-01-08 13:51
 * @Version 1.0
 */
@RestController
public class FeignDemoController {

    @Autowired
    HelloRemote4Feign HelloRemote;

    @RequestMapping("/helloFeign/{name}")
    public String helloFeign(@PathVariable("name") String name) {
        return HelloRemote.helloFeign(name);
    }

}
