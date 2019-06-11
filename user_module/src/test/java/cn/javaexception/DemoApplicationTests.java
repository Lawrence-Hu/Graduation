package cn.javaexception;

import cn.javaexception.entity.Message;
import cn.javaexception.mapper.UserMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    UserMapper userMapper;

    //@Test
    public void test() {
//        redisTemplate.opsForValue().set("test","asdsd");
//        Object test = redisTemplate.opsForValue().get("test");
//        System.out.println(test);
        Message message = new Message();
        message.setContent("你也hello");
        message.setFromUserId("cba4ad95e3b5b50ff7a637e1f4cd6cf1");
        message.setToUserId("7382a98ad248fbbacd248b072bbbff26");
        System.out.println(JSON.toJSON(message));
    }

    //@Test
    public void test01() throws IOException, MyException {
        ClientGlobal.initByProperties("config/fastdfs-client.properties");
        TrackerClient client = new TrackerClient();
        TrackerServer trackerServer = client.getConnection();
        StorageClient1 client1 = new StorageClient1(trackerServer, null);
        NameValuePair[] meta = new NameValuePair[1];
       // meta[0] = new NameValuePair("fileName", "1.png");
        //  String png = client1.upload_file1("C:\\Users\\97347\\Desktop\\1.jpg", "jpg", meta);
//        FileInfo group1 = client1.query_file_info("group1", "M00/00/00/rBAAClzmrAyAGA8CAAILeb7p8n4927.jpg");
//        FileInfo info = client1.query_file_info1("group1/M00/00/00/rBAAClzmrAyAGA8CAAILeb7p8n4927.jpg");
//        NameValuePair[] metadata1 = client1.get_metadata1("group1/M00/00/00/rBAAClzmrAyAGA8CAAILeb7p8n4927.jpg");

//        byte[] bytes = client1.download_file1("group1/M00/00/00/rBAAClzmrAyAGA8CAAILeb7p8n4927.jpg");
//        File file = new File("C:\\Users\\97347\\Desktop\\2.jpg"   );
//        FileOutputStream outputStream = new FileOutputStream(file);
//        outputStream.write(bytes);
//        int i = client1.delete_file1("group1/M00/00/00/rBAAClznZ8WAFM9jAAILeb7p8n4707.jpg");
//        System.out.println(i);
        FileInfo fileInfo = client1.query_file_info1("group1/M00/00/00/rBAAClzmrAyAGA8CAAILeb7p8n4927.jpg");
        System.out.println(fileInfo);
//        byte[] bytes = client1.download_file1("group1/M00/00/00/rBAAClznZ8WAFM9jAAILeb7p8n4707.jpg");
//        File file = new File("C:\\Users\\97347\\Desktop\\1.jpg");
//        FileOutputStream outputStream = new FileOutputStream(file);
//        outputStream.write(bytes);
        //   System.out.println(png);
//        System.out.println(info);
//        System.out.println(group1);
        trackerServer.close();
    }

    @Test
    public void test2() throws IOException, DocumentException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://passport.csdn.net/v1/register/pc/login/doLogin");
        JSONObject object = JSON.parseObject("{\n" +
                "\t\"fkid\": \"WC39ZUyXRgdGIn3d4fQZJFzawXnGzf/vsh86+CK/Qr5Qb7FYoIlnkWhVmBWPct/ZR8pmnTUevx7Tg+ryQowGAJXMWowi7UG1lELgz6mfKIbKiXFaGTKAi6kzA0CtbXuWTNaLm3yCn+Luwj5YO81dDu2k1cFDNm8ZdQLz+QwFFZRMWb38ZIHBX2BzsooWuczpFvHMtfJuAU2gke1HsJOdFJIYBxJtVVkYZHmpB5KeNyMSqy91zR9F2CbS/1pqz9k2rqnxm0nDk4KJzqIVPfSLuupoQMnY11jut1487577677129\",\n" +
                "\t\"loginType\": \"1\",\n" +
                "\t\"pwdOrVerifyCode\": \"huchao123\",\n" +
                "\t\"uaToken\": \"118#ZVWZz7ATWeoQ5Zgdle2/ZeZTZsZhBZ5nZZxPkz6hzHRzZvquVnq4I9NzZ2msVHR4Zg2ZMfqhzHh0gJZZ44W4zHZzZZYbXHWVZgqgZzuTzeRZZgZZ1Kq4zHZTZeChzeCVce4pLeTeO4HxzZCZ/zqVZe2zZZZTXHC2zx52paNTzeRZzgbZVoqYZoTtTeFhVHhHZZ2ZZ7qZzHRZZgCZXoqVzHZzZeuTVHW4ZgZZZOTizHRZZgCZVHq/zZ2zVYfu4HY/zurgrJ6WZNF5ZmzN2CK9ns9SFwX53eYV3BFXn3RXAypN73kZP/llyBYV6udrNeiDhWvl2ifpaPlIoQ32ob8zalpDnZ87ZZe3L4PXZZxqEeWLzFodZYiGhWm1YJ9DJxfUfSLncAQN8jIm8sSAdD7gr9e0ekehYIVRVtH8mZMG5qdE2UymcETozlBgUuwp6WM9ZTsHQjxEo0XVRq96Y1PHT9NVlP9UnaS8usosyR9LHbRDXWuli4R8tz6l7Wi1oM0vJQIn5lY6Hy9JwEICVBHjaEJrpgYf2iutpWtSyAglyxxKiTlH0Z1yBVmVLFYOlWUazA+OE3b9SLebTUZskuq6tnN2s0KnhL9gm72G6fZ5ARwpojMlTggJcOV1J5qGFHr95i7nkkyrzDGWv3QvT5pfX9vkMZnUpx/vr8YZfdJP63CvXduBXewAPhVNn7Pa0Eo3AM5BdHJisfo+Ia+odIZjf2pV2wLkVTg0HdRQf6wveyhrA8bIrZQFZbhRSOWjwNcjE5SQzdc/vzAY7aXaSrOuO/OZihehAVBMfGXQnRAkfkOBBriHLMaqmv2tUZjBacGu5l4/UhqPgvuHoWSZDl9O6MNM6EHowhl5z9O1k2b5SkR7pVY0It4Zr7wji+xTXzeoPMs9+wfts6QCx5xp3T3FHieecsWNQon/of3sIoVn77pHKePZAKm8tzeN/w/3oNHyR1SkcaISdYP5MSR/bPbrG0igwgLm9Q8ChQAkaMrpshDACw8fcCHe7NEcKqIj1tHDkBKebNL67jvRbDUlOMSSRPuq2vPfWd8KXstBPzBFN4uAHXkV2qojC9psV67Mf76NdLpqZv8xAu1VUHjxioL3W9pNJ1/Zd3Sb8WdiL40zOMlEVE7hrIUfVubTtjNELKTnkWZFH4ZSpi4FPoBog0f41zUEBVo7NDzH1TSipl5bD61AhbAJ6aV5/90ynfdG7/wg4NhBknWj8sUhpqrEubkMWyVO2k6b34mBrT9JofxLk3zKIpegNfBWEZzjR96vNeb+a6tsa2ud5hPONQ7eKrIIDDgBqHgvZcd8VdDyoJ/FHNK7CzpydyNd7Ks+DZqAENt+HCfc33c/H8pCijbHSm4UxxfGj1AF1or536vPX8iecG3wmTC0eQfEDisa3W6bYPxPMJt5Ny+mfNlxQpUmH3Q7Oxn74vQT0y3XVOfsn4STXqB0VRez/9R=\",\n" +
                "\t\"userIdentification\": \"14781835300\",\n" +
                "\t\"webUmidToken\": \"T20FD1F6D7B941162C10932DA16E79A940246BC865871B9FF8B0B49A5A2\"\n" +
                "}");
        StringEntity entity = new StringEntity(object.toString());
        entity.setContentEncoding("utf-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}