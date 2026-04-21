package github.com.MaxBr221.imageliteapi.application.users;


import github.com.MaxBr221.imageliteapi.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapperToUser(UserDto dto){
        return User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .senha(dto.getSenha())
                .build();
    }
}
