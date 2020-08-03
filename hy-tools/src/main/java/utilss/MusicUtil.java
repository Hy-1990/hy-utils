package utilss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author huyi
 * @Date 2020/7/16 15:29
 * @Description
 */
public class MusicUtil {
    private static final Logger logger = LoggerFactory.getLogger(MusicUtil.class);

    public static void sayPlay(String url, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置响应内容类型
        response.setHeader("Content-type", "text/html; charset=UTF-8");
        //解决乱码
        request.setCharacterEncoding("UTF-8");
        //解决乱码
        response.setContentType("text/html;charset=UTF-8");
        //输出 wav IO流
        response.setHeader("Content-Type", "audio/mpeg");

        File file = new File(url);
        //判断文件父目录是否存在
        if (file.exists()) {
            FileInputStream fis = null;
            OutputStream out = null;
            try {
                int len_l = (int) file.length();
                byte[] buf = new byte[2048];
                fis = new FileInputStream(file);
                out = response.getOutputStream();
                len_l = fis.read(buf);
                while (len_l != -1) {
                    out.write(buf, 0, len_l);
                    len_l = fis.read(buf);
                }
            } catch (Exception e) {
                logger.error("下载音频失败, e");
            } finally {
                try {
                    out.flush();
                    out.close();
                    fis.close();
                } catch (IOException e) {
                    logger.error("下载音频关闭流失败, e");
                }
            }
        }
    }

    public static void saveFile(String url, MultipartFile multipartFile) throws IOException {
        File file = new File(url);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        InputStream sis = null;
        FileOutputStream fos = null;
        try {
            sis = multipartFile.getInputStream();
            fos = new FileOutputStream(file);
            byte[] content = new byte[1024];
            int len = 0;
            while ((len = sis.read(content)) > -1) {
                fos.write(content, 0, len);
            }
            fos.flush();
        } finally {
            sis.close();
            fos.close();
        }
    }
}
