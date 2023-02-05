package com.nchernysh.crudapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Developer implements Serializable {
  private Integer id;
  private String name;
  private Integer specialtyId;
  private List<Integer> skillIds = new ArrayList<>();

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

  public Integer getSpecialtyId() {
    return specialtyId;
  }

  public void setSpecialtyId(Integer specialtyId) {
    this.specialtyId = specialtyId;
  }

  public List<Integer> getSkillIds() {
    return skillIds;
  }

  public void setSkillIds(List<Integer> skillIds) {
    this.skillIds = skillIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Developer developer = (Developer) o;
    return Objects.equals(id, developer.id) && Objects.equals(specialtyId, developer.specialtyId) && Objects.equals(skillIds, developer.skillIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, specialtyId, skillIds);
  }
}
