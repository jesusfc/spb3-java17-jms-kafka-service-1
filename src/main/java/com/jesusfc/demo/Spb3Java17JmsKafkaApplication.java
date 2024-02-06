package com.jesusfc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spb3Java17JmsKafkaApplication {

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

        SpringApplication.run(Spb3Java17JmsKafkaApplication.class, args);
    }
}
