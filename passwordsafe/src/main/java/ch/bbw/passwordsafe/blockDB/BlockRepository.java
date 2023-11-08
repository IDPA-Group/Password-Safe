package ch.bbw.passwordsafe.blockDB;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BlockRepository extends MongoRepository<Block, String> {

    public Block findByUsername(String username);

}
