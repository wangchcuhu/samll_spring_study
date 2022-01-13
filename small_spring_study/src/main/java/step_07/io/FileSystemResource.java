package step_07.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: small_spring_study
 * @description:本地文件加载
 * @author: Z-Man
 * @create: 2022-01-10 10:10
 */
public class FileSystemResource implements Resource {
    private final String path;
    private File file;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    public FileSystemResource(String path) {
        this.path = path;
        //使用路径加载资源,实际上没有出路给的路径是错误的,加载的路径不对
        this.file = new File(path);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    public final String getPath(){
        return this.path;
    }
}
