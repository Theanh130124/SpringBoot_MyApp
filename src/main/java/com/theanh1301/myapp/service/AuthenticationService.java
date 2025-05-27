package com.theanh1301.myapp.service;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.theanh1301.myapp.dto.request.AuthenticationRequest;
import com.theanh1301.myapp.dto.response.AuthenticationResponse;
import com.theanh1301.myapp.exception.AppException;
import com.theanh1301.myapp.exception.ErrorCode;
import com.theanh1301.myapp.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationService {

    UserRepository userRepository;

    //Nữa đặt chổ khác
    @NonFinal // de lombok khong them final vao constructor
    protected static final String SINGER_KEY = "KAr9UiUW5DtjBqUK+kfp4YpmCFdQGsp7U/OXR0N90/7HvaJOlFNou2sIpmq9Cg/d";

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        //tìm user   ->
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(),user.getPassword());// so sánh password
        if(!authenticated){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        var token = generateToken(request.getUsername());



        return AuthenticationResponse.builder().token(token).authenticated(true).build();
    }
    private String generateToken(String username)
    {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        //builder payload
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)//user đăng nhập
                .issuer("devtheanh.com") //token issuer từ ai
                .issueTime(new Date()) // tg đăng đăng ký
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli())) // thời hạn token sau 1 giờ
                .claim("customClaim","Custom")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header,payload);
        //Ký token -> MACSigner() khóa ký và giải mã trùng nhau

        try{jwsObject.sign(new MACSigner(SINGER_KEY.getBytes()));
            return jwsObject.serialize();
        }
        catch (JOSEException e)
        {
            log.error("Không thể tạo token lỗi:"+ e);
            throw new RuntimeException(e);
        }
    }
}
