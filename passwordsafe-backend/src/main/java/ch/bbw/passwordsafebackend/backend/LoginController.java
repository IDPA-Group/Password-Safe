package ch.bbw.passwordsafebackend.backend;


import ch.bbw.passwordsafebackend.DB.Register.Login;
import ch.bbw.passwordsafebackend.DB.Register.LoginRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LoginController {

    private LoginRepository repository;

    public LoginController(LoginRepository repository) {
        this.repository = repository;
    }


    //GET find by mastername
    @GetMapping("/login/{mastername}")
    Login one(@PathVariable String mastername){
        return repository.findByMastername(mastername);
    }

    // POST create LoginData with JSON
    @PostMapping(value = "/createLoginJson", consumes = "application/json", produces = "application/json")
    Login createLogin(@RequestBody Login login){
        return repository.save(login);
    }

}
