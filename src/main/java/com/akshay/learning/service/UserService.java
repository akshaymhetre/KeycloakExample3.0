package com.akshay.learning.service;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private KeycloakRestTemplate keycloakRestTemplate;

    public List<UserRepresentation> getUsers() {
        try {
            String keycloakUserListEndpoint = "http://localhost:8282/auth/admin/realms/SpringBoot/users";
            ResponseEntity<UserRepresentation[]> response = keycloakRestTemplate.getForEntity(keycloakUserListEndpoint, UserRepresentation[].class);
            return Arrays.asList(response.getBody());
        } catch (RestClientException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

