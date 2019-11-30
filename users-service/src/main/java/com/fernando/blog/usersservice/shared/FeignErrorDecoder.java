package com.fernando.blog.usersservice.shared;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private final Environment environment;

    public FeignErrorDecoder(Environment environment) {
        this.environment = environment;
    }

    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 400:
                break;
            case 404: {
                if(s.contains("getAlbums")) {
                    return new ResponseStatusException(
                    HttpStatus.valueOf(response.status()),
                            environment.getProperty("msg.404.default")
                    );
                }
                break;
            }
            default:
                return new Exception(response.reason());
        }
        return null;

    }
}
