package tw.hacker.java;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class MyBean
{
    public static void main(String[] args)
    {
        SpringApplication.run(MyBean.class, args);
    }
}