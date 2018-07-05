package cloud.cosumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("ConsumerService")
public class ConsumerService {
    @Autowired
    RestTemplate restTemplate;

    public String fallback() { //在降级时——请求生产端阻塞或发生错误时调用
        return "fallback";
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public String consumer() {
        return restTemplate.getForObject("http://eureka-client/dc", String.class);
    }
}
