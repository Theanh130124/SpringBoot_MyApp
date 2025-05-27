package com.theanh1301.myapp.controller;


import com.fasterxml.jackson.core.io.JsonEOFException;
import com.nimbusds.jose.JOSEException;
import com.theanh1301.myapp.dto.request.AuthenticationRequest;
import com.theanh1301.myapp.dto.request.IntrospectRequest;
import com.theanh1301.myapp.dto.request.NormalizeApiResponse;
import com.theanh1301.myapp.dto.response.AuthenticationResponse;
import com.theanh1301.myapp.dto.response.IntrospectResponse;
import com.theanh1301.myapp.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.text.ParseException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    private final RestClient.Builder builder;

    @PostMapping("/login")
    public NormalizeApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        //Xem đăng nhập thành công có message
        //<AuthenticationResponse>builder() -> tương đướng với setResult
        //AuthenticationResponse.builder().authenticated(result).build() -> tương đướng với 
        return NormalizeApiResponse.<AuthenticationResponse>builder().result(result).build();

    }
    @PostMapping("/introspect")
    public NormalizeApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
    throws ParseException , JOSEException {

        var res = authenticationService.introspect(request);
        return NormalizeApiResponse.<IntrospectResponse>builder().result(res).build();

    }


}
