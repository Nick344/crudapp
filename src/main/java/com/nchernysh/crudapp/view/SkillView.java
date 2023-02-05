package com.nchernysh.crudapp.view;

import com.nchernysh.crudapp.controller.SkillController;
import com.nchernysh.crudapp.model.Skill;

import java.util.List;
import java.util.Scanner;

public class SkillView {
  private final SkillController skillController = new SkillController();
  private final Scanner scanner = new Scanner(System.in);

  public void menu() {
    boolean inMenu = true;
    while (inMenu) {
      System.out.println("SKILL MENU");
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
          getAllSkills();
          break;
        case 3:
          createSkill();
          break;
        case 4:
          updateSkill();
          break;
        case 5:
          deleteSkill();
          break;
        case 0:
          inMenu = false;
          break;
        case -1:
          System.exit(0);
      }
    }
  }

  public void createSkill() {
    System.out.println("Enter name");
    String name = scanner.next();
    Skill skill = new Skill();
    skill.setName(name);
    Skill createdSkill = skillController.createSkill(skill);
    System.out.println(createdSkill);
  }

  public void getAllSkills() {
    List<Skill> skills = skillController.getAllSkills();
    System.out.println("SKILL LIST");
    for (Skill skill : skills) {
      System.out.println(skill);
    }
  }

  public void getByID() {
    System.out.println("Enter ID");
    Integer id = scanner.nextInt();
    System.out.println(skillController.getById(id));
    }

  public void deleteSkill() {
    System.out.println("Enter ID");
    Integer id = scanner.nextInt();
    skillController.deleteSkill(id);
  }

  public void updateSkill() {
    System.out.println("Enter ID");
    Integer id = scanner.nextInt();
    System.out.println("Enter name");
    String name = scanner.next();
    Skill skill = new Skill();
    skill.setId(id);
    skill.setName(name);
    Skill updatedSkill = skillController.updateSkill(skill);
    System.out.println(updatedSkill);
  }

}
