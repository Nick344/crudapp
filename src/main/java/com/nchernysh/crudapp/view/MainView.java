package com.nchernysh.crudapp.view;

import java.util.Scanner;

public class MainView {
    private final Scanner scanner = new Scanner(System.in);

    private SkillView skillView = new SkillView();

    public void mainMenu() {
        while (true) {
            System.out.println("MAIN MENU");
            System.out.println("1 - developers");
            System.out.println("2 - specialties");
            System.out.println("3 - skills");

            int choice = scanner.nextInt();

            switch (choice) {
                case 3:
                    skillView.menu();
                    break;
                case -1:
                    System.exit(0);
            }
        }
    }
}
