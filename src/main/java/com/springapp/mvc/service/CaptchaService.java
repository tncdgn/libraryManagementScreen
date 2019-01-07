package com.springapp.mvc.service;


import com.springapp.mvc.exception.RecaptchaServiceException;
import com.springapp.mvc.model.RecaptchaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;



@Service("captchaService")
public class CaptchaService {


    RestTemplate restTemplate;
    @Value( "${recaptcaSecretKey}" )
    String recaptchaSecretKey;

    @Value( "${recaptcaUrl}" )
    String recaptchaUrl;

    public boolean isResponseValid(HttpServletRequest request, String response) {
        RecaptchaResponse recaptchaResponse;
        if (response==null ||  response.equals("")){
            return false;
        }
        try {
            restTemplate=new RestTemplate();
            MultiValueMap<String, String> captchaBody = createBody(recaptchaSecretKey, getRemoteIp(request), response);
            recaptchaResponse = restTemplate.postForEntity(recaptchaUrl,captchaBody, RecaptchaResponse.class).getBody();
        } catch (RestClientException e) {
            throw new RecaptchaServiceException("Recaptcha API not available exception",e);

        }
        if (recaptchaResponse.isSuccess()) {
            return true;
        } else {
            return false;
        }

    }

    private MultiValueMap<String, String> createBody(String secret, String remoteIp, String response) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        form.add("secret", secret);
        form.add("remoteip", remoteIp);
        form.add("response", response);
        return form;
    }
    private String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

	
}
