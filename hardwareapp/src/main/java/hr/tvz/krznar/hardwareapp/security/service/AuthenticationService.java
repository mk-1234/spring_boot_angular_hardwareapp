package hr.tvz.krznar.hardwareapp.security.service;

import hr.tvz.krznar.hardwareapp.security.command.LoginCommand;
import hr.tvz.krznar.hardwareapp.security.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}
