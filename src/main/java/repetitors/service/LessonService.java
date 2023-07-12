package repetitors.service;

import repetitors.model.Lesson;

import java.util.List;

public interface LessonService {
    Lesson getLessonById(Long id);
    void saveLesson(Lesson lesson);
    void updateLesson(Lesson lesson);
    void deleteLesson(Long id);
    List<Lesson> findAllByOrderByIdAsc();
}
