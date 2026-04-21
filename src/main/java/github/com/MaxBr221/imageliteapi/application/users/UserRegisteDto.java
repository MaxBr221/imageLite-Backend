package github.com.MaxBr221.imageliteapi.application.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegisteDto {
    private String email;
    private String senha;
}
