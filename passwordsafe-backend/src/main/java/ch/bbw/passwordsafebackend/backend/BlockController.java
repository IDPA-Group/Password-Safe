package ch.bbw.passwordsafebackend.backend;

import ch.bbw.passwordsafebackend.DB.Block.Block;
import ch.bbw.passwordsafebackend.DB.Block.BlockRepository;
import ch.bbw.passwordsafebackend.DB.Register.Login;
import ch.bbw.passwordsafebackend.DB.Register.LoginRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;

@RestController
public class BlockController {

    private BlockRepository repository;
    private LoginRepository loginRepository;

    public BlockController(BlockRepository repository, LoginRepository loginRepository) {
        this.repository = repository;
        this.loginRepository = loginRepository;
    }

    // Gets all Blocks by mastername/owner
    @GetMapping(value = "/Blocks/{mastername}")
    List<Block> getAllBlocks(@PathVariable String mastername) {
        List<Block> blocks = repository.findByOwner(mastername);

        for (Block block : blocks) {
            try {
                String hashedMasterPassword = loginRepository.findByMastername(mastername).getMasterpassword();
                byte[] salt = new byte[16];
                int iterations = 10000;
                int keyLength = 256;

                KeySpec keySpec = new PBEKeySpec(hashedMasterPassword.toCharArray(), salt, iterations, keyLength);
                SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyFactory.generateSecret(keySpec).getEncoded(), "AES");

                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(new byte[16]));

                byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(block.getPassword()));

                String decryptedPassword = new String(decryptedBytes, "UTF-8");
                block.setPassword(decryptedPassword);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return blocks;
    }

    //Create a Block and references with owner
    @PostMapping(value = "/createBlock", consumes = "application/json", produces = "application/json")
    ResponseEntity<?> createBlock(@RequestBody Block block) {
        try {
            String mastername = block.getOwner();
            Login login = loginRepository.findByMastername(mastername);

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

                repository.save(block);
                return ResponseEntity.ok(block);

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mastername not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }

    // Update a Block by id
    @PutMapping("/updateBlock/{id}")
    ResponseEntity<?> updateBlock(@PathVariable String id, @RequestBody Block updatedBlock) {
        try {
            // Check if the block exists
            if (repository.existsById(id)) {
                // Retrieve the existing block
                Block existingBlock = repository.findById(id).orElse(null);

                if (existingBlock != null) {
                    // Update only the provided fields
                    if (updatedBlock.getTitle() != null) {
                        existingBlock.setTitle(updatedBlock.getTitle());
                    }
                    if (updatedBlock.getUsername() != null) {
                        existingBlock.setUsername(updatedBlock.getUsername());
                    }
                    if (updatedBlock.getPassword() != null) {
                        existingBlock.setPassword(updatedBlock.getPassword());
                    }
                    if (updatedBlock.getOwner() != null) {
                        existingBlock.setOwner(updatedBlock.getOwner());
                    }

                    // Save the updated block
                    repository.save(existingBlock);

                    return ResponseEntity.ok("Block updated successfully");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating the block");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Block not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }

    // Delete a Block by id
    @DeleteMapping("/deleteBlock/{id}")
    ResponseEntity<?> deleteBlock(@PathVariable String id) {
        try {
            // Check if the block exists
            if (repository.existsById(id)) {
                // Delete the block by id
                repository.deleteById(id);
                return ResponseEntity.ok("Block deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Block not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }
}
