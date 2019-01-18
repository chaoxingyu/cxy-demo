package consul.consumer.demo.feign;

import consul.consumer.demo.hystrix.HelloRemoteHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName HelloRemote
 * @Description TODO
 * @auther chaoxingyu
 * @Date 2019-01-08 13:49
 * @Version 1.0
 */
@FeignClient(name = "service-consul-provider", fallback = HelloRemoteHystrix.class)
public interface HelloRemote4Feign {

    @RequestMapping(value = "/cloudApi/helloFeign")
    public String helloFeign(@RequestParam(value = "name") String name);

}
