package beans.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static beans.models.UserRole.RESGISTERED_USER;

@Data
@NoArgsConstructor
public class User {

    private long      id;
    private String    email;
    private String    name;
    private LocalDate birthday;
    private Set<UserRole> role;
    private String    password;
    {
        role=new HashSet<>();
    }

    public User(String email, String name, LocalDate birthday) {
        this.email=email;
        this.name=name;
        this.birthday=birthday;
        role=new HashSet<>();
        this.role.add(RESGISTERED_USER);
    }


    public User(String email, String name, LocalDate birthday,UserRole role) {
        this.email=email;
        this.name=name;
        this.birthday=birthday;
        this.role.add(role);
    }

    public User(String email, String name, LocalDate birthday,UserRole role,String password) {
        this(email,name,birthday,role);
        this.password=password;
    }
    public User(long id, String email, String name, LocalDate birthday,UserRole role,String password) {
        this(email,name,birthday,role);
        this.password=password;
        this.id=id;
    }

    public User(long id, String email, String name, LocalDate birthday, Set<UserRole> role, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = birthday;

        /*setting default role*/
        if(role.isEmpty()){
            role.add(RESGISTERED_USER);
        }

        this.role = role;
        this.password = password;
    }


    public User withId(long id) {
        return new User(id, email, name, birthday,role,password);
    }

}
