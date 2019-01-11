package demo.consul.producer.webdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description TODO
 * @auther chaoxingyu
 * @Date 2019-01-04 16:11
 * @Version 1.0
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello consul";
    }


    @RequestMapping("/helloFeign")
    public String helloFeign(@RequestParam(value = "name") String name) {
        return "hello " + name + " this is one ";
    }


}
