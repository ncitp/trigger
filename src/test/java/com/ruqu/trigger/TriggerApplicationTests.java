package com.ruqu.trigger;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

@Slf4j
@SpringBootTest
class TriggerApplicationTests {

    @Test
    void contextLoads() throws IOException {

        String url = "192.168.0.3";
        Integer post = 8234;

        Socket socket = new Socket(url, post);
        OutputStream os = socket.getOutputStream();
        os.write("33".getBytes());
    }

}
