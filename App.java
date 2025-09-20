

public class App {
    private static User currentUser = null;
    private static final EnrollmentManagement enrollmentManager = new EnrollmentManagement();
    private static final UserManagement userManager = new UserManagement();
    private static final CourseManagement courseManager = new CourseManagement();

    public static void main(String[] args) {
        System.out.println("Welcome to the Student Management System!");
        while (true) {
            
            if (currentUser == null) {
                showInitialMenu();
            } else if (currentUser.getUserType().equals("admin")) {
                showAdminMenu();
            } else {
                showStudentMenu();
            }
        }
    }

    private static void showInitialMenu() {
        System.out.println("\n1. Login");
        System.out.println("2. Register as Student");
        System.out.println("3. Exit");
        int choice = Utils.readInt("Enter your choice: ");

        switch (choice) {
            case 1:
                currentUser = enrollmentManager.login();
                break;
            case 2:
                enrollmentManager.register();
                break;
            case 3:
                System.out.println("Exiting application.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void showAdminMenu() {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. Add a Student");
        System.out.println("2. Delete a Student");
        System.out.println("3. Add a Course");
        System.out.println("4. Delete a Course");
        System.out.println("5. Logout");
        int choice = Utils.readInt("Enter your choice: ");

        switch (choice) {
            case 1:
                userManager.addStudent(currentUser);
                break;
            case 2:
                userManager.deleteStudent(currentUser);
                break;
            case 3:
                courseManager.addCourse(currentUser);
                break;
            case 4:
                courseManager.deleteCourse(currentUser);
                break;
            case 5:
                currentUser = null;
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void showStudentMenu() {
        System.out.println("\nStudent Menu:");
        System.out.println("1. Enroll in a Course");
        System.out.println("2. Drop a Course");
        System.out.println("3. View My Enrolled Courses");
        System.out.println("4. Logout");
        int choice = Utils.readInt("Enter your choice: ");

        switch (choice) {
            case 1:
                enrollmentManager.addCourseToStudent(currentUser);
                break;
            case 2:
                enrollmentManager.deleteCourseFromStudent(currentUser);
                break;
            case 3:
                if (currentUser.getCourses().isEmpty()) {
                    System.out.println("You are not enrolled in any courses.");
                } else {
                    System.out.println("Your courses:");
                    for (int i = 0; i < currentUser.getCourses().size(); i++) {
                        System.out.println((i + 1) + ". " + currentUser.getCourses().get(i).getCourseName());
                    }
                }
                break;
            case 4:
                currentUser = null;
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
}
