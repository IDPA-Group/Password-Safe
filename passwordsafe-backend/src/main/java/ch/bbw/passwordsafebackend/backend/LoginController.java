package ch.bbw.passwordsafebackend.backend;


import ch.bbw.passwordsafebackend.DB.Register.Login;
import ch.bbw.passwordsafebackend.DB.Register.LoginRepository;
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
