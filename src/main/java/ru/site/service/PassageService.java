package ru.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.site.model.Passage;
import ru.site.repository.PassageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassageService {

    @Autowired
    private PassageRepository passageRepository;

    public List<Passage> getAllPassages() {
        List<Passage> passages = new ArrayList<Passage>();
        passageRepository.findAll().forEach(passage -> passages.add(passage));
        return passages;
    }

    public List<Passage> getPassagesByEmployee(Long employeeId) {
        return passageRepository.findByEmployeeId(employeeId);
    }

    public Passage getPassageById(Long id) {
        return passageRepository.findById(id).get();
    }

    public void saveOrUpdate(Passage passage) {
        passageRepository.save(passage);
    }

    public void delete(Long id) {
        passageRepository.deleteById(id);
    }

}
