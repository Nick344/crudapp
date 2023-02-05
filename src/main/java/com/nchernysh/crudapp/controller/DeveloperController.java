package com.nchernysh.crudapp.controller;

import com.nchernysh.crudapp.dto.DeveloperDto;
import com.nchernysh.crudapp.model.Developer;
import com.nchernysh.crudapp.repository.DeveloperRepository;
import com.nchernysh.crudapp.repository.SkillRepository;
import com.nchernysh.crudapp.repository.SpecialtyRepository;
import com.nchernysh.crudapp.repository.gson.GsonDeveloperRepositoryImpl;
import com.nchernysh.crudapp.repository.gson.GsonSkillRepositoryImpl;
import com.nchernysh.crudapp.repository.gson.GsonSpecialtyRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class DeveloperController {

  private final DeveloperRepository developerRepository = new GsonDeveloperRepositoryImpl();
  private final SkillRepository skillRepository = new GsonSkillRepositoryImpl();
  private final SpecialtyRepository specialtyRepository = new GsonSpecialtyRepositoryImpl();

  public DeveloperDto createDeveloper(Developer developer) {
    return convert(developerRepository.save(developer));
  }

  public List<DeveloperDto> getAllDevelopers() {
    return developerRepository.getAll().stream().map(this::convert).collect(Collectors.toList());
  }


  public DeveloperDto getById(Integer id) {
    return convert(developerRepository.getById(id));
  }

  public void deleteDeveloper(Integer id) {
    developerRepository.deleteById(id);
  }

  public DeveloperDto updateDeveloper(Developer developer) {
    return convert(developerRepository.update(developer));
  }

  private DeveloperDto convert(Developer developer) {
    DeveloperDto developerDto = new DeveloperDto();
    developerDto.setId(developer.getId());
    developerDto.setName(developer.getName());
    for (Integer skillId : developer.getSkillIds()) {
      developerDto.getSkills().add(skillRepository.getById(skillId));
    }
    Integer specialtyId = developer.getSpecialtyId();
    if (specialtyId != null) {
      developerDto.setSpecialty(specialtyRepository.getById(specialtyId));
    }
    return developerDto;
  }
}
