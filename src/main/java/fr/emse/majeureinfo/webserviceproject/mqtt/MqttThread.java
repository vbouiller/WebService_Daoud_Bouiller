package fr.emse.majeureinfo.webserviceproject.mqtt;

import org.eclipse.paho.client.mqttv3.*;


/*

public class MqttThread implements  Runnable {
    String broker="tcp://m23.cloudmqtt.com:13655";
    String philipshue="philipshue/";
    MqttClient client=null;

    private Thread thread;
    private volatile boolean someCondition;

    public MqttThread(){
        this.thread = new Thread(this);
    }

    @Override
    public void run(){
        MqttConnector.Connection (broker,client,philipshue,"");
        while(someCondition){
            //doStuff();
        }
    }
    public void destroy(){
        someCondition = false;
    }




}*/