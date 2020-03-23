package ru.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.site.model.Passage;
import ru.site.service.PassageService;

import java.util.List;

@RestController
public class PassageController {

    @Autowired
    PassageService passageService;

    @GetMapping("/passages")
    private List<Passage> getAllPassages() {
        return passageService.getAllPassages();
    }

    @GetMapping("/passage/{id}")
    private Passage getPassage(@PathVariable("id") Long id) {
        return passageService.getPassageById(id);
    }

    @DeleteMapping("/passage/{id}")
    private void deletePassage(@PathVariable("id") Long id) {
        passageService.delete(id);
    }

    @PostMapping("/passage")
    private Long savePassages(@RequestBody Passage passage) {
        passageService.saveOrUpdate(passage);
        return passage.getId();
    }

    @GetMapping("/employeePassages/{id}")
    private List<Passage> getPassagesByEmployee(@PathVariable("id") Long id) {
        return passageService.getPassagesByEmployee(id);
    }

    //@GetMapping("/PassagePerson/{id}")
    //private Person getPassageById(@PathVariable("id") Long id) {
    //    return passageService.getPersonById(id);
    //}


}
