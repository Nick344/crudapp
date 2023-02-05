package com.nchernysh.crudapp.controller;

import com.nchernysh.crudapp.model.Skill;
import com.nchernysh.crudapp.repository.SkillRepository;
import com.nchernysh.crudapp.repository.gson.GsonSkillRepositoryImpl;

import java.util.List;

public class SkillController {

  private final SkillRepository skillRepository = new GsonSkillRepositoryImpl();

  public Skill createSkill(Skill skill) {
    return skillRepository.save(skill);
  }

  public List<Skill> getAllSkills() {
    return skillRepository.getAll();
  }


  public Skill getById(Integer id) {
    return skillRepository.getById(id);
  }

  public void deleteSkill(Integer id ) {
     skillRepository.deleteById(id);
  }

  public Skill updateSkill(Skill skill) {
    return skillRepository.update(skill);
  }
}
