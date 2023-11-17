package ch.bbw.passwordsafebackend.backend;

import ch.bbw.passwordsafebackend.DB.Block.Block;
import ch.bbw.passwordsafebackend.DB.Block.BlockRepository;
import ch.bbw.passwordsafebackend.DB.Register.LoginRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@RestController
public class BlockController {

    private BlockRepository repository;



    public BlockController(BlockRepository repository) {
        this.repository = repository;
    }

    // Create Block
    @PostMapping(value = "/createBlock", consumes = "application/json", produces = "application/json")
    Block createBlock(@RequestBody Block block){
        try{

            String password = "SymmetricPw47111";
            byte[] ivBytes = new byte[16];
            IvParameterSpec iv =new IvParameterSpec(ivBytes);

            SecretKey secretKey = new SecretKeySpec(password.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

            byte[] encryptedBytes = cipher.doFinal(block.password.getBytes("UTF-8"));

            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            block.setPassword(encryptedText);

        }catch (Exception e){
            e.printStackTrace();
        }
        return repository.save(block);
    }

}
