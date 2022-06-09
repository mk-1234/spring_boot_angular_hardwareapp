package hr.tvz.krznar.hardwareapp.security.service;

import hr.tvz.krznar.hardwareapp.security.domain.User;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}
