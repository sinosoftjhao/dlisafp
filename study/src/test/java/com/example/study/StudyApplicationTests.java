package com.example.study;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudyApplicationTests {
    @Value("${names}")
    String name;
    @Value("${age}")
    Integer age;

    @Test
    void contextLoads() {
        System.out.println(name + age);
    }

}
