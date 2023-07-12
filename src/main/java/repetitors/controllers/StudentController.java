package repetitors.controllers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repetitors.model.ExclusionStrategy;
import repetitors.model.Student;
import repetitors.model.Tutor;
import repetitors.repository.HibernateProxyTypeAdapter;
import repetitors.service.StudentService;
import repetitors.service.TutorService;
import repetitors.util.Exclude;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private TutorService tutorService;
    private StudentService studentService;


    @Autowired
    public void setTutorService(TutorService service){this.tutorService=service;}
    @Autowired
    public void setStudentService(StudentService service){this.studentService=service;}

    //http://localhost:8081/student
    @GetMapping(produces = "application/json")
    public String list(){
        List<Student> students = studentService.findAllByOrderByIdAsc();
        return new GsonBuilder().addSerializationExclusionStrategy(
                new ExclusionStrategy(Exclude.EXCLUDET2)).create().toJson(students);
    }

    //http://localhost:8081/student/1
    @GetMapping(path = "/{id}", produces = "application/json")
    public String get(@PathVariable Long id) {
        try {
            Gson gson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                    .addSerializationExclusionStrategy(new ExclusionStrategy(Exclude.EXCLUDET2)).create();
            return gson.toJson(studentService.getStudentById(id));
        } catch (Exception e) {
            return "null";
        }
    }


    //http://localhost:8081/student/add/1
    //{"lastName": "Павлов","name": "Евгений"}
    @PostMapping(path = "/add/{tutorId}", consumes = "application/json", produces = "application/json")
    public String add(@RequestBody Student student, @PathVariable Long tutorId) {
        Tutor tutorById = tutorService.getTutorById(tutorId);
        student.setTutor(tutorById);
        studentService.saveStudent(student);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                .addSerializationExclusionStrategy(new ExclusionStrategy(Exclude.EXCLUDET2)).create();
        return gson.toJson(student);
    }


    //http://localhost:8081/student/update
    //{"id" : 6, "lastName": "Ильич","name": "Игорь"}
    @PostMapping(path = "/update", consumes = "application/json", produces = "application/json")
    public String update(@RequestBody Student student) {
        try {
            if(student.getId() == null)
                return "null";
           studentService.updateStudent(student);
            return new GsonBuilder().addSerializationExclusionStrategy(
                    new ExclusionStrategy(Exclude.EXCLUDET2)).create().toJson(student);
        } catch (Exception e) {
            return "null";
        }
    }

    //http://localhost:8081/lesson/delete/1
    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public String delete(@PathVariable Long id){
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                .addSerializationExclusionStrategy(new ExclusionStrategy(Exclude.EXCLUDET2)).create();
        String res = gson.toJson(studentService.getStudentById(id));
        studentService.deleteStudent(id);
        return res;
    }
}
