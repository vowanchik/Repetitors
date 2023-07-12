package repetitors.model;


import repetitors.util.ExcludeT3;

import javax.persistence.*;

@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name")
    private String lastName;
    private String name;

    @ManyToOne
    @JoinColumn(name="id_tutor")
    @ExcludeT3
    private Tutor tutor;

    public Student(){

    }

    public Student(String lastName, String name) {
        this.lastName = lastName;
        this.name = name;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    @Override
    public String toString() {
        return "Student{" +
                ", lastName='" + lastName + '\'' +
                ", name='" + name +
                '}';
    }
}
