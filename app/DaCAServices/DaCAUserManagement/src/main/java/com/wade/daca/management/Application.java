package com.wade.daca.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.wade.daca.management")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
