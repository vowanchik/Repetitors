package repetitors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repetitors.model.Student;
import repetitors.repository.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    private StudentRepository repository;

    @Autowired
    public void SetStudentRepository(StudentRepository repository) {this.repository = repository;}
    public Student getStudentById(Long id){ return repository.getOne(id);}

    @Override
    public void saveStudent(Student student){repository.save(student);}

    @Override
    public void updateStudent(Student student){
        Student updated = repository.getOne(student.getId());
        if(student.getName()!= null)
            updated.setName(student.getName());
        if(student.getLastName()!=null)
            updated.setLastName(student.getLastName());
        repository.save(updated);
    }

    @Override
    public void deleteStudent(Long id){repository.deleteById(id);}


    @Override
    public List<Student> findAllByOrderByIdAsc() { return repository.findAllByOrderByIdAsc(); }
}
