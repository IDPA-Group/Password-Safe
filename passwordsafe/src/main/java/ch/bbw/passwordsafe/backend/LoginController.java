package ch.bbw.passwordsafe.backend;


import ch.bbw.passwordsafe.DB.register.Login;
import ch.bbw.passwordsafe.DB.register.LoginRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class LoginController {

    private LoginRepository repository;

    public LoginController(LoginRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/login/{mastername}")
    Login one(@PathVariable String mastername){
        return repository.findByMastername(mastername);
    }

}
