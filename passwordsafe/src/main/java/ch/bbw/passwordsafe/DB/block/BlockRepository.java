package ch.bbw.passwordsafe.DB.block;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BlockRepository extends MongoRepository<Block, String> {

    public Block findByUsername(String username);
    public List<Block> findByTitle(String title);

}
