package com.nchernysh.crudapp.model;

import java.io.Serializable;
import java.util.Objects;

public class Skill implements Serializable {
  private Integer id;
  private String name;

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

  @Override
  public String toString() {
    return "id=" + id + ", name='" + name + '\'';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Skill skill = (Skill) o;
    return Objects.equals(id, skill.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
