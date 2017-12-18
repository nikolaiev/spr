package beans.models;

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
    private Set<UserRole> roles;
    private String    password;
    private double    balance;

    /*Set default roles*/
    {
        roles =new HashSet<UserRole>(){{add(RESGISTERED_USER);}};
        balance = 0.0;
    }

    public User(String email, String name, LocalDate birthday) {
        this.email=email;
        this.name=name;
        this.birthday=birthday;
    }


    public User(String email, String name, LocalDate birthday,UserRole roles) {
        this.email=email;
        this.name=name;
        this.birthday=birthday;
        this.roles.add(roles);
    }

    public User(String email, String name, LocalDate birthday, UserRole roles, String password) {
        this(email,name,birthday, roles);
        this.password=password;
    }

    public User(long id, String email, String name, LocalDate birthday, UserRole roles, String password) {
        this(email,name,birthday, roles);
        this.password=password;
        this.id=id;
    }

    public User(long id, String email, String name, LocalDate birthday, Set<UserRole> roles, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.roles.addAll(roles);
        this.password = password;
    }


    public User withId(long id) {
        return new User(id, email, name, birthday, roles,password);
    }

}
