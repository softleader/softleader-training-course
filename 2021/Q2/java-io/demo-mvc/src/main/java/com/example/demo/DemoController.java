package com.example.demo;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class DemoController {

    @SneakyThrows
    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        response.setHeader("Content-disposition", "attachment; filename=TIB_js-studiocomm_6.17.0_windows_x86_64.zip");
        response.setContentType("application/zip");

        ServletOutputStream outputStream = response.getOutputStream();
        Files.copy(Paths.get("X:\\in\\TIB_js-studiocomm_6.17.0_windows_x86_64.zip"), outputStream);
    }

}
