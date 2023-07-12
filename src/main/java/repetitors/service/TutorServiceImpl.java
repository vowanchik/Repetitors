package repetitors.service;


import repetitors.model.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repetitors.repository.TutorRepository;

import java.util.List;

@Service
public class TutorServiceImpl implements TutorService {
    private TutorRepository repository;

    @Autowired
    public void setTutorRepository(TutorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tutor getTutorById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public void saveTutor(Tutor tutor) {
        repository.save(tutor);
    }

    @Override
    public void updateTutor(Tutor tutor) {
        Tutor updated = repository.getOne(tutor.getId());
        if(tutor.getLastName() != null)
            updated.setLastName(tutor.getLastName());
        if(tutor.getName() != null)
            updated.setName(tutor.getName());
        if(tutor.getLogin() != null)
            updated.setLogin(tutor.getLogin());
        if(tutor.getPassword() != null)
            updated.setPassword(tutor.getPassword());
        repository.save(updated);
    }

    @Override
    public void deleteTutor(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Tutor> findAllByOrderByIdAsc() {
        return repository.findAllByOrderByIdAsc();
    }
}
