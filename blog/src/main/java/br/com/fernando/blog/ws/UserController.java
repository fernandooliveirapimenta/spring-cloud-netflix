package br.com.fernando.blog.ws;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUsers(@RequestParam(defaultValue = "3") Integer page,
                           @RequestParam(defaultValue = "5") Integer limit,
                           @RequestParam(defaultValue = "7") String sort) {


        return "get users was called " + limit + " , " + sort;
    }

    @GetMapping(path = "{userId}",
            produces = {
                MediaType.APPLICATION_XML_VALUE,
                MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity getUser(@PathVariable String userId) {

        String f = null;

        int t = f.length();

        UserRest u = UserRest
                .builder()
                .firstName("Fernando")
                .lastName("Pimenta")
                .email("fefe@pimetan")
                .userId(null)
                .build();

        final UserRest newUser = u.toBuilder().build();

        return ResponseEntity.ok(u);
    }

    @PostMapping
    public UserRest createUsers(@Valid @RequestBody UserDetailsRequest request) {

        System.out.println(request);

        return UserRest
                .builder()
                .userId(UUID.randomUUID().toString())
                .build();
    }

    @PutMapping(path = "/{userId}")
    public String updateUsers(@PathVariable String userId,
                              @Valid @RequestBody UpdateUserDetailsRequestModel update) {

        System.out.println(update);

        return "updateUsers";
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity deleteUsers(@PathVariable String userId) {

        return ResponseEntity.ok().build();
    }
}
