package demo.gatew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudGateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudGateWayApplication.class, args);
	}

}

