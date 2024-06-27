import java.io.*;
import java.util.*;


class Student {
    private final String name;
    private final int rollNumber;
    private final String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber=" + rollNumber +
                ", grade='" + grade + '\'' +
                '}';
    }
}


class StudentManagementSystem {
    private final List<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("All students:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public void saveStudentsToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.println(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
            }
            System.out.println("Student data saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving student data: " + e.getMessage());
        }
    }

    public void loadStudentsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            students.clear(); 
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int rollNumber = Integer.parseInt(parts[1].trim());
                String grade = parts[2];
                students.add(new Student(name, rollNumber, grade));
            }
            System.out.println("Student data loaded from " + filename);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading student data: " + e.getMessage());
        }
    }
}

// Student Management System UI
public class StudentManagementSystemUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManagementSystem sms = new StudentManagementSystem();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add a new student");
            System.out.println("2. Remove a student");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Save students to file");
            System.out.println("6. Load students from file");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> removeStudent();
                case 3 -> searchStudent();
                case 4 -> displayAllStudents();
                case 5 -> saveStudentsToFile();
                case 6 -> loadStudentsFromFile();
                case 7 -> exit = true;
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }

        System.out.println("Exiting Student Management System. Goodbye!");
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student roll number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter student grade: ");
        String grade = scanner.nextLine();

        Student newStudent = new Student(name, rollNumber, grade);
        sms.addStudent(newStudent);
        System.out.println("Student added successfully.");
    }

    private static void removeStudent() {
        System.out.print("Enter roll number of student to remove: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); 

        sms.removeStudent(rollNumber);
        System.out.println("Student removed successfully.");
    }

    private static void searchStudent() {
        System.out.print("Enter roll number of student to search: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); 

        Student foundStudent = sms.searchStudent(rollNumber);
        if (foundStudent != null) {
            System.out.println("Student found: " + foundStudent);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void displayAllStudents() {
        sms.displayAllStudents();
    }

    private static void saveStudentsToFile() {
        System.out.print("Enter filename to save student data: ");
        String filename = scanner.nextLine();

        sms.saveStudentsToFile(filename);
    }

    private static void loadStudentsFromFile() {
        System.out.print("Enter filename to load student data: ");
        String filename = scanner.nextLine();

        sms.loadStudentsFromFile(filename);
    }
}
