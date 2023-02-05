package com.nchernysh.crudapp.dto;

import com.nchernysh.crudapp.model.Skill;
import com.nchernysh.crudapp.model.Specialty;

import java.util.ArrayList;
import java.util.List;

public class DeveloperDto {
  private Integer id;
  private String name;
  private Specialty specialty;
  private List<Skill> skills = new ArrayList<>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Specialty getSpecialty() {
    return specialty;
  }

  public void setSpecialty(Specialty specialty) {
    this.specialty = specialty;
  }

  public List<Skill> getSkills() {
    return skills;
  }

  public void setSkills(List<Skill> skills) {
    this.skills = skills;
  }

  @Override
  public String toString() {
    return "DeveloperDto{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", specialty=" + specialty +
      ", skills=" + skills +
      '}';
  }
}
