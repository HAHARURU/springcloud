package cloud.cosumer.controller;

import cloud.cosumer.intf.FeignClient;
import cloud.cosumer.service.ConsumerService;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
public class DcController {
    //ribbon
//    @Autowired
//    RestTemplate restTemplate;
//
    @Autowired
    ConsumerService consumerService;

    @RequestMapping("/hConsumer")
    public String hdc() {
        return consumerService.consumer();
    }

    @Autowired
    FeignClient feignClient;

    @RequestMapping("/consumer")
    public String dc() {
        return feignClient.consumer();
    }

    @RequestMapping("/upload")
    public String upload() {
        File file = new File("C:\\Users\\Administrator\\Desktop\\TIM图片20180408170226.png");
        DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                MediaType.TEXT_PLAIN_VALUE, true, file.getName());

        try {
            InputStream input = new FileInputStream(file);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }

        MultipartFile multi = new CommonsMultipartFile(fileItem);
        return feignClient.upload(multi);
    }

}
