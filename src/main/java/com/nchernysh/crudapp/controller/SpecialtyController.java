package com.nchernysh.crudapp.controller;

import com.nchernysh.crudapp.model.Specialty;
import com.nchernysh.crudapp.repository.SpecialtyRepository;
import com.nchernysh.crudapp.repository.gson.GsonSpecialtyRepositoryImpl;

import java.util.List;

public class SpecialtyController {

  private final SpecialtyRepository specialtyRepository = new GsonSpecialtyRepositoryImpl();

  public Specialty createSpecialty(Specialty specialty) {
    return specialtyRepository.save(specialty);
  }

  public List<Specialty> getAllSpecialties() {
    return specialtyRepository.getAll();
  }


  public Specialty getById(Integer id) {
    return specialtyRepository.getById(id);
  }

  public void deleteSpecialty(Integer id ) {
     specialtyRepository.deleteById(id);
  }

  public Specialty updateSpecialty(Specialty specialty) {
    return specialtyRepository.update(specialty);
  }
}
