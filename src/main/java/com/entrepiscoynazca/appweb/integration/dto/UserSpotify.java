package com.entrepiscoynazca.appweb.integration.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSpotify{

    @JsonProperty("display_name")
    private String displayName;
    private String country;
    private String email;
    private Followers followers;

}