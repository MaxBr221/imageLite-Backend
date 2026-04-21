package github.com.MaxBr221.imageliteapi.application.users;

import github.com.MaxBr221.imageliteapi.InfraRepository.UserRepository;
import github.com.MaxBr221.imageliteapi.application.jwt.JwtService;
import github.com.MaxBr221.imageliteapi.domain.AccessToken;
import github.com.MaxBr221.imageliteapi.domain.entity.User;
import github.com.MaxBr221.imageliteapi.domain.exception.DuplicatedTupleException;
import github.com.MaxBr221.imageliteapi.domain.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public User getByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional
    public User save(User user) {
        var possibleUser = getByEmail(user.getEmail());
        if(possibleUser != null){
            throw new DuplicatedTupleException("User already exists!");
        }
        encodePassword(user);
        return repository.save(user);
    }

    @Override
    public AccessToken autheticate(String email, String password) {
        var user = getByEmail(email);
        if(user == null){
            return null;
        }
        boolean matches = passwordEncoder.matches(password, user.getSenha());
        if (matches){
            return jwtService.generateToken(user);
        }

        return null;
    }
    private void encodePassword(User user){
        String rawPassword = user.getSenha();
        String encodePassword = passwordEncoder.encode(rawPassword);
        user.setSenha(encodePassword);
    }
}
