package cloud.cosumer.intf;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@org.springframework.cloud.netflix.feign.FeignClient(value = "eureka-client", configuration = FeignClient.MultipartSupportConfig.class)
public interface FeignClient {
    //传文件
    @Configuration
    class MultipartSupportConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }

    @RequestMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String upload(MultipartFile file);

    @RequestMapping("/dc")
    String consumer();

}
