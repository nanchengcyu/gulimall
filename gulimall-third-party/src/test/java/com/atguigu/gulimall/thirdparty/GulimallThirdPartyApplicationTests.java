package com.atguigu.gulimall.thirdparty;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;

@SpringBootTest
class GulimallThirdPartyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private OSSClient ossClient;

    @Test
    public void testUpload() {
        // 创建PutObjectRequest对象。
        // 依次填写Bucket名称（例如examplebucket）、Object完整路径（例如exampledir/exampleobject.txt）和本地文件的完整路径。Object完整路径中不能包含Bucket名称。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
        PutObjectRequest putObjectRequest = new PutObjectRequest("ncygulimall", "ncy.jpg", new File("D:\\Afile\\picture\\头像.jpg"));

        // 上传文件。
        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

}
