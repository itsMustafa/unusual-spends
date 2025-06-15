package com.scw.ddd.unusual.spends.config;

public class UnusualSpendsConfig {

    private double percentage;

    public UnusualSpendsConfig(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
