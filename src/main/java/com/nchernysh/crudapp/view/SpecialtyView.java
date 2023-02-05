package com.nchernysh.crudapp.view;

import com.nchernysh.crudapp.controller.SpecialtyController;
import com.nchernysh.crudapp.model.Specialty;

import java.util.List;
import java.util.Scanner;

public class SpecialtyView {
  private final SpecialtyController specialtyController = new SpecialtyController();
  private final Scanner scanner = new Scanner(System.in);

  public void menu() {
    boolean inMenu = true;
    while (inMenu) {
      System.out.println("SPECIALTY MENU");
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
          getAllSpecialties();
          break;
        case 3:
          createSpecialty();
          break;
        case 4:
          updateSpecialty();
          break;
        case 5:
          deleteSpecialty();
          break;
        case 0:
          inMenu = false;
          break;
        case -1:
          System.exit(0);
      }
    }
  }

  public void createSpecialty() {
    System.out.println("Enter name");
    String name = scanner.next();
    Specialty specialty = new Specialty();
    specialty.setName(name);
    Specialty createdSpecialty = specialtyController.createSpecialty(specialty);
    System.out.println(createdSpecialty);
  }

  public void getAllSpecialties() {
    List<Specialty> specialties = specialtyController.getAllSpecialties();
    System.out.println("SPECIALTY LIST");
    for (Specialty specialty : specialties) {
      System.out.println(specialty);
    }
  }

  public void getByID() {
    System.out.println("Enter ID");
    Integer id = scanner.nextInt();
    System.out.println(specialtyController.getById(id));
    }

  public void deleteSpecialty() {
    System.out.println("Enter ID");
    Integer id = scanner.nextInt();
    specialtyController.deleteSpecialty(id);
  }

  public void updateSpecialty() {
    System.out.println("Enter ID");
    Integer id = scanner.nextInt();
    System.out.println("Enter name");
    String name = scanner.next();
    Specialty specialty = new Specialty();
    specialty.setId(id);
    specialty.setName(name);
    Specialty updatedSpecialty = specialtyController.updateSpecialty(specialty);
    System.out.println(updatedSpecialty);
  }

}
