package com.example.controller;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.Response;
import com.example.dto.CaptchaRequest;
import com.example.dto.CaptchaResponse;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    private final AdminController adminController;

    @Autowired
    private StringRedisTemplate redisTemplate;

    Response response = null;

    CaptchaController(AdminController adminController) {
        this.adminController = adminController;
    }

    @GetMapping("/generate")
    public ResponseEntity<Response> generateCaptcha() {
        try {
            String captcha = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
            String key = UUID.randomUUID().toString();

            redisTemplate.opsForValue().set(key, captcha, 5, TimeUnit.MINUTES);

            // return new CaptchaResponse(key, captcha);
            response = new Response(200, "Captcha generated successfully", new CaptchaResponse(key, captcha));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            response = new Response(400, "captcha generation failed",
                    "Error while generating Captcha.Try again after sometime.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/verify")
    public ResponseEntity<Response> verifyCaptcha(@RequestBody CaptchaRequest request) {
      System.out.println(request);
        try {
            String storedCaptcha = redisTemplate.opsForValue().get(request.getKey());

            if (storedCaptcha != null && storedCaptcha.equalsIgnoreCase(request.getCaptcha())) {
                redisTemplate.delete(request.getKey());
                response = new Response(200, "Captcha verified successfully", true);
                return new ResponseEntity<>(response, HttpStatus.OK);
                
            }
            response = new Response(200, "Captcha verified successfully", false);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = new Response(400, "captcha verification failed",
                    "Error while verifying Captcha.Try again after sometime.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}