package beans.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class User {

    private long      id;
    private String    email;
    private String    name;
    private LocalDate birthday;

    public User(long id, String email, String name, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
    }

    public User(String email, String name, LocalDate birthday) {
        this(-1, email, name, birthday);
    }

    public User withId(long id) {
        return new User(id, email, name, birthday);
    }
}
