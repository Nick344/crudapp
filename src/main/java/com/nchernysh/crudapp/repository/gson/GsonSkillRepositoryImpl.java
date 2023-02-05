package com.nchernysh.crudapp.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nchernysh.crudapp.model.Skill;
import com.nchernysh.crudapp.repository.SkillRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class GsonSkillRepositoryImpl implements SkillRepository {

    private final Gson GSON = new Gson();
    private final String SKILL_FILE = "src/main/resources/skills.json";

    private List<Skill> getSkillsFromFile() {
        try {
            Path filePath = Path.of(SKILL_FILE);
            byte[] bytes = Files.readAllBytes(Paths.get(String.valueOf(filePath)));
            String json = new String(bytes);
            return GSON.fromJson(json, new TypeToken<List<Skill>>() {
            }.getType());
        } catch (IOException e) {
            return Collections.emptyList();
        }

    }

    private void writeSkillToFile(List<Skill> skills) {
        String json = GSON.toJson(skills);

        try (FileOutputStream fos = new FileOutputStream(SKILL_FILE)) {
            byte[] bytes = json.getBytes();
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer generateNewId(List<Skill> skills) {
        Skill maxIdSkill = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        return Objects.nonNull(maxIdSkill) ? maxIdSkill.getId() + 1 : 1;
    }


    @Override
    public Skill getById(Integer id) {
        return getSkillsFromFile().stream()
                .filter(s -> s.getId().equals(id)).findFirst()
                .orElse(null);
    }

    @Override
    public List<Skill> getAll() {
        return getSkillsFromFile();
    }

    @Override
    public Skill save(Skill skill) {
        List<Skill> currentSkills = getSkillsFromFile();
        Integer id = generateNewId(currentSkills);
        skill.setId(id);
        currentSkills.add(skill);
        writeSkillToFile(currentSkills);
        return skill;
    }

    @Override
    public Skill update(Skill skillToUpdate) {
        List<Skill> updatedSkills = getSkillsFromFile();
        updatedSkills.forEach(existingSkill -> {
            if(existingSkill.getId().equals(skillToUpdate.getId())) {
                existingSkill.setName(skillToUpdate.getName());
            }
        });
        writeSkillToFile(updatedSkills);
        return skillToUpdate;
    }

    @Override
    public void deleteById(Integer id) {
        List<Skill> currentSkills = getSkillsFromFile();
        currentSkills.removeIf(s -> s.getId().equals(id));
        writeSkillToFile(currentSkills);
    }
}
