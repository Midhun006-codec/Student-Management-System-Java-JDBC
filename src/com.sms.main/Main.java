import dao.AdminDAO;
import dao.AdminDAOImpl;
import dao.StudentDAO;
import dao.StudentDAOImpl;
import java.util.Scanner;
import model.Admin;
import model.Student;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static StudentDAO studentDAO = new StudentDAOImpl();
    static AdminDAO adminDAO = new AdminDAOImpl();
    static Admin loggedInAdmin = null;

    public static void main(String[] args) {
        while (true) {
            if (loggedInAdmin == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }

    static void showLoginMenu() {
        System.out.println("\n========== STUDENT MANAGEMENT SYSTEM ==========");
        System.out.println("1. Admin Login");
        System.out.println("2. Exit");
        System.out.print("Enter choice: ");

        if (!sc.hasNextInt()) {
            System.out.println("❌ Invalid input");
            sc.next();
            return;
        }

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                adminLogin();
                break;
            case 2:
                System.out.println("Thank you!");
                sc.close();
                System.exit(0);
            default:
                System.out.println("❌ Invalid choice");
        }
    }

    static void adminLogin() {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        Admin admin = adminDAO.getAdminByUsername(username);

        if (admin != null && admin.getPassword().equals(password)) {
            loggedInAdmin = admin;
            System.out.println("✅ Login successful! Welcome " + admin.getUsername());
        } else {
            System.out.println("❌ Invalid credentials");
        }
    }

    static void showMainMenu() {
        System.out.println("\n========== STUDENT MANAGEMENT SYSTEM ==========");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Search Student by Name");
        System.out.println("7. Search Student by Course");
        System.out.println("8. Admin Settings");
        System.out.println("9. Logout");
        System.out.println("10. Exit");
        System.out.print("Enter choice: ");

        if (!sc.hasNextInt()) {
            System.out.println("❌ Invalid input");
            sc.next();
            return;
        }

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                viewAllStudents();
                break;
            case 3:
                searchStudentById();
                break;
            case 4:
                updateStudent();
                break;
            case 5:
                deleteStudent();
                break;
            case 6:
                searchStudentByName();
                break;
            case 7:
                searchStudentByCourse();
                break;
            case 8:
                adminSettings();
                break;
            case 9:
                loggedInAdmin = null;
                System.out.println("✅ Logged out successfully");
                break;
            case 10:
                System.out.println("Thank you!");
                sc.close();
                System.exit(0);
            default:
                System.out.println("❌ Invalid choice");
        }
    }

    static void addStudent() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Course: ");
        String course = sc.nextLine();

        Student s = new Student(name, email, phone, course);
        studentDAO.addStudent(s);
        System.out.println("✅ Student added successfully");
    }

    static void viewAllStudents() {
        var students = studentDAO.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("⚠️ No students found");
        } else {
            System.out.println("\n--- All Students ---");
            System.out.println("ID | Name | Email | Phone | Course");
            System.out.println("---+------+-------+-------+--------");
            students.forEach(st ->
                    System.out.println(st.getStudentId() + " | " +
                            st.getName() + " | " +
                            st.getEmail() + " | " +
                            st.getPhone() + " | " +
                            st.getCourse())
            );
        }
    }

    static void searchStudentById() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Student student = studentDAO.getStudentById(id);

        if (student != null) {
            System.out.println("\n--- Student Details ---");
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Phone: " + student.getPhone());
            System.out.println("Course: " + student.getCourse());
        } else {
            System.out.println("❌ Student not found");
        }
    }

    static void updateStudent() {
        System.out.print("Enter Student ID: ");
        int uid = sc.nextInt();
        sc.nextLine();

        Student existing = studentDAO.getStudentById(uid);
        if (existing == null) {
            System.out.println("❌ Student not found");
            return;
        }

        System.out.print("New Name (current: " + existing.getName() + "): ");
        String n = sc.nextLine();
        System.out.print("New Email (current: " + existing.getEmail() + "): ");
        String e = sc.nextLine();
        System.out.print("New Phone (current: " + existing.getPhone() + "): ");
        String p = sc.nextLine();
        System.out.print("New Course (current: " + existing.getCourse() + "): ");
        String c = sc.nextLine();

        Student up = new Student(n, e, p, c);
        up.setStudentId(uid);
        studentDAO.updateStudent(up);
        System.out.println("✅ Student updated successfully");
    }

    static void deleteStudent() {
        System.out.print("Enter Student ID: ");
        int did = sc.nextInt();
        sc.nextLine();

        Student student = studentDAO.getStudentById(did);
        if (student == null) {
            System.out.println("❌ Student not found");
            return;
        }

        System.out.print("Are you sure? (yes/no): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            studentDAO.deleteStudent(did);
            System.out.println("✅ Student deleted successfully");
        } else {
            System.out.println("❌ Deletion cancelled");
        }
    }

    static void searchStudentByName() {
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        var students = studentDAO.getAllStudents();
        var filtered = students.stream()
                .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();

        if (filtered.isEmpty()) {
            System.out.println("⚠️ No students found with name: " + name);
        } else {
            System.out.println("\n--- Search Results ---");
            System.out.println("ID | Name | Email | Phone | Course");
            System.out.println("---+------+-------+-------+--------");
            filtered.forEach(st ->
                    System.out.println(st.getStudentId() + " | " +
                            st.getName() + " | " +
                            st.getEmail() + " | " +
                            st.getPhone() + " | " +
                            st.getCourse())
            );
        }
    }

    static void searchStudentByCourse() {
        System.out.print("Enter Course Name: ");
        String course = sc.nextLine();

        var students = studentDAO.getAllStudents();
        var filtered = students.stream()
                .filter(s -> s.getCourse().equalsIgnoreCase(course))
                .toList();

        if (filtered.isEmpty()) {
            System.out.println("⚠️ No students found in course: " + course);
        } else {
            System.out.println("\n--- Students in " + course + " ---");
            System.out.println("ID | Name | Email | Phone");
            System.out.println("---+------+-------+-------");
            filtered.forEach(st ->
                    System.out.println(st.getStudentId() + " | " +
                            st.getName() + " | " +
                            st.getEmail() + " | " +
                            st.getPhone())
            );
        }
    }

    static void adminSettings() {
        System.out.println("\n--- Admin Settings ---");
        System.out.println("1. View Profile");
        System.out.println("2. Change Password");
        System.out.println("3. Back");
        System.out.print("Enter choice: ");

        if (!sc.hasNextInt()) {
            System.out.println("❌ Invalid input");
            sc.next();
            return;
        }

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.println("\n--- Admin Profile ---");
                System.out.println("ID: " + loggedInAdmin.getAdminId());
                System.out.println("Username: " + loggedInAdmin.getUsername());
                System.out.println("Email: " + loggedInAdmin.getEmail());
                break;
            case 2:
                changePassword();
                break;
            case 3:
                break;
            default:
                System.out.println("❌ Invalid choice");
        }
    }

    static void changePassword() {
        System.out.print("Current Password: ");
        String currentPass = sc.nextLine();

        if (!currentPass.equals(loggedInAdmin.getPassword())) {
            System.out.println("❌ Incorrect password");
            return;
        }

        System.out.print("New Password: ");
        String newPass = sc.nextLine();
        System.out.print("Confirm Password: ");
        String confirmPass = sc.nextLine();

        if (!newPass.equals(confirmPass)) {
            System.out.println("❌ Passwords do not match");
            return;
        }

        loggedInAdmin.setPassword(newPass);
        adminDAO.updateAdmin(loggedInAdmin);
        System.out.println("✅ Password changed successfully");
    }
}
