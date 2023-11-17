package ch.bbw.passwordsafebackend.backend;

import ch.bbw.passwordsafebackend.DB.Block.Block;
import ch.bbw.passwordsafebackend.DB.Block.BlockRepository;
import ch.bbw.passwordsafebackend.DB.Register.Login;
import ch.bbw.passwordsafebackend.DB.Register.LoginRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

@RestController
public class BlockController {

    private BlockRepository repository;
    private LoginRepository loginRepository;

    public BlockController(BlockRepository repository, LoginRepository loginRepository) {
        this.repository = repository;
        this.loginRepository = loginRepository;
    }

    @PostMapping(value = "/createBlock", consumes = "application/json", produces = "application/json")
    Block createBlock(@RequestBody Block block) {
        try {
            // Retrieve the Login object based on the mastername
            String mastername = block.owner; // Replace with the actual mastername
            Login login = loginRepository.findByMastername(mastername);

            // Check if the mastername exists
            if (login != null) {

                String hashedMasterPassword = login.getMasterpassword();
                byte[] salt = new byte[16];
                int iterations = 10000;
                int keyLength = 256;

                KeySpec keySpec = new PBEKeySpec(hashedMasterPassword.toCharArray(), salt, iterations, keyLength);
                SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyFactory.generateSecret(keySpec).getEncoded(), "AES");

                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(new byte[16]));

                byte[] encryptedBytes = cipher.doFinal(block.getPassword().getBytes("UTF-8"));

                String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
                block.setPassword(encryptedText);

            } else {
                // Handle the case where the mastername is not found
                System.err.println("Mastername not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return repository.save(block);
    }
}

