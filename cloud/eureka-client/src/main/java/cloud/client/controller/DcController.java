package cloud.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RefreshScope   //是热部署，即配置文件改变的话，配置中心客户端可以实时更新。
@RestController
public class DcController {

    @Value("${from}")
    private String from;

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping("/dc")
    public String dc() throws InterruptedException {
//        Thread.sleep(5000L);    //模拟降级
        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }

    @RequestMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(MultipartFile file) {
        return file.getOriginalFilename();
    }
}
