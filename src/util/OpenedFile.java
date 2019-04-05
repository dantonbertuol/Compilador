package util;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class - responsável por declarar o arquivo aberto para edição
 * @author Daniel, Danton e Adrian
 * @since 22/10/2017
 */
public class OpenedFile {
    private byte[] file;
    private Path path;
    private String name;

    public OpenedFile(){
        this.file = null;
        this.path = Paths.get(System.getProperty("user.dir")+"\\newFile.c");
        this.name = null;
    }
    
    public OpenedFile(byte[] file, Path path, String name) {
        this.file = file;
        this.path = path;
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
