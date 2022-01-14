public class Application {
    private Console console = new Console();
    private Student student = new Student();
    private Group group = new Group();
    private University university = new University();
    private String inputUserItemMenu = "";

    public void start() {
        student.addStudent();
        group.addGroup();
        university.addUniversity();
        showTitle();
        showMainMenu();
    }
    private void showTitle() {
        System.out.println("Вы вошли в базу по университетам!");
        System.out.println("В базу уже успешно внесены внесены студенты, группы, университеты.");
        System.out.println("Пожалуйста, выберите пункт меню...");
    }

    private void showMainMenu() {
        while (true) {
            System.out.println("1 - Подсчет среднего бала студента/группы/университета");
            System.out.println("2 - Сортировка студентов в группе по среднему балу");
            System.out.println("3 - Вывод всей информации");
            System.out.println("4 - Выход\n");
            inputUserItemMenu = console.readString();
            if (inputUserItemMenu.equals("1")) {
                showTitleAverageRatingUserGroupUniversity();
                showMenuAverageRatingUserGroupUniversity();
            } else if (inputUserItemMenu.equals("2")) {
                showTitleSortedStudentInGroupByRatings();
                showMenuSortedStudentInGroupByRatings();
            } else if (inputUserItemMenu.equals("3")) {
                showTitlePrintAllUserGroupUniversity();
                showMenuPrintAllUserGroupUniversity();
            } else if (inputUserItemMenu.equals("4")) {
                console.readerClose();
                System.exit(0);
            } else {
                System.out.println("\nДанного пункта меню нет");
            }
        }
    }

    private void showTitleAverageRatingUserGroupUniversity() {
        System.out.println("\nПодсчет среднего балла студента/группы/университета");
    }

    private void showTitleSortedStudentInGroupByRatings() {
        System.out.println("\nCортировка студентов в группе по среднему баллу\n");
    }

    private void showTitlePrintAllUserGroupUniversity() {
        System.out.println("\nВывод всей информации");
    }

    private void showMenuAverageRatingUserGroupUniversity() {
        while (true) {
            System.out.println("\n1 - подсчет среднего балла студентов");
            System.out.println("2 - подсчет среднего балла групп");
            System.out.println("3 - подсчет среднего балла университетов");
            System.out.println("4 - выход в главное меню\n");

            inputUserItemMenu = console.readString();

            if (inputUserItemMenu.equals("1")) {
                student.sortStudentsMediumMark();
            } else if (inputUserItemMenu.equals("2")) {
                group.calculateMediumMarkGroup();
            } else if (inputUserItemMenu.equals("3")) {
                university.calculateMediumMarkUniversity();
            } else if (inputUserItemMenu.equals("4")) {
                showMainMenu();
            } else {
                System.out.println("\nДанного пункта меню нет");
            }
        }
    }

    private void showMenuSortedStudentInGroupByRatings() {
        while (true) {
            group.sortedStudentInGroupByRatings();

            System.out.println("\n1 - выход в главное меню\n");
            inputUserItemMenu = console.readString();

            if (inputUserItemMenu.equals("1")) {
                showMainMenu();
            } else {
                System.out.println("\nДанного пункта меню нет");
            }
        }
    }

    private void showMenuPrintAllUserGroupUniversity() {
        while (true) {
            System.out.println("\n1 - вывод информации о студентах");
            System.out.println("2 - вывод информации о группах");
            System.out.println("3 - вывод информации об университетах");
            System.out.println("4 - выход в главное меню\n");

            inputUserItemMenu = console.readString();

            if (inputUserItemMenu.equals("1")) {
                student.printAllStudentsInfo();
            } else if (inputUserItemMenu.equals("2")) {
                group.printAllGroupInfo();
            } else if (inputUserItemMenu.equals("3")) {
                university.printAllUniversityInfo();
            } else if (inputUserItemMenu.equals("4")) {
                showMainMenu();
            } else {
                System.out.println("\nДанного пункта меню нет");
            }
        }
    }
}



