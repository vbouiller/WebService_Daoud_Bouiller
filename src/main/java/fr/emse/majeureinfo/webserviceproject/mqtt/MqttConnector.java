package fr.emse.majeureinfo.webserviceproject.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttConnector {
    public static void Connection(String broker, MqttClient client, String topic, String message){

        try {

            //Instantiate MqttClient
            client = new MqttClient(
                    broker, //URI
                    MqttClient.generateClientId(), //ClientId
                    new MemoryPersistence()); //Persistence

            // Connection credentials
            MqttConnectOptions options = new MqttConnectOptions();
                /*options.setUserName("mxasjcaz");
                options.setPassword("6YcXZea6Z1eu".toCharArray());*/
            options.setUserName("mqttApiAccount");
            options.setPassword("hYZO!72dja".toCharArray());


            // Callback triggered when receiving a new message
            client.setCallback(new MqttCallback() {

                @Override
                public void connectionLost(Throwable cause) { //Called when the client lost the connection to the broker
                    System.out.println("-- Connection List");
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    //System.out.println(topic + ": " + message.toString());
                    handleNewMessage(topic, message.toString());
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {//Called when a outgoing publish is complete
                    System.out.println("-- Message sent w/ token: " + token);
                }
            });


            client.connect(options);
            client.subscribe("#");

        } catch (MqttException e) {
            e.printStackTrace();
        }


        try {

            client.publish(
                    "RPi/MqttAdapter/connexion", // topic
                    "Successfully connected - From Api".getBytes(), // payload
                    2, // QoS
                    false); // retained?
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    protected static void handleNewMessage(String topic, String message){
        System.out.println(topic + ": " + message);


    }
    public static void publish(MqttClient client, String topic, String message){
        try {

            //Instantiate MqttClient
            client = new MqttClient(
                    "tcp://m23.cloudmqtt.com:13655", //URI
                    MqttClient.generateClientId(), //ClientId
                    new MemoryPersistence()); //Persistence

            // Connection credentials
            MqttConnectOptions options = new MqttConnectOptions();
                /*options.setUserName("mxasjcaz");
                options.setPassword("6YcXZea6Z1eu".toCharArray());*/
            options.setUserName("mqttApiAccount");
            options.setPassword("hYZO!72dja".toCharArray());
            client.connect(options);
            //message
            MqttMessage msg = new MqttMessage(message.getBytes());
            msg.setQos(2);
            client.publish(topic, msg);
            System.out.println("Message published");
        }
        catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
