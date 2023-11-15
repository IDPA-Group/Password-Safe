package ch.bbw.passwordsafe.DB.block;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Block")
public class Block {


    @Id
    public String id;

    public String title;
    public String username;
    public String password;

    public Block(String title, String username, String password) {
        this.title = title;
        this.username = username;
        this.password = password;
    }



    @Override
    public String toString() {
        return "Block{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
