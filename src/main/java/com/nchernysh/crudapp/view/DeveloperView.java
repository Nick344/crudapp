package com.nchernysh.crudapp.view;

import com.nchernysh.crudapp.controller.DeveloperController;
import com.nchernysh.crudapp.dto.DeveloperDto;
import com.nchernysh.crudapp.model.Developer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {
  private final DeveloperController developerController = new DeveloperController();
  private final Scanner scanner = new Scanner(System.in);

  public void menu() {
    boolean inMenu = true;
    while (inMenu) {
      System.out.println("DEVELOPER MENU");
      System.out.println("1 - getById");
      System.out.println("2 - getAll");
      System.out.println("3 - create");
      System.out.println("4 - update");
      System.out.println("5 - deleteById");
      System.out.println("0 - back to main menu");
      System.out.println("-1 - exit");

      int choice = scanner.nextInt();

      switch (choice) {
        case 1:
          getByID();
          break;
        case 2:
          getAllDevelopers();
          break;
        case 3:
          createDeveloper();
          break;
        case 4:
          updateDeveloper();
          break;
        case 5:
          deleteDeveloper();
          break;
        case 0:
          inMenu = false;
          break;
        case -1:
          System.exit(0);
      }
    }
  }

  public void createDeveloper() {
    Developer developer = new Developer();

    System.out.println("Enter name");
    String name = scanner.next();
    developer.setName(name);

    System.out.println("Enter specialty ID");
    Integer specialtyId = scanner.nextInt();
    developer.setSpecialtyId(specialtyId);

    List<Integer> skillIds = new ArrayList<>();
    boolean enteredSkills = false;
    while (!enteredSkills) {
      System.out.println("Enter skill ID. To finish enter 0");
      int skillId = scanner.nextInt();
      if (skillId == 0) {
        enteredSkills = true;
      } else {
        skillIds.add(skillId);
      }
    }
    developer.setSkillIds(skillIds);
    DeveloperDto createdDeveloper = developerController.createDeveloper(developer);
    System.out.println(createdDeveloper);
  }

  public void getAllDevelopers() {
    List<DeveloperDto> developers = developerController.getAllDevelopers();
    System.out.println("DEVELOPER LIST");
    for (DeveloperDto developer : developers) {
      System.out.println(developer);
    }
  }

  public void getByID() {
    System.out.println("Enter ID");
    Integer id = scanner.nextInt();
    System.out.println(developerController.getById(id));
  }

  public void deleteDeveloper() {
    System.out.println("Enter ID");
    Integer id = scanner.nextInt();
    developerController.deleteDeveloper(id);
  }

  public void updateDeveloper() {
    Developer developer = new Developer();

    System.out.println("Enter ID");
    Integer id = scanner.nextInt();
    developer.setId(id);

    System.out.println("Enter name");
    String name = scanner.next();
    developer.setName(name);

    System.out.println("Enter specialty ID");
    Integer specialtyId = scanner.nextInt();
    developer.setSpecialtyId(specialtyId);

    List<Integer> skillIds = new ArrayList<>();
    boolean enteredSkills = false;
    while (!enteredSkills) {
      System.out.println("Enter skill ID. To finish enter 0");
      int skillId = scanner.nextInt();
      if (skillId == 0) {
        enteredSkills = true;
      } else {
        skillIds.add(skillId);
      }
    }
    developer.setSkillIds(skillIds);

    DeveloperDto updatedDeveloper = developerController.updateDeveloper(developer);
    System.out.println(updatedDeveloper);
  }
}
