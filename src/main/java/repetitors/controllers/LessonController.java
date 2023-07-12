package repetitors.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repetitors.model.ExclusionStrategy;
import repetitors.model.Lesson;
import repetitors.model.Tutor;
import repetitors.repository.HibernateProxyTypeAdapter;
import repetitors.service.LessonService;
import repetitors.service.TutorService;
import repetitors.util.Exclude;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    private TutorService tutorService;
    private LessonService lessonService;

    @Autowired
    public void setTutorService(TutorService service) {
        this.tutorService = service;
    }

    @Autowired
    public void setLessonService(LessonService service) {
        this.lessonService = service;
    }

    //http://localhost:8081/lesson
    @GetMapping(produces = "application/json")
    public String list() {
        List<Lesson> lessons = lessonService.findAllByOrderByIdAsc();
        return new GsonBuilder().addSerializationExclusionStrategy(
                new ExclusionStrategy(Exclude.EXCLUDET2)).create().toJson(lessons);
    }

    //http://localhost:8081/lesson/1
    @GetMapping(path = "/{id}", produces = "application/json")
    public String get(@PathVariable Long id) {
        try {
            Gson gson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                    .addSerializationExclusionStrategy(new ExclusionStrategy(Exclude.EXCLUDET2)).create();
            return gson.toJson(lessonService.getLessonById(id));
        } catch (Exception e) {
            return "null";
        }
    }

    //http://localhost:8081/lesson/add/1
    //{"name": "Кибернетика","date": "15.10.1995","price": 5000}
    @PostMapping(path = "/add/{tutorId}", consumes = "application/json", produces = "application/json")
    public String add(@RequestBody Lesson lesson, @PathVariable Long tutorId) {
        Tutor tutorById = tutorService.getTutorById(tutorId);
        lesson.setTutor(tutorById);
        lessonService.saveLesson(lesson);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                .addSerializationExclusionStrategy(new ExclusionStrategy(Exclude.EXCLUDET2)).create();
        return gson.toJson(lesson);
    }

    //http://localhost:8081/lesson/update
    //{"id" : 6, "name": "Кибернетика","date": "15.10.1995","price": 5000}
    @PostMapping(path = "/update", consumes = "application/json", produces = "application/json")
    public String update(@RequestBody Lesson lesson) {
        try {
            if(lesson.getId() == null)
                return "null";
            lessonService.updateLesson(lesson);
            return new GsonBuilder().addSerializationExclusionStrategy(
                    new ExclusionStrategy(Exclude.EXCLUDET2)).create().toJson(lesson);
        } catch (Exception e) {
            return "null";
        }
    }

    //http://localhost:8081/lesson/delete/1
    @DeleteMapping(path = "/delete/{id}", produces = "application/json")
    public String delete(@PathVariable Long id){
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                .addSerializationExclusionStrategy(new ExclusionStrategy(Exclude.EXCLUDET2)).create();
        String res = gson.toJson(lessonService.getLessonById(id));
        lessonService.deleteLesson(id);
        return res;
    }
}
