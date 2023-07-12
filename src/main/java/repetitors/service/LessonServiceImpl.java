package repetitors.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repetitors.model.Lesson;
import repetitors.repository.LessonRepository;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    private LessonRepository repository;

    @Autowired
    public void setTutorRepository(LessonRepository repository) {
        this.repository = repository;
    }

    public Lesson getLessonById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public void saveLesson(Lesson lesson) {
        repository.save(lesson);
    }

    @Override
    public void updateLesson(Lesson lesson) {
        Lesson updated = repository.getOne(lesson.getId());
        if(lesson.getName() != null)
            updated.setName(lesson.getName());
        if(lesson.getName() != null)
            updated.setName(lesson.getName());
        if(lesson.getDay() != null)
            updated.setDay(lesson.getDay());
        if(lesson.getTime()!=null)
            updated.setTime(lesson.getTime());
        if(lesson.getPrice() != null)
            updated.setPrice(lesson.getPrice());
        repository.save(updated);
    }

    @Override
    public void deleteLesson(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Lesson> findAllByOrderByIdAsc() {
        return repository.findAllByOrderByIdAsc();
    }
}
