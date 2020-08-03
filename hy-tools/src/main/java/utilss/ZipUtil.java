package utilss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author huyi
 * @Date 2020/7/16 15:30
 * @Description
 */
public class ZipUtil {
    private static final Logger logger = LoggerFactory.getLogger(ZipUtil.class);

    /**
     * 把文件打成压缩包并输出到客户端浏览器中
     */
    public static void downloadZipFiles(HttpServletResponse response, List<String> srcFiles, String zipFileName) {
        try {
            response.reset(); // 重点突出
            response.setCharacterEncoding("UTF-8"); // 重点突出
            response.setContentType("application/zip;charset=utf-8"); // 不同类型的文件对应不同的MIME类型 // 重点突出
            // 对文件名进行编码处理中文问题
            zipFileName = new String(zipFileName.getBytes(), StandardCharsets.UTF_8);
            // inline在浏览器中直接显示，不提示用户下载
            // attachment弹出对话框，提示用户进行下载保存本地
            // 默认为inline方式
            response.setHeader("Content-Disposition", "attachment;filename=" + zipFileName + ".zip");
            // --设置成这样可以不用保存在本地，再输出， 通过response流输出,直接输出到客户端浏览器中。
            zipFilePip(srcFiles, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩文件
     *
     * @param filePaths 需要压缩的文件路径集合
     * @throws IOException
     */
    private static void zipFile(List<String> filePaths, ZipOutputStream zos) {
        //设置读取数据缓存大小
        byte[] buffer = new byte[4096];
        try {
            //循环读取文件路径集合，获取每一个文件的路径
            for (String filePath : filePaths) {
                File inputFile = new File(filePath);
                //判断文件是否存在
                if (inputFile.exists()) {
                    //判断是否属于文件，还是文件夹
                    if (inputFile.isFile()) {
                        //创建输入流读取文件
                        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
                        //将文件写入zip内，即将文件进行打包
                        zos.putNextEntry(new ZipEntry(inputFile.getName()));
                        //写入文件的方法，同上
                        int size = 0;
                        //设置读取数据缓存大小
                        while ((size = bis.read(buffer)) > 0) {
                            zos.write(buffer, 0, size);
                        }
                        //关闭输入输出流
                        zos.closeEntry();
                        bis.close();
                    } else {  //如果是文件夹，则使用穷举的方法获取文件，写入zip
                        File[] files = inputFile.listFiles();
                        List<String> filePathsTem = new ArrayList<>();
                        for (File fileTem : files) {
                            filePathsTem.add(fileTem.toString());
                        }
                        zipFile(filePathsTem, zos);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("下载失败", e);
        } finally {
            if (null != zos) {
                try {
                    zos.close();
                } catch (IOException e) {
                    logger.error("下载失败", e);
                }
            }
        }
    }

    /**
     * 压缩文件
     *
     * @param filePaths
     * @param zos
     */
    private static void zipFileChannel(List<String> filePaths, ZipOutputStream zos) {
        //开始时间
        try (WritableByteChannel writableByteChannel = Channels.newChannel(zos)) {
            //循环读取文件路径集合，获取每一个文件的路径
            for (String filePath : filePaths) {
                File inputFile = new File(filePath);
                //判断文件是否存在
                if (inputFile.exists()) {
                    //判断是否属于文件，还是文件夹
                    if (inputFile.isFile()) {
                        try (FileChannel fileChannel = new FileInputStream(filePath).getChannel()) {
                            zos.putNextEntry(new ZipEntry(inputFile.getName()));
                            fileChannel.transferTo(0, fileChannel.size(), writableByteChannel);
                        }
                    } else {  //如果是文件夹，则使用穷举的方法获取文件，写入zip
                        File[] files = inputFile.listFiles();
                        List<String> filePathsTem = new ArrayList<>();
                        for (File fileTem : files) {
                            filePathsTem.add(fileTem.toString());
                        }
                        zipFileChannel(filePathsTem, zos);
                    }
                }
            }

        } catch (Exception e) {
            logger.error("下载失败", e);
        } finally {
            if (null != zos) {
                try {
                    zos.close();
                } catch (IOException e) {
                    logger.error("下载失败", e);
                }
            }
        }
    }

    //Version 5 使用Pip
    private static void zipFilePip(List<String> filePaths, OutputStream out) {
        try (WritableByteChannel writableByteChannel = Channels.newChannel(out)) {
            Pipe pipe = Pipe.open();
            //异步任务
            CompletableFuture.runAsync(() -> runTask(filePaths, pipe));
            //获取读通道
            ReadableByteChannel readableByteChannel = pipe.source();
            ByteBuffer buffer = ByteBuffer.allocate(4096 * 10);
            while (readableByteChannel.read(buffer) >= 0) {
                buffer.flip();
                writableByteChannel.write(buffer);
                buffer.clear();
            }
        } catch (Exception e) {
            logger.error("下载失败", e);
        }
    }

    //异步任务
    private static void runTask(List<String> filePaths, Pipe pipe) {
        try (ZipOutputStream zos = new ZipOutputStream(Channels.newOutputStream(pipe.sink()));
             WritableByteChannel writableByteChannel = Channels.newChannel(zos)) {
            //循环读取文件路径集合，获取每一个文件的路径
            for (String filePath : filePaths) {
                File inputFile = new File(filePath);
                //判断文件是否存在
                if (inputFile.exists()) {
                    //判断是否属于文件，还是文件夹
                    if (inputFile.isFile()) {
                        try (FileChannel fileChannel = new FileInputStream(filePath).getChannel()) {
                            zos.putNextEntry(new ZipEntry(inputFile.getName()));
                            fileChannel.transferTo(0, fileChannel.size(), writableByteChannel);
                        }
                    }
                } else {  //如果是文件夹，则使用穷举的方法获取文件，写入zip
                    File[] files = inputFile.listFiles();
                    List<String> filePathsTem = new ArrayList<>();
                    for (File fileTem : files) {
                        filePathsTem.add(fileTem.toString());
                    }
                    runTask(filePathsTem, pipe);
                }
            }
        } catch (Exception e) {
            logger.error("下载失败", e);
        }
    }
}
