import java.util.*;
import java.util.stream.Collectors;

public class Student {
    private int idStudent;
    private String firstName;
    private String lastName;
    private List<Integer> marksList;
    public static Set<Integer> setIdStudents = new HashSet<>();

    private Storage storage = new Storage();

    public Student() {
    }

    public Student(int idStudent, String firstName, String lastName, List<Integer> marksList) {
        this.idStudent = idStudent;
        this.firstName = firstName;
        this.lastName = lastName;
        this.marksList = marksList;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Integer> getMarksList() {
        return marksList;
    }

    public void setMarksList(List<Integer> marksList) {
        this.marksList = marksList;
    }


    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", marksList=" + marksList +
                '}';
    }

    public void addStudent() {
        System.out.println("Добавление студентов:");
        Student student = new Student(0, "studentFirstName1", "studentLastName1", new ArrayList<>(List.of(2, 3, 5, 2, 2)));
        storage.add(student);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Student student1 = new Student(1, "studentFirstName2", "studentLastName1", new ArrayList<>(List.of(5, 2, 5, 3, 2)));
        storage.add(student1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Student student2 = new Student(2, "studentFirstName3", "studentLastName1", new ArrayList<>(List.of(5, 4, 5, 3, 2)));
        storage.add(student2);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Student student3 = new Student(3, "studentFirstName4", "studentLastName1", new ArrayList<>(List.of(4, 4, 5, 3, 4)));
        storage.add(student3);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Student student4 = new Student(4, "studentFirstName5", "studentLastName1", new ArrayList<>(List.of(5, 4, 2, 3, 4)));
        storage.add(student4);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Student student5 = new Student(5, "studentFirstName6", "studentLastName1", new ArrayList<>(List.of(2, 3, 2, 3, 2)));
        storage.add(student5);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Student student6 = new Student(6, "studentFirstName7", "studentLastName1", new ArrayList<>(List.of(5, 4, 5, 3, 2)));
        storage.add(student6);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Student student7 = new Student(7, "studentFirstName8", "studentLastName1", new ArrayList<>(List.of(3, 4, 5, 3, 2)));
        storage.add(student7);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Student student8 = new Student(8, "studentFirstName9", "studentLastName1", new ArrayList<>(List.of(5, 3, 5, 2, 2)));
        storage.add(student8);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Student student9 = new Student(9, "studentFirstName10", "studentLastName1", new ArrayList<>(List.of(5, 4, 5, 3, 4)));
        storage.add(student9);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Прошло успешно!");
    }

    public void printAllStudentsInfo() {
        storage.printAllStudents();
    }

    public void sortStudentsMediumMark() {
        if (setIdStudents.size() == 0) {
            System.out.println("Для вычисления среднего балла в списке студентов должен быть хотя бы один студент");
        } else {
            System.out.println("Рейтинг студентов по среднему баллу");
            for (Integer id : setIdStudents) {
                double mediumMarkStudent = 0;
                Student student = storage.getStudentFromId(id);
                List<Integer> studentMediumMarks = student.getMarksList();
                OptionalDouble mediumMark = studentMediumMarks.stream()
                        .mapToInt(m -> m)
                        .average();
                if (mediumMark.isPresent()) {
                    mediumMarkStudent = mediumMark.getAsDouble();
                }
                System.out.println("ID : " + student.idStudent + ", Имя: " + student.getFirstName() +
                        ", Фамилия : " + student.getLastName() + ", Средний бал : " + mediumMarkStudent);
            }
        }
    }

    public ArrayList<Integer> sortStudentAsc(ArrayList<Integer> listIdStudents) {
        ArrayList<Student> listStudents = new ArrayList<>();
        for (Integer idStudent : listIdStudents) {
            listStudents.add(storage.getStudentFromId(idStudent));
        }
        HashMap<Integer, Double> mapStudentRating = new HashMap<>();
        for (Student student : listStudents) {
            mapStudentRating.put(student.getIdStudent(), calculateMediumMark(student.getMarksList()));
        }
        Map<Integer, Double> result = mapStudentRating.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        ArrayList<Integer> resultSortIdStudentByRating = new ArrayList<>();
        Iterator iterator = result.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Double> pair = (Map.Entry) iterator.next();
            resultSortIdStudentByRating.add(pair.getKey());
        }
        return resultSortIdStudentByRating;
    }

    private Double calculateMediumMark(List<Integer> marksList) {
        double sumMarks = 0;
        for (double mark : marksList) sumMarks += mark;
        return sumMarks / marksList.stream().count();
    }


}
