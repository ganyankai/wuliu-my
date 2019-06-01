package com.zrytech.framework.app.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WlZipUtils {


    private WlZipUtils() {
    }

    public static void doCompress(String srcFile, String zipFile) throws IOException {
        doCompress(new File(srcFile), new File(zipFile));
    }

    /**
     * 文件压缩
     *
     * @param srcFile 目录或者单个文件
     * @param zipFile 压缩后的ZIP文件
     */
    public static void doCompress(File srcFile, File zipFile) throws IOException {
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFile));
            doCompress(srcFile, out);
        } catch (Exception e) {
            throw e;
        } finally {
            out.close();//记得关闭资源
        }
    }

    public static void doCompress(String filelName, ZipOutputStream out) throws IOException {
        doCompress(new File(filelName), out);
    }

    public static void doCompress(File file, ZipOutputStream out) throws IOException {
        doCompress(file, out, "");
    }

    public static void doCompress(File inFile, ZipOutputStream out, String dir) throws IOException {
        if (inFile.isDirectory()) {
            File[] files = inFile.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    String name = inFile.getName();
                    if (!"".equals(dir)) {
                        name = dir + "/" + name;
                    }
                    WlZipUtils.doCompress(file, out, name);
                }
            }
        } else {
            WlZipUtils.doZip(inFile, out, dir);
        }
    }

    public static void doZip(File inFile, ZipOutputStream out, String dir) throws IOException {
        String entryName = null;
        if (!"".equals(dir)) {
            entryName = dir + "/" + inFile.getName();
        } else {
            entryName = inFile.getName();
        }
        ZipEntry entry = new ZipEntry(entryName);
        out.putNextEntry(entry);
        int len = 0;
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(inFile);
        while ((len = fis.read(buffer)) > 0) {
            out.write(buffer, 0, len);
            out.flush();
        }
        out.closeEntry();
        fis.close();
    }


    public static void main(String[] args) throws IOException {
        doCompress("D:/tmp", "D:/java.zip");
    }


    /**
     * @Desintion:多个链接地址:打包下载
     * @param:需要传响应HttpServletResponse
     * @Authro:jxx
     */
    public static void connnectDowload(HttpServletResponse response) {
        try {
            String filePath = "D:/";
            String param = "1";
            if ("1".equals(param)) {//多个文件压缩后下载
                //log.info("多个文件压缩后下载----------------------------");
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition", "attachment; filename=" + getZipFilename());

                //TODO:可变参数(可以从数据库中获取长链接地址)
                String wav = "http://47.94.173.245:8089/img/0e28151dcc38484d82a0730d7ce48fc9_1546570247208.jpg";//http://127.0.0.1:8080/LoadingDemo/20150522111534_35_8002_8002_to_015618587456.wav
                String img = "http://47.94.173.245:8089/img/90a122c76e264b04a31e58210de89f42_1546845705664.png";//.jpg
                try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {
                    //放入需下载文件地址
                    String[] strs = {wav, img};
                    zipFile(strs, "", zos);
                    zos.flush();
                    zos.close();
                }
            } else if ("2".equals(param)) { //一个文件直接下载
                downloadOnedFile(response, filePath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     * @Desinition:生成zip包的名称 这里是以当前时间为zip文件名
     * @Author:jxx
     */
    private static String getZipFilename() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String zipName = format.format(date.getTime()) + ".zip";
        return zipName;
    }


    /**
     * 压缩文件(通过长链接地址)
     *
     * @param strs     文件地址数组
     * @param baseName zip文件前缀名称
     * @param zos      zip输出流
     * @throws IOException
     */
    private static void zipFile(String[] strs, String baseName, ZipOutputStream zos)
            throws IOException {
        for (int i = 0; i < strs.length; i++) {
            URL url = new URL(strs[i]);
            URLConnection conn = url.openConnection();
            InputStream in = conn.getInputStream();
            String fileName = strs[i].substring(strs[i].lastIndexOf("/") + 1);//下载文件时显示的文件保存名称
            zos.putNextEntry(new ZipEntry(fileName));
            byte[] buffer = new byte[1024];
            int r = 0;
            while ((r = in.read(buffer)) != -1) {
                zos.write(buffer, 0, r);
            }
            in.close();
        }
    }

    /**
     * @Desinition:单个文件下载 注：这里指的是超链接文件下载
     * @param:request
     * @param:response
     * @throws:ServletException
     * @throws:IOException
     */
    public static void downloadOnedFile(HttpServletResponse response, String filePath) throws IOException {
        filePath = "http://47.94.173.245:8089/img/90a122c76e264b04a31e58210de89f42_1546845705664.png";//http://127.0.0.1:8080/LoadingDemo/20150522111534_35_8002_8002_to_015618587456.wav
        //log.info("===文件路径=====================>"+filePath);
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);//下载文件时显示的文件保存名称
        //log.info("===文件名=====================>"+fileName);
        response.setContentType("application/x-download");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        InputStream in = null;
        OutputStream outp = null;
        try {
            URL url = new URL(filePath);
            URLConnection conn = url.openConnection();
            in = conn.getInputStream();
            outp = response.getOutputStream();
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = in.read(b)) > 0) {
                outp.write(b, 0, i);
            }
            outp.flush();
        } catch (Exception e) {
            //log.error("录音下载时发生异常：" ,e);
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
                in = null;
            }
            if (outp != null) {
                outp.close();
                outp = null;
            }
        }
    }
}
