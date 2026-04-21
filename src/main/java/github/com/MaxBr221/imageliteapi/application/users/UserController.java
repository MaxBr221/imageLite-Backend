package github.com.MaxBr221.imageliteapi.application.users;


import github.com.MaxBr221.imageliteapi.domain.entity.User;
import github.com.MaxBr221.imageliteapi.domain.exception.DuplicatedTupleException;
import github.com.MaxBr221.imageliteapi.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/v1/users")
@RestController
@Slf4j
public class UserController {
    
    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody UserDto dto){
        try{
            log.info("Salvando User" );
            User user = mapper.mapperToUser(dto);
            service.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (DuplicatedTupleException exception){
            Map<String, String> jsonResult = Map.of("error", exception.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(jsonResult);
        }
    }
    @PostMapping("/auth")
    public ResponseEntity autheticate(@RequestBody UserRegisteDto dto){
        log.info("Autenticando User");
        var token = service.autheticate(dto.getEmail(), dto.getSenha());
        if (token == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(token);
    }
}
