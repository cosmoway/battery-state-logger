package net.cosmoway.batterystatelogger.models;

import android.os.BatteryManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BatteryState {

    private Date mTimestamp;
    private int mStatus;
    private int mHealth;
    private boolean mPresent;
    private int mLevel;
    private int mScale;
    private int mIconSmall;
    private int mPlugged;
    private int mVoltage;
    private int mTemperature;
    private String mTechnology;

    public Date getTimestamp() {
        return mTimestamp;
    }

    public int getStatus() {
        return mStatus;
    }

    public String getStatusString() {
        String statusString = "";

        switch (mStatus) {
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                statusString = "unknown";
                break;
            case BatteryManager.BATTERY_STATUS_CHARGING:
                statusString = "charging";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                statusString = "discharging";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                statusString = "not charging";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                statusString = "full";
                break;
        }

        return statusString;
    }

    public int getHealth() {
        return mHealth;
    }

    public String getHealthString() {
        String healthString = "";

        switch (mHealth) {
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                healthString = "unknown";
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                healthString = "good";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                healthString = "overheat";
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                healthString = "dead";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                healthString = "over mVoltage";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                healthString = "unspecified failure";
                break;
        }

        return healthString;
    }

    public boolean isPresent() {
        return mPresent;
    }

    public int getLevel() {
        return mLevel;
    }

    public int getScale() {
        return mScale;
    }

    public int getIconSmall() {
        return mIconSmall;
    }

    public int getPlugged() {
        return mPlugged;
    }

    public String getPluggedString() {
        String pluggedString = "";

        switch (mPlugged) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                pluggedString = "mPlugged ac";
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                pluggedString = "mPlugged usb";
                break;
        }

        return pluggedString;
    }

    public int getVoltage() {
        return mVoltage;
    }

    public int getTemperature() {
        return mTemperature;
    }

    public String getTechnology() {
        return mTechnology;
    }

    public void setTimestamp(Date timestamp) {
        mTimestamp = timestamp;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public void setHealth(int health) {
        mHealth = health;
    }

    public void setPresent(boolean present) {
        mPresent = present;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    public void setScale(int scale) {
        mScale = scale;
    }

    public void setIconSmall(int iconSmall) {
        mIconSmall = iconSmall;
    }

    public void setPlugged(int plugged) {
        mPlugged = plugged;
    }

    public void setVoltage(int voltage) {
        mVoltage = voltage;
    }

    public void setTemperature(int temperature) {
        mTemperature = temperature;
    }

    public void setTechnology(String technology) {
        mTechnology = technology;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        String timestamp = sdf.format(mTimestamp);
        return String.format(Locale.ENGLISH, "%s,%3d,%4d,%5d,%s,%s",
                timestamp, mLevel * 100 / mScale, mTemperature, mVoltage, getHealthString(),
                getStatusString());
    }

}
