package com.jesusfc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.jms.annotation.EnableJms;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableJms
public class Spb3Java17JmsKafkaService1Application {

    public static void main(String[] args) {

        // Launch the application
/*		ConfigurableApplicationContext context = SpringApplication.run(Spb3Java17JmsKafkaApplication.class, args);

		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		// Send a message with a POJO - the template reuse the message converter
		System.out.println("Sending an email message.");

		JmsMessage jmsMessage = JmsMessage.builder()
				.uuid(UUID.randomUUID())
				.to("jfcaraballo@gmail.com")
				.message("Hello there")
				.body("Super Body").build();

		jmsTemplate.convertAndSend(JmsMessageConfig.MY_QUEUE, jmsMessage);

	}
*/

        SpringApplication.run(Spb3Java17JmsKafkaService1Application.class, args);
    }
}
