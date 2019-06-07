package com.lambdaschool.apigdp;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.DispatcherServlet;

@EnableWebMvc
@SpringBootApplication
public class ApigdpApplication
{
    public static final String EXCHANGE_NAME= "LambdaServer";
    public static final String QUEUE_NAME_LOW= "LowPriorityQueue";
    public static final String QUEUE_NAME_HIGH= "HighPriorityQueue";

    public static CountryList ourCountryList;
    public static void main(String[] args)
    {
        ourCountryList = new CountryList();
        SpringApplication.run(ApigdpApplication.class, args);

       // DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
        //dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }

}
