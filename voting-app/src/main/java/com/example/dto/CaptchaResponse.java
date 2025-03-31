package com.example.dto;

public class CaptchaResponse {
    private String key;
    private String captcha;

    public CaptchaResponse(String key, String captcha) {
        this.key = key;
        this.captcha = captcha;
    }

    public String getKey() {
        return key;
    }

    public String getCaptcha() {
        return captcha;
    }
}
