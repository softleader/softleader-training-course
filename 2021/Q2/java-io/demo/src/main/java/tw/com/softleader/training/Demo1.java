package tw.com.softleader.training;

import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Demo1 {

    @SneakyThrows
    public static void main(String[] args) {
        String source = "ABCabc中文1";
        byte[] bytes = source.getBytes(StandardCharsets.UTF_8);

//        try (OutputStream out = System.out) {
//            out.write(bytes);
//        }
        OutputStream out = System.out;
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        int bufferLength = 4;
        byte[] buffer = new byte[bufferLength];

        while (bin.available() > 0) {
            int readed = bin.read(buffer, 0, bufferLength);
            out.write(buffer, 0, readed);
        }

    }

}
