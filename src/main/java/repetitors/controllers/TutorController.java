package repetitors.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import repetitors.model.ExclusionStrategy;
import repetitors.model.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repetitors.repository.HibernateProxyTypeAdapter;
import repetitors.service.TutorService;
import repetitors.util.Exclude;

import java.util.List;

@RestController
@RequestMapping("/tutor")
public class TutorController {
    private TutorService service;
    @Autowired
    public void setTutorService(TutorService service) {
        this.service = service;
    }

    //http://localhost:8080/tutor
    @GetMapping(produces = "application/json")
    public String list() {
        List<Tutor> tutors = service.findAllByOrderByIdAsc();
        return new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy(Exclude.EXCLUDET1)).create().toJson(tutors);
    }

    //http://localhost:8080/tutor/1
    @GetMapping(path = "/{id}", produces = "application/json")
    public String get(@PathVariable Long id) {
        try {
            Gson gson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                    .addSerializationExclusionStrategy(new ExclusionStrategy(Exclude.EXCLUDET1)).create();
            return gson.toJson(service.getTutorById(id));
        } catch (Exception e) {
            return "null";
        }
    }

    //http://localhost:8080/tutor/add
    //{"lastName" : "petrov", "name" : "petr", "login" : "tt", "password" : "123rrr"}
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public String add(@RequestBody Tutor tutor) {
        service.saveTutor(tutor);
        return new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy(Exclude.EXCLUDET1)).create().toJson(tutor);
    }

    //http://localhost:8080/tutor/update
    //{"id" : 4, "lastName" : "petrov", "name" : "petr", "login" : "tt", "password" : "123rrr"}
    @PostMapping(path = "/update", consumes = "application/json", produces = "application/json")
    public String update(@RequestBody Tutor tutor) {
        try {
            if(tutor.getId() == null)
                return "null";
            service.updateTutor(tutor);
            return new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy(Exclude.EXCLUDET1)).create().toJson(tutor);
        } catch (Exception e) {
            return "null";
        }
    }

    //http://localhost:8080/tutor/delete/1
    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public String delete(@PathVariable Long id){
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                .addSerializationExclusionStrategy(new ExclusionStrategy(Exclude.EXCLUDET1)).create();
        String res = gson.toJson(service.getTutorById(id));
        service.deleteTutor(id);
        return res;
    }
}
