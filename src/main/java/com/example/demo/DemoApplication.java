package com.example.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        CamelContext myContext = new DefaultCamelContext();
        CamelRoute myRoute = new CamelRoute();

        try {
            myContext.addRoutes(myRoute);
            myContext.start();
            Endpoint endpoint  = myContext.getEndpoint("rabbitmq://localhost:5672/myrabbitexchange?username=guest&password=guest&autoDelete=false&routingKey=camel&queue=myrabbitqueue");
            Thread.sleep(5000);
            myContext.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SpringApplication.run(DemoApplication.class, args);
    }

}
