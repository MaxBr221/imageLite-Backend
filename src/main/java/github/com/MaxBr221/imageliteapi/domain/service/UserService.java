package github.com.MaxBr221.imageliteapi.domain.service;

import github.com.MaxBr221.imageliteapi.domain.AccessToken;
import github.com.MaxBr221.imageliteapi.domain.entity.User;

public interface UserService {
    User getByEmail(String email);
    User save(User user);
    AccessToken autheticate(String email, String password);
}
