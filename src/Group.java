import java.util.*;

public class Group {
    private int idGroup;          //ID группы
    private String groupName;
    private ArrayList<Integer> idListStudentsInGroup = new ArrayList<>(); //ID студентов в группе
    public static ArrayList<Integer> idListOfGroups = new ArrayList<>(); //ID всех групп
    private Storage storage = new Storage();
    private Student student = new Student();

    public Group() {
    }

    public Group(int idGroup, String groupName, ArrayList<Integer> idListStudentsInGroup) {
        this.idGroup = idGroup;
        this.groupName = groupName;
        this.idListStudentsInGroup = idListStudentsInGroup;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<Integer> getIdListStudentsInGroup() {
        return idListStudentsInGroup;
    }

    public void setIdListStudentsInGroup(ArrayList<Integer> idListStudentsInGroup) {
        this.idListStudentsInGroup = idListStudentsInGroup;
    }

    public static ArrayList<Integer> getIdListOfGroups() {
        return idListOfGroups;
    }

    public static void setIdListOfGroups(ArrayList<Integer> idListOfGroups) {
        Group.idListOfGroups = idListOfGroups;
    }

    @Override
    public String toString() {
        return "Group{" +
                "idGroup=" + idGroup +
                ", groupName='" + groupName + '\'' +
                ", idListStudentsInGroup=" + idListStudentsInGroup +
                '}';
    }

    public void addGroup() {
        System.out.println("Добавление всех групп: ");
        Group group1 = new Group(0, "group1", new ArrayList<>(List.of(2, 1)));
        storage.add(group1);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Group group2 = new Group(1, "group2", new ArrayList<>(List.of(0, 3)));
        storage.add(group2);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Group group3 = new Group(2, "group3", new ArrayList<>(List.of(4, 5)));
        storage.add(group3);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Group group4 = new Group(3, "group4", new ArrayList<>(List.of(6, 7)));
        storage.add(group4);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Group group5 = new Group(4, "group5", new ArrayList<>(List.of(8, 9)));
        storage.add(group5);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Прошло успешно!");

    }

    public void printAllGroupInfo() {
        storage.printAllGroups();
    }

    public void calculateMediumMarkGroup() {
        System.out.println("Средний балл группы:");
        double mediumMarkGroup = 0;
        if (idListOfGroups.size() == 0 || Student.setIdStudents.size() == 0) {
            System.out.println("Недостаточно данных для вычисления средней оценки");
        } else {
            for (Integer idGroup : idListOfGroups) {
                Group group = storage.getGroup(idGroup);
                ArrayList<Integer> listAllMarksStudents = new ArrayList<>();
                ArrayList<Integer> listIdAllStudentInGroup = group.getIdListStudentsInGroup();
                for (int i = 0; i < listIdAllStudentInGroup.size(); i++) {
                    listAllMarksStudents.addAll(storage.getStudentFromId(listIdAllStudentInGroup.get(i)).getMarksList());
                }
                OptionalDouble average = listAllMarksStudents.stream().mapToDouble(e -> e).average();
                if (average.isPresent()) {
                    mediumMarkGroup = average.getAsDouble();
                }
                System.out.println("ID : " + group.getIdGroup() + ", название : " + group.getGroupName() +
                        ", средний балл : " + mediumMarkGroup);
            }
        }
    }

    public void sortedStudentInGroupByRatings() {
        if (Student.setIdStudents.size() == 0 || idListOfGroups.size() == 0) {
            System.out.println("Недостаточно данных для сортировки в группах");
        } else {
            for (int i = 0; i < idListOfGroups.size(); i++) {
                Group group = storage.getGroup(i);
                ArrayList<Integer> sortStudentIdByAsc = new ArrayList<>();

                sortStudentIdByAsc = student.sortStudentAsc(group.getIdListStudentsInGroup());

                System.out.println("ID : " + group.getIdGroup() + ", название : " + group.getGroupName()
                        + ", до сортировки : " + group.getIdListStudentsInGroup()
                        + ", после сортировки : " + sortStudentIdByAsc);
            }
        }
    }
}
