package hr.tvz.krznar.hardwareapp.security.service;

import hr.tvz.krznar.hardwareapp.security.command.LoginCommand;
import hr.tvz.krznar.hardwareapp.security.domain.User;
import hr.tvz.krznar.hardwareapp.security.dto.LoginDTO;
import hr.tvz.krznar.hardwareapp.security.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<LoginDTO> login(LoginCommand command) {
        Optional<User> user = userRepository.findByUsername(command.getUsername());

        if (user.isEmpty()) {
            System.out.println("no user with that name");
        }

        if (user.isEmpty() || !isMatchingPassword(command.getPassword(), user.get().getPassword())) {
            return Optional.empty();
        }

        return Optional.of(
                new LoginDTO(jwtService.createJwt(user.get()))
        );
    }

    private boolean isMatchingPassword(String rawPassword, String encryptedPassword) {
        // TODO - implementirati provjeru odgovara li lozinka, koju je unio korisnik, enkriptiranoj lozinki u bazi
        //throw new UnsupportedOperationException();
        System.out.println("raw password: " + rawPassword);
        System.out.println("enc password: " + encryptedPassword);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("encoded raw: " + encoder.encode(rawPassword));
        boolean match = encoder.matches(rawPassword, encryptedPassword);
        if (match) {
            System.out.println("passwords are the same!!!");
        } else {
            System.out.println("passwords are NOT the same");
        }
        return match;
    }
}
