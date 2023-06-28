package com.example.spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@SpringBootTest
class SpringBootSampleApplicationTests {

    @Test
    void contextLoads() {
        Map<String, String> testMap = new HashMap<>();

        testMap.put("01", "cat");
        testMap.put("02", "dog");

        System.out.printf(testMap.toString());
    }

}
