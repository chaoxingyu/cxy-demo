package consul.consumer;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Configuration
public class ConsulConsumerFeignHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulConsumerFeignHystrixApplication.class, args);
    }


    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registServlet = new ServletRegistrationBean(streamServlet);
        registServlet.setLoadOnStartup(1);
        registServlet.addUrlMappings("/actuator/hystrix.stream");
        registServlet.setName("HystrixMetricsStreamServlet");
        return  registServlet;
    }


}

