package com.example.dto;

public class CaptchaRequest {
    private String key;
    private String captcha;

    public String getKey() {
        return key;
    }

    public String getCaptcha() {
        return captcha;
    }

    @Override
    public String toString() {
        return "CaptchaRequest [key=" + key + ", captcha=" + captcha + "]";
    }
}
