package tw.com.softleader.training;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Demo4 {

    @SneakyThrows
    public static void main(String[] args) {

        LocalDateTime start = LocalDateTime.now();
        System.out.println("start");

        try (InputStream in = new FileInputStream("X:\\in\\TIB_js-studiocomm_6.17.0_windows_x86_64.zip");
            OutputStream out = new FileOutputStream("X:\\out\\TIB_js-studiocomm_6.17.0_windows_x86_64.zip")
        ) {
            in.transferTo(out);
        }
        System.out.println("done " + ChronoUnit.MILLIS.between(start, LocalDateTime.now()));

    }
}
