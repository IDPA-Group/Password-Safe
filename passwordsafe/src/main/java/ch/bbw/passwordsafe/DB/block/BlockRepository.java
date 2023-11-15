package ch.bbw.passwordsafe.DB.block;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends MongoRepository<Block, String> {

    public Block findByUsername(String username);
    public List<Block> findByTitle(String title);

}
