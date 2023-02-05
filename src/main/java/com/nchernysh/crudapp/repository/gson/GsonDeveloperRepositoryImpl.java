package com.nchernysh.crudapp.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nchernysh.crudapp.model.Developer;
import com.nchernysh.crudapp.repository.DeveloperRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {

  private final Gson GSON = new Gson();
  private final String DEVELOPER_FILE = "src/main/resources/developers.json";

  private List<Developer> getDevelopersFromFile() {
    try {
      Path filePath = Path.of(DEVELOPER_FILE);
      byte[] bytes = Files.readAllBytes(Paths.get(String.valueOf(filePath)));
      String json = new String(bytes);
      return GSON.fromJson(json, new TypeToken<List<Developer>>() {
      }.getType());
    } catch (IOException e) {
      return Collections.emptyList();
    }

  }

  private void writeDeveloperToFile(List<Developer> developers) {
    String json = GSON.toJson(developers);

    try (FileOutputStream fos = new FileOutputStream(DEVELOPER_FILE)) {
      byte[] bytes = json.getBytes();
      fos.write(bytes);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Integer generateNewId(List<Developer> developers) {
    Developer maxIdDeveloper = developers.stream().max(Comparator.comparing(Developer::getId)).orElse(null);
    return Objects.nonNull(maxIdDeveloper) ? maxIdDeveloper.getId() + 1 : 1;
  }


  @Override
  public Developer getById(Integer id) {
    return getDevelopersFromFile().stream()
      .filter(s -> s.getId().equals(id)).findFirst()
      .orElse(null);
  }

  @Override
  public List<Developer> getAll() {
    return getDevelopersFromFile();
  }

  @Override
  public Developer save(Developer developer) {
    List<Developer> currentDevelopers = getDevelopersFromFile();
    Integer id = generateNewId(currentDevelopers);
    developer.setId(id);
    currentDevelopers.add(developer);
    writeDeveloperToFile(currentDevelopers);
    return developer;
  }

  @Override
  public Developer update(Developer developerToUpdate) {
    List<Developer> updatedDevelopers = getDevelopersFromFile();
    updatedDevelopers.forEach(existingDeveloper -> {
      if (existingDeveloper.getId().equals(developerToUpdate.getId())) {
        existingDeveloper.setName(developerToUpdate.getName());
        existingDeveloper.setSpecialtyId(developerToUpdate.getSpecialtyId());
        existingDeveloper.setSkillIds(developerToUpdate.getSkillIds());
      }
    });
    writeDeveloperToFile(updatedDevelopers);
    return developerToUpdate;
  }

  @Override
  public void deleteById(Integer id) {
    List<Developer> currentDevelopers = getDevelopersFromFile();
    currentDevelopers.removeIf(s -> s.getId().equals(id));
    writeDeveloperToFile(currentDevelopers);
  }
}
