package repetitors.service;


import repetitors.model.Student;

import java.util.List;

public interface StudentService {
    Student getStudentById(Long id);
    void saveStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Long id);
    List<Student> findAllByOrderByIdAsc();
}
