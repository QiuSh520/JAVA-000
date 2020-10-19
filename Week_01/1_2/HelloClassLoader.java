import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader{

    private File helloFile;

    public HelloClassLoader(File helloFile) {
        this.helloFile = helloFile;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        long length = helloFile.length();
        byte[] bytes=new byte[(int) length];

        try {
            FileInputStream fileInputStream=new FileInputStream(helloFile);
            fileInputStream.read(bytes,0, (int) length);
        } catch (FileNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage());
        } catch (IOException e) {
            throw new ClassNotFoundException(e.getMessage());
        }

        for (int i=0;i<bytes.length;i++) {
            bytes[i]= (byte) (255-bytes[i]);
        }

        return defineClass(name,bytes,0,bytes.length);
    }

    public static void main(String[] args) throws Exception {
        String fileStr = getSystemClassLoader().getResource("Hello.xlass").getFile();
        System.out.println(fileStr);
        File file=new File(fileStr);
        Class<?> helloClazz = new HelloClassLoader(file).findClass("Hello");
        Object instance = helloClazz.newInstance();
        Method helloMethod= helloClazz.getDeclaredMethod("hello");
        helloMethod.invoke(instance);
    }
}
