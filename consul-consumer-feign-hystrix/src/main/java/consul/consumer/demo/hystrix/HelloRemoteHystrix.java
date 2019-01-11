package consul.consumer.demo.hystrix;

import consul.consumer.demo.feign.HelloRemote4Feign;
import org.springframework.stereotype.Component;

/**
 * @ClassName HelloRemoteHystrix
 * @Description TODO
 * @auther chaoxingyu
 * @Date 2019-01-09 16:24
 * @Version 1.0
 */
@Component
public class HelloRemoteHystrix implements HelloRemote4Feign {

    @Override
    public String helloFeign(String name) {
        return "this service is bad by Hystrix！";
    }

}
