package repetitors.model;

import repetitors.util.ExcludeT1;

import javax.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String day;
    private String time;
    private Double price;

    @ManyToOne
    @JoinColumn(name="id_tutor")
    @ExcludeT1
    private Tutor tutor;

    public Lesson() {
    }

    public Lesson(String name, String day,String time, Double price) {
        this.name = name;
        this.day = day;
        this.time = time;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() { return day; }

    public void setDay(String day) { this.day = day; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", day='" + day + '\'' +
                ", time=' " + time + '\'' +
                ", price=" + price +
                '}';
    }
}
