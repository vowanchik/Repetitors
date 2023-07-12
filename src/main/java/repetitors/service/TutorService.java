package repetitors.service;

import repetitors.model.Tutor;

import java.util.List;

public interface TutorService {
    Tutor getTutorById(Long id);
    void saveTutor(Tutor tutor);
    void updateTutor(Tutor tutor);
    void deleteTutor(Long id);
    List<Tutor> findAllByOrderByIdAsc();
}
