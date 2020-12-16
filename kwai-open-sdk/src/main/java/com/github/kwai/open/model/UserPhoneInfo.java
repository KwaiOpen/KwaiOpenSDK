package com.github.kwai.open.model;

/**
 * @author wuge wuge@kuaishou.com
 * Created on 2020-12-14
 */
public class UserPhoneInfo {

    private String countryCode;
    private String phoneNumber;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserPhoneInfo{");
        sb.append("countryCode='").append(countryCode).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
