package tw.com.softleader.training;

import lombok.SneakyThrows;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Demo5 {

    @SneakyThrows
    public static void main(String[] args) {

        Thread.sleep(5000);
        Thread.sleep(1000);

        LocalDateTime start = LocalDateTime.now();
        System.out.println("start");

        try (OutputStream out = Files.newOutputStream(Paths.get("X:\\out\\TIB_js-studiocomm_6.17.0_windows_x86_64.zip"))
        ) {
            Files.copy(Paths.get("X:\\in\\TIB_js-studiocomm_6.17.0_windows_x86_64.zip"), out);
        }
        System.out.println("done " + ChronoUnit.MILLIS.between(start, LocalDateTime.now()));

        Thread.sleep(30000);
    }
}
