package com.fernando.blog.usersservice.data;

import com.fernando.blog.usersservice.model.AlbumResponseModel;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "albums-ws",
        path = "/users",
        fallbackFactory = AlbumsFallbackFactory.class)
public interface AlbumHttpClient {

    @GetMapping(path = "/{userId}/albums")
    List<AlbumResponseModel> getAlbums(@PathVariable String userId);

}

@Component
class AlbumsFallbackFactory implements FallbackFactory<AlbumHttpClient>  {

    @Override
    public AlbumHttpClient create(Throwable throwable) {
        return new AlbumsFalback(throwable);
    }
}

class AlbumsFalback implements AlbumHttpClient {

    private Throwable cause;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public AlbumsFalback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public List<AlbumResponseModel> getAlbums(String userId) {

        if(cause instanceof FeignException
                && ((FeignException) cause).status() == 404) {

            logger.error("404 error took place" +
                    " when getAlbuns was  called " +
                    "with usserId " + userId + " Error message :"
            + cause.getLocalizedMessage());
        } else {
            logger.error("Other error took place: " + cause.getLocalizedMessage());
        }

        return new ArrayList<>();
    }
}
