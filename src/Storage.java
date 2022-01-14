import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String fileNameUniversity = "/lesson17Practice/src/DataUniversities.txt";
    private String fileNameGroups = "/lesson17Practice/src/DataGroups.txt";
    private String fileNameStudents = "/lesson17Practice/src/DataStudents.txt";
    private ObjectMapper objectMapper = new ObjectMapper();
    private FileWriter fileWriterStudent;
    private FileWriter fileWriterGroups;
    private FileWriter fileWriterUniversity;

    public void add(Student student) {
        if (Student.setIdStudents.contains(student.getIdStudent())) {
            System.out.println("Студент с таким ID = " + student.getIdStudent() + " уже существует\n");
        } else {
            //Если уже такой файл есть, то удаляем, т.к. в файл дописывается информация
            if (Student.setIdStudents.size() == 0) {
                File file = new File(fileNameStudents);
                if (file.exists()) {
                    file.delete();
                }
            }
            Student.setIdStudents.add(student.getIdStudent());

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(student);
                //В файл дописывываются данные
                fileWriterStudent = new FileWriter(new File(fileNameStudents), true);
                fileWriterStudent.write(json + "\n");
                fileWriterStudent.close();

                System.out.println();

                System.out.println(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Студент с ID = " + student.getIdStudent() + " успешно добавлен!\n");
        }
    }

    public void printAllStudents() {
        if (Student.setIdStudents.size() == 0) {
            System.out.println("\nВ базе нет студентов\n");
        } else {
            File file = new File(fileNameStudents);
            if (!file.exists() || file.length() == 0) {
                System.out.println("\nВ базе нет студентов\n");
            } else {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileNameStudents));
                    System.out.println("\nВывод информации о всех студентах\n");
                    while (bufferedReader.ready()) {
                        String json = bufferedReader.readLine();
                        System.out.println(json);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Student getStudentFromId(int idStudent) {
        if (Student.setIdStudents.contains(idStudent)) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileNameStudents));
                while (bufferedReader.ready()) {
                    String json = bufferedReader.readLine();
                    Student student = objectMapper.readValue(json, Student.class);
                    if (student.getIdStudent() == idStudent) {
                        return student;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Студента с данным ID не существует");
        }
        return new Student(-1, "", "", new ArrayList<>());
    }

    public void add(Group group) {
        try {
            if (Student.setIdStudents.size() == 0) {
                System.out.println("Группа не добавлена. Нужно добавить студентов\n");
            } else if (Group.idListOfGroups.contains(group.getIdGroup())) {
                System.out.println("Группа с таким ID = " + group.getIdGroup() + " уже существует\n");
            } else {
                //Если уже такой файл есть, то удаляем, т.к. в файл дописывается информация
                if (Group.idListOfGroups.size() == 0) {
                    File file = new File(fileNameGroups);
                    if (file.exists()) {
                        file.delete();
                    }
                }
                Group.idListOfGroups.add(group.getIdGroup());

                try {
                    String json = objectMapper.writeValueAsString(group);
                    //В файл дописываются данные
                    fileWriterGroups = new FileWriter(new File(fileNameGroups), true);
                    fileWriterGroups.write(json + "\n");
                    fileWriterGroups.close();

                    System.out.println(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Группа с ID = " + group.getIdGroup() + " успешно добавлена!\n");
            }
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }

    }

    public void printAllGroups() {
        if (Group.idListOfGroups.size() == 0) {
            System.out.println("\nВ базе нет групп\n");
        } else {
            File file = new File(fileNameGroups);
            if (!file.exists() || file.length() == 0) {
                System.out.println("\nВ базе нет групп\n");
            } else {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileNameGroups));
                    System.out.println("\nВывод информации о всех группах\n");
                    while (bufferedReader.ready()) {
                        String json = bufferedReader.readLine();
                        System.out.println(json);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Group getGroup(int idGroup) {
        if (Group.idListOfGroups.contains(idGroup)) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileNameGroups));
                while (bufferedReader.ready()) {
                    String json = bufferedReader.readLine();
                    Group group = objectMapper.readValue(json, Group.class);
                    if (group.getIdGroup() == idGroup) {
                        return group;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Студента с данным ID не существует");
        }
        return new Group(-1, "", new ArrayList<>());
    }

    public void add(University university) {
        if (Group.idListOfGroups.size() == 0) {
            System.out.println("Университет не добавлен. Нужно добавить группы\n");
        } else if (University.listIdUniversity.contains(university.getIdUniversity())) {
            System.out.println("Университет с таким ID = " + university.getIdUniversity() + " уже существует\n");
        } else {
            //Если уже такой файл есть, то удаляем, т.к. в файл дописывается информация
            if (University.listIdUniversity.size() == 0) {
                File file = new File(fileNameUniversity);
                if (file.exists()) {
                    file.delete();
                }
            }

            University.listIdUniversity.add(university.getIdUniversity());
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                String json = objectMapper.writeValueAsString(university);

                fileWriterUniversity = new FileWriter(new File(fileNameUniversity), true);
                fileWriterUniversity.write(json + "\n");
                fileWriterUniversity.close();

                System.out.println(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Университет с ID = " + university.getIdUniversity() + " успешно добавлен!\n");
        }
    }

    public void printAllUniversity() {
        if (University.listIdUniversity.size() == 0) {
            System.out.println("\nВ базе нет университетов\n");
        } else {
            File file = new File(fileNameUniversity);
            if (!file.exists() || file.length() == 0) {
                System.out.println("\nВ базе нет университетов\n");
            } else {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileNameUniversity));
                    System.out.println("\nВывод информации о всех университеах\n");
                    while (bufferedReader.ready()) {
                        String json = bufferedReader.readLine();
                        System.out.println(json);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public University getUniversity(int idUniversity) {
        if (University.listIdUniversity.contains(idUniversity)) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileNameUniversity));
                while (bufferedReader.ready()) {
                    String json = bufferedReader.readLine();
                    University university = objectMapper.readValue(json, University.class);
                    if (university.getIdUniversity() == idUniversity) {
                        return university;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Университета с данным ID не существует");
        }
        return new University(-1, "", new ArrayList<>());
    }
}
