package com.entrepiscoynazca.appweb.integration.api;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.entrepiscoynazca.appweb.integration.dto.*;

@Service
public class SpotifyAPI{

    //curl -X "GET" "https://api.spotify.com/v1/me" 
    //-H "Accept: application/json" 
    //-H "Content-Type: application/json" 
    //-H "Authorization: Bearer BQAgziFhDi3UjKMoyXslaY2ZHk9rxsz3Z5AdbpMPhqT91Fq7MbzcqVgPUZrlPtLcgm-7ybkDcAlFwGy_Zpg3b6vz266UFFlLEbSNLM_H19O0ty0N56bQpzSX1IAZstVcH6mPktREwt7k99RPZobeYEEzR0dfd4dnTlGrkYEKO-RqLeuAqXnB_MJFw5G88rb2NtMfSA"

    private static final String URL_API_SPOTIFY="https://api.spotify.com/v1/me";
    private static final String ACCESS_TOKEN="BQAgziFhDi3UjKMoyXslaY2ZHk9rxsz3Z5AdbpMPhqT91Fq7MbzcqVgPUZrlPtLcgm-7ybkDcAlFwGy_Zpg3b6vz266UFFlLEbSNLM_H19O0ty0N56bQpzSX1IAZstVcH6mPktREwt7k99RPZobeYEEzR0dfd4dnTlGrkYEKO-RqLeuAqXnB_MJFw5G88rb2NtMfSA";

    private RestTemplate restTemplate;

    
    public SpotifyAPI(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public UserSpotify me(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ACCESS_TOKEN);

        HttpEntity<String> entity = 
                new HttpEntity<String>(headers);
        ResponseEntity<UserSpotify> response = restTemplate.
                                    exchange(URL_API_SPOTIFY,
                                    HttpMethod.GET,
                                    entity,
                                    new ParameterizedTypeReference<UserSpotify>(){});
        return response.getBody();
    }

}