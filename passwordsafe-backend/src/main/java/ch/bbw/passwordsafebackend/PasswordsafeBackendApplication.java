package ch.bbw.passwordsafebackend;

import ch.bbw.passwordsafebackend.DB.Block.BlockRepository;
import ch.bbw.passwordsafebackend.DB.Register.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class PasswordsafeBackendApplication implements CommandLineRunner {



	@Autowired
	private BlockRepository blockRepository;

	@Autowired
	private LoginRepository loginRepository;

	public static void main(String[] args) {
		SpringApplication.run(PasswordsafeBackendApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception{
		blockRepository.deleteAll();
		loginRepository.deleteAll();

	}

}
