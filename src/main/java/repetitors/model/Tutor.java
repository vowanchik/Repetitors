package repetitors.model;

import org.hibernate.annotations.Cascade;
import repetitors.util.ExcludeT2;
import repetitors.util.ExcludeT3;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tutors")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name")
    private String lastName;
    private String name;
    private String login;
    private String password;

    @ExcludeT2
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutor")
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    private List<Lesson> lessons;

    @ExcludeT2
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tutor")
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    private List<Student> students;

    public Tutor() {
    }

    public Tutor(String lastName, String name, String login, String password) {
        this.lastName = lastName;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", lessons=" + lessons +
                '}';
    }
}
