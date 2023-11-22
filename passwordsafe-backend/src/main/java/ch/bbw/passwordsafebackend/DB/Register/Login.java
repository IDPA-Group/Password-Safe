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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMastername() {
        return mastername;
    }

    public void setMastername(String mastername) {
        this.mastername = mastername;
    }

    public String getMasterpassword() {
        return masterpassword;
    }

    public void setMasterpassword(String masterpassword) {
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
