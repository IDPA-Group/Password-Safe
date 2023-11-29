package ch.bbw.passwordsafebackend.backend;

import ch.bbw.passwordsafebackend.DB.Register.Login;
import ch.bbw.passwordsafebackend.DB.Register.LoginRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private final LoginRepository repository;

    public LoginController(LoginRepository repository) {
        this.repository = repository;
    }

    // GET find by mastername
    @GetMapping("/login/{mastername}")
    Login one(@PathVariable String mastername){
        return repository.findByMastername(mastername);
    }

    // POST create LoginData with JSON
    @PostMapping(value = "/createLogin", consumes = "application/json", produces = "application/json")
    Login createLogin(@RequestBody Login login){
        System.out.println(login);
        String hashedPassword = BCrypt.hashpw(login.getMasterpassword(), BCrypt.gensalt(5));
        login.setMasterpassword(hashedPassword);
        return repository.save(login);
    }

    // POST login with JSON
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    ResponseEntity<?> loginUser(@RequestBody Login requestLogin) {
        Login storedLogin = repository.findByMastername(requestLogin.getMastername());

        if (storedLogin != null) {
            if (BCrypt.checkpw(requestLogin.getMasterpassword(), storedLogin.getMasterpassword())) {
                String token = requestLogin.getMastername();
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mastername not found or incorrect");
        }
    }
}
