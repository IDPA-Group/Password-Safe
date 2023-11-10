package ch.bbw.passwordsafe;

import ch.bbw.passwordsafe.DB.block.Block;
import ch.bbw.passwordsafe.DB.block.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class PasswordsafeApplication implements CommandLineRunner {



	@Autowired
	private BlockRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(PasswordsafeApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception{
		repository.deleteAll();

		// Save Blocks provisionally
		repository.save(new Block("Chrome", "justindav505", "123123123"));
		repository.save(new Block("Youtube","justindavid", "password123456"));
		repository.save(new Block("Youtube","davidhofi", "passw"));


		//fetch all Blocks
		System.out.println("Customers found with findAll()");
		System.out.println("-------------------------------");
		for (Block block : repository.findAll()){
			System.out.println(block);
		}
		System.out.println();


		// fetch an individual customer
		System.out.println("Block found with findByFirstName('justindav505'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByUsername("justindav505"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Block block : repository.findByTitle("Youtube")) {
			System.out.println(block);
		}
	}

}
