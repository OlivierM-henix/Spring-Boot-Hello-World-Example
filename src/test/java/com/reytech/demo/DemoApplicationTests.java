package com.reytech.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@DisplayName("A special test case")
//@Disabled("All test in this class will be skipped")
@TestMethodOrder(OrderAnnotation.class) // il faut minimum la version 5.4 pour gérer l'ordre
@SpringBootTest
class DemoApplicationTests {

	 @BeforeAll
    static void setupAll() {
        System.out.println("BeforeAll");
    }

    @BeforeEach
    void setup() {
        System.out.println("BeforeEach");
    }

    @Test
    @Order(2)
    @DisplayName("Test 1")
    void testOne() {
        System.out.println("TEST 1");
    }
    
    @Test 
    @Order(1)
    @DisplayName("Test 2")
    void testTwo() {
        System.out.println("TEST 2");
    }

    @AfterEach
    void teardown() {
        System.out.println("AfterEach");
    }

    @AfterAll
    static void teardownAll() {
        System.out.println("AfterAll");
    }

}
