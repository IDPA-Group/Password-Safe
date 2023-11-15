package ch.bbw.passwordsafe;

import ch.bbw.passwordsafe.DB.block.Block;
import ch.bbw.passwordsafe.DB.block.BlockRepository;
import ch.bbw.passwordsafe.DB.register.Login;
import ch.bbw.passwordsafe.DB.register.LoginRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class PasswordsafeApplication implements CommandLineRunner {



	@Autowired
	private BlockRepository blockRepository;

	@Autowired
	private LoginRepository loginRepository;

	public static void main(String[] args) {
		SpringApplication.run(PasswordsafeApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception{
		blockRepository.deleteAll();
		loginRepository.deleteAll();

		// Save Blocks provisionally
		blockRepository.save(new Block("Chrome", "justindav505", "123123123"));
		blockRepository.save(new Block("Youtube","justindavid", "password123456"));
		blockRepository.save(new Block("Youtube","davidhofi", "passw"));


		loginRepository.save(new Login("Test", "123123"));
		loginRepository.save(new Login("Tes3t", "1231234"));

		System.out.println(loginRepository.findByMastername("Test"));

		//fetch all Blocks
		System.out.println("Customers found with findAll()");
		System.out.println("-------------------------------");
		for (Block block : blockRepository.findAll()){
			System.out.println(block);
		}
		System.out.println();


		// fetch an individual customer
		System.out.println("Block found with findByFirstName('justindav505'):");
		System.out.println("--------------------------------");
		System.out.println(blockRepository.findByUsername("justindav505"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Block block : blockRepository.findByTitle("Youtube")) {
			System.out.println(block);
		}
	}

}
