package com.zrytech.framework.app.ftp;

import com.zrytech.framework.app.domain.File;

import java.io.InputStream;
import java.io.OutputStream;

public interface WlIFtp {

    /**
     * @param describer 远程文件名称,文件名必须唯一,若出现重名则以.1,.2,.3来区分
     * @param inputStream
     */
    void storeUniqueFile(File describer, InputStream inputStream);

    /**
     * 给定路径的文件将会被删除
     * @param describer
     * @return  成功返回true,否则返回false
     */
    boolean deleteFile(File describer);

    /**
     *
     * @param describer 远程文件名称
     * @return  进行远程写操作，上传完毕，需检查completePendingCommand()，完成手动关闭输入流
     */
    OutputStream storeFileStream(File describer);


    /**
     * @param describer
     * @param local  下载完成后 需手动close()
     * @return
     */
    boolean  retrieveFile(File describer, OutputStream local);


    /**
     * 需要手动检查completePendingCommand()状态，是否下载完毕
     * @param describer
     * @return
     */
    InputStream retrieveFileStream(File describer);


}
