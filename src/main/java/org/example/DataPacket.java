package org.example;

public class DataPacket {
    public String id;
    public int packet_no;
    public double temperature;
    public double waterLevel;

    public DataPacket(String id, int packet_no, double temperature, double waterLevel) {
        this.id = id;
        this.packet_no = packet_no;
        this.temperature = temperature;
        this.waterLevel = waterLevel;
    }
}
