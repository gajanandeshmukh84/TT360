package org.team.tt360app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tt360AppApplication {

    public static void main(String[] args) {

        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(Tt360AppApplication.class, args);
    }

}
