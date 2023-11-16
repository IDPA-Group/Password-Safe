package ch.bbw.passwordsafebackend.DB.Register;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Login")
public class Login {

    @Id
    public String id;

    public String mastername;
    public String masterpassword;

    public Login(String mastername, String masterpassword) {
        this.mastername = mastername;
        this.masterpassword = masterpassword;
    }


    @Override
    public String toString() {
        return "Login{" +
                "id='" + id + '\'' +
                ", mastername='" + mastername + '\'' +
                ", masterpassword='" + masterpassword + '\'' +
                '}';
    }
}
