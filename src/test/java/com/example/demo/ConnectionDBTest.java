package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConnectionDBTest {
    
    @Test
    void DBConTest() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/routine", "root", "vbn752014&");

            System.out.println("ConnectionDBTest.DBConTest()");
            System.out.println("con : " + con);

        } catch (Exception e) {
            System.out.println("ConnectionDBTest.DBConTest() FAIL");
        }
    }
}
