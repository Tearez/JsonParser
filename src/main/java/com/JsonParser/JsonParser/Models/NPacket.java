package com.JsonParser.JsonParser.Models;

public class NPacket {
    private String stationName;
    private double power;

    public NPacket(String stationName, double power) {
        this.stationName = stationName;
        this.power = power;
    }

    public NPacket() {
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return String.format("%s, %g", this.stationName, this.power);
    }

    @Override
    public boolean equals(Object obj) {
        NPacket compare = (NPacket) obj;
        return this.stationName.equals(compare.getStationName()) && this.power == compare.getPower();
    }
}
