package com.example.demo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.jndi.JndiContext;
import org.springframework.stereotype.Component;

@Component
public class CamelRoute  extends RouteBuilder {

    @Override
    public void configure() throws Exception
    {
        MyTransformation myCase = new MyTransformation();
        JndiContext jndiContext = new JndiContext();
        jndiContext.bind("uppercase", new MyTransformation());
        String url = "rabbitmq:localhost:5672/myrabbitexchange?username=guest&password=guest&autoDelete=false&routingKey=camel&queue=myrabbitqueue";
        from(url).log("the original message is ${body}")
                .bean(myCase, "toUpper")
                .log("Transformed message is ${body}")
                //.transform(method("uppercase"))
                .to("file:data/file");
    }
}
