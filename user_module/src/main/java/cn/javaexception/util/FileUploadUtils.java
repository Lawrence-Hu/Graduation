package cn.javaexception.util;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileUploadUtils {
    private static  StorageClient1 client1 = null;

    static {
        try {
            ClientGlobal.initByProperties("config/fastdfs-client.properties");
            TrackerClient client = new TrackerClient();
            TrackerServer trackerServer = client.getConnection();
            client1 = new StorageClient1(trackerServer,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static JsonData upload(MultipartFile[] files) {
        List<String> paths = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                byte[] bytes = file.getBytes();
                String suffix = Objects.requireNonNull(file.getOriginalFilename())
                        .substring(Objects.requireNonNull(file.getOriginalFilename())
                                .lastIndexOf(".") + 1);
                if (suffix.equals(file.getOriginalFilename())) {
                    return JsonData.buildError("文件后缀格式错误！");
                }
                String path = client1.upload_file1(bytes, suffix, null);
                System.out.println(file.getOriginalFilename());
                paths.add(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return JsonData.buildSuccess(paths);
    }

    public static JsonData qureyFile(String fileName) throws IOException, MyException {
        FileInfo fileInfo = client1.query_file_info1(fileName);
        return JsonData.buildSuccess(fileInfo);
    }

    public static JsonData deleteFile(String fileName){
        int i;
        try {
            i = client1.delete_file1(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("删除文件失败！");
        }
        return i>0?JsonData.buildSuccess("删除成功！"):JsonData.buildError("删除失败！");
    }
}
