package fr.emse.majeureinfo.webserviceproject;

import fr.emse.majeureinfo.webserviceproject.mqtt.MqttConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebserviceProjectApplication {

	public static void main(String[] args) {
		MqttConnector.Connection("tcp://m23.cloudmqtt.com:13655","#");
		SpringApplication.run(WebserviceProjectApplication.class, args);
	}
}
