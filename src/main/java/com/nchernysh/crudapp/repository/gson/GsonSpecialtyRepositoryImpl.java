package com.nchernysh.crudapp.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nchernysh.crudapp.model.Specialty;
import com.nchernysh.crudapp.repository.SpecialtyRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonSpecialtyRepositoryImpl implements SpecialtyRepository {

    private final Gson GSON = new Gson();
    private final String SPECIALTY_FILE = "src/main/resources/specialties.json";

    private List<Specialty> getSpecialtiesFromFile() {
        try {
            Path filePath = Path.of(SPECIALTY_FILE);
            byte[] bytes = Files.readAllBytes(Paths.get(String.valueOf(filePath)));
            String json = new String(bytes);
            return GSON.fromJson(json, new TypeToken<List<Specialty>>() {
            }.getType());
        } catch (IOException e) {
            return Collections.emptyList();
        }

    }

    private void writeSpecialtyToFile(List<Specialty> specialties) {
        String json = GSON.toJson(specialties);

        try (FileOutputStream fos = new FileOutputStream(SPECIALTY_FILE)) {
            byte[] bytes = json.getBytes();
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer generateNewId(List<Specialty> specialties) {
        Specialty maxIdSpecialty = specialties.stream().max(Comparator.comparing(Specialty::getId)).orElse(null);
        return Objects.nonNull(maxIdSpecialty) ? maxIdSpecialty.getId() + 1 : 1;
    }


    @Override
    public Specialty getById(Integer id) {
        return getSpecialtiesFromFile().stream()
                .filter(s -> s.getId().equals(id)).findFirst()
                .orElse(null);
    }

    @Override
    public List<Specialty> getAll() {
        return getSpecialtiesFromFile();
    }

    @Override
    public Specialty save(Specialty specialty) {
        List<Specialty> currentSpecialties = getSpecialtiesFromFile();
        Integer id = generateNewId(currentSpecialties);
        specialty.setId(id);
        currentSpecialties.add(specialty);
        writeSpecialtyToFile(currentSpecialties);
        return specialty;
    }

    @Override
    public Specialty update(Specialty specialtyToUpdate) {
        List<Specialty> updatedSpecialties = getSpecialtiesFromFile();
        updatedSpecialties.forEach(existingSpecialty -> {
            if(existingSpecialty.getId().equals(specialtyToUpdate.getId())) {
                existingSpecialty.setName(specialtyToUpdate.getName());
            }
        });
        writeSpecialtyToFile(updatedSpecialties);
        return specialtyToUpdate;
    }

    @Override
    public void deleteById(Integer id) {
        List<Specialty> currentSpecialties = getSpecialtiesFromFile();
        currentSpecialties.removeIf(s -> s.getId().equals(id));
        writeSpecialtyToFile(currentSpecialties);
    }
}
