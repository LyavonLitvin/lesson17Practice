import java.util.*;

public class University {
    private int idUniversity;
    private String nameUniversity;
    private ArrayList<Integer> listIdGroupsInUniversity = new ArrayList<>();

    private Storage storage = new Storage();
    public static ArrayList<Integer> listIdUniversity = new ArrayList<>();
    public University() {
    }

    public University(int idUniversity, String nameUniversity, ArrayList<Integer> listIdGroupsInUniversity) {
        this.idUniversity = idUniversity;
        this.nameUniversity = nameUniversity;
        this.listIdGroupsInUniversity = listIdGroupsInUniversity;
    }

    public int getIdUniversity() {
        return idUniversity;
    }

    public void setIdUniversity(int idUniversity) {
        this.idUniversity = idUniversity;
    }

    public String getNameUniversity() {
        return nameUniversity;
    }

    public void setNameUniversity(String nameUniversity) {
        this.nameUniversity = nameUniversity;
    }

    public ArrayList<Integer> getListIdGroupsInUniversity() {
        return listIdGroupsInUniversity;
    }

    public void setListIdGroupsInUniversity(ArrayList<Integer> listIdGroupsInUniversity) {
        this.listIdGroupsInUniversity = listIdGroupsInUniversity;
    }

    @Override
    public String toString() {
        return "University{" +
                "idUniversity=" + idUniversity +
                ", nameUniversity='" + nameUniversity + '\'' +
                ", listIdGroupsInUniversity=" + listIdGroupsInUniversity +
                '}';
    }

    public void addUniversity() {
        System.out.println("Добавление университетов:");
        University university1 = new University(0, "university1", new ArrayList<>(List.of(0,2)));
        University university2 = new University(1, "university2", new ArrayList<>(List.of(1, 4)));
        University university3 = new University(2, "university3", new ArrayList<>(List.of(2, 3)));
        storage.add(university1);
        storage.add(university2);
        storage.add(university3);
        System.out.println("Прошло успешно!");
    }

    public void printAllUniversityInfo() {
        storage.printAllUniversity();
    }

    public void calculateMediumMarkUniversity() {
        System.out.println("\nСредний рейтинг университета\n");
        double mediumMarkUniversity = 0;

        if (listIdUniversity.size() == 0 || Group.idListOfGroups.size() == 0 || Student.setIdStudents.size() == 0) {
            System.out.println("Недостаточно данных для вычисления среднего балла\n");
        } else {
            for (Integer idUniversity : listIdUniversity) {
                University university = storage.getUniversity(idUniversity);
                ArrayList<Integer> listAllMarksStudents = new ArrayList<>();
                ArrayList<Integer> listIdAllStudentInUniversity = new ArrayList<>();
                ArrayList<Integer> listIdAllGroupInUniversity = university.getListIdGroupsInUniversity();

                for (int i = 0; i < listIdAllGroupInUniversity.size(); i++) {
                    listIdAllStudentInUniversity.addAll(storage.getGroup(listIdAllGroupInUniversity.get(i)).getIdListStudentsInGroup());
                }

                for (int i = 0; i < listIdAllStudentInUniversity.size(); i++) {
                    listAllMarksStudents.addAll(storage.getStudentFromId(listIdAllStudentInUniversity.get(i)).getMarksList());
                }

                OptionalDouble average = listAllMarksStudents.stream().mapToDouble(e -> e).average();
                if (average.isPresent()) {
                    mediumMarkUniversity = average.getAsDouble();
                }

                System.out.println("ID - " + university.getIdUniversity() + ", название - " + university.getNameUniversity() +
                        ", средний балл - " + mediumMarkUniversity);
            }
        }
    }
}
