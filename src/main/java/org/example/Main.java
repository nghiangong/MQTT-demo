package org.example;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        System.out.println("MQTT Demo!");
        new Main().doDemo();
    }
    public void doDemo() {
        String subTopic = "/ktmt/nghia";
        String pubTopic = "/ktmt/nghia";
        String content = "Hello world";
        int qos = 1;
        String broker = "tcp://broker.hivemq.com:1883";
        String clientId = "1";
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            client.setCallback(new OnMessageCallBack());
            System.out.println("Connecting to broker: " + broker);
            client.connect(connOpts);
            System.out.println("Connected");
            System.out.println("----------------------------");

            client.subscribe(subTopic);

            DataPacket dataPacket = new DataPacket(clientId, 1, 27.8, 500);
            Gson gson = new Gson();
            MqttMessage message = new MqttMessage(gson.toJson(dataPacket).getBytes());
            message.setQos(qos);
            System.out.println("Publishing message: " + message.toString());
            client.publish(pubTopic, message);
            System.out.println("Message published");
            System.out.println("----------------------------");

            Thread.sleep(60000);

            client.disconnect();
            System.out.println("Disconnected");
            client.close();
            System.exit(0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}