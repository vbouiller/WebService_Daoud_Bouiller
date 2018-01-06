package fr.emse.majeureinfo.webserviceproject;

import fr.emse.majeureinfo.webserviceproject.mqtt.MqttThread;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup
        implements ApplicationListener<ApplicationReadyEvent> {

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        // here your code ...
        MqttThread mqttThread=new MqttThread();
        mqttThread.run();

        return;
    }

} // class
