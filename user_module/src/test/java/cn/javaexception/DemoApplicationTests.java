package cn.javaexception;

import cn.javaexception.entity.Message;
import cn.javaexception.mapper.UserMapper;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
        FileInfo fileInfo = client1.query_file_info1("group1/M00/00/00/rBAAClzmrAyAGA8CAAILeb7p8n4927.jpg");
        System.out.println(fileInfo);

        trackerServer.close();
    }


    @Test
    public void demo() throws IOException {
        OkHttpClient client = new OkHttpClient();
        //设置请求头编码
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        //设置请求体
        RequestBody body = RequestBody.
                create(mediaType,
                "__VIEWSTATE=oQpJYHwQjAYU58GDhj8pGyPga7BNMD76llnV1Fwh2j7EmjLV92YIJhOxftIcOTeGGX%2FEfq8ECVHGeLodSK7yqjYgC2Nfb9jdOEr0CpfimYwLVQ7FgqjdzwgREQx6BXQEQlJg1dtDhTd%2FCjWv9vPx8MtM5YamPBh3bFdPSlHjiWlXigxsGnOGaqnH6cYc48M8&__VIEWSTATEGENERATOR=41A7F112&ctl00%24mainContent%24Username=lan%40petrochinatidal&ctl00%24mainContent%24Password=lan123c&ctl00%24mainContent%24UserLogin=Login&__EVENTVALIDATION=z50k6d10gPyE%2B0vhxL7qL6wjkszfyMfpfhPC7SCTB62kPCfVYhAvkopfkJ8zPXTRjG1PmaDQ3aemrLdmjyexlqkBtmxt%2B4WiM6%2BqAWdOC4SwEqgITdG4Y6ZLPoka93yrARUbV%2BwTgCt6R8ddky%2B%2FW8QJ0afGSBwFGJ2ChgYLJ3Qf%2BurWxjmjgH7z2qVT%2BFzb");
        //执行登录
        Request login = new Request.Builder()
                .url("https://data-metricorr.com/IdentityAccount/Login.aspx")
                .post(body)
                .build();
        Response response = client.newCall(login).execute();
        //登陆后获取Cookie并解析
        List<String> list = response.headers("Set-Cookie");
        StringBuilder cookies = new StringBuilder();
        for (String str : list) {
            str = str.substring(0, str.indexOf(";") + 1);
            cookies.append(str);
        }
        //现在登录成功了，想发啥请求都可以了 根据url 请求方式，想咋弄就咋弄
        Request request = new Request.Builder()
                .header("cookie",cookies.toString())
                .get()
                .url("https://data-metricorr.com/Mvc/Upload").build();
        Response result = client.newCall(request).execute();
        assert result.body() != null;
        System.out.println(result.body().string());
    }
}