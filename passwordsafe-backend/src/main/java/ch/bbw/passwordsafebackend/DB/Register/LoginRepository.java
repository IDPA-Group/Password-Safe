package ch.bbw.passwordsafebackend.DB.Register;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends MongoRepository<Login, String> {
    public Login findByMastername(String mastername);
}
