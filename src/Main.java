public class Main {
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        StudentManagementSystem sms;

        try {
            sms = new StudentManagementSystem();

            while (true) {
                int choice = ui.mainMenu();

                switch (choice) {
                    case 1 -> {
                        int id = ui.getInt("Enter ID: ");
                        String name = ui.getString("Enter Name: ");
                        int age = ui.getInt("Enter Age: ");
                        String course = ui.getString("Enter Course: ");
                        sms.addStudent(id, name, age, course);
                    }
                    case 2 -> sms.viewStudents();
                    case 3 -> {
                        int id = ui.getInt("Enter Student ID to update: ");
                        String name = ui.getString("Enter new Name: ");
                        int age = ui.getInt("Enter new Age: ");
                        String course = ui.getString("Enter new Course: ");
                        sms.updateStudent(id, name, age, course);
                    }
                    case 4 -> {
                        int id = ui.getInt("Enter Student ID to delete: ");
                        sms.deleteStudent(id);
                    }
                    case 5 -> {
                        System.out.println("Search by:\n1. Name\n2. Course");
                        int searchChoice = ui.getInt("Enter choice: ");
                        if (searchChoice == 1) {
                            String name = ui.getString("Enter name to search: ");
                            sms.searchByName(name);
                        } else if (searchChoice == 2) {
                            String course = ui.getString("Enter course to search: ");
                            sms.searchByCourse(course);
                        } else {
                            ui.showMessage("Invalid search choice.");
                        }
                    }
                    case 6 -> {
                        sms.closeConnection();
                        ui.showMessage("Successfully exited!");
                        ui.closeScanner();
                        return;
                    }
                    default -> ui.showMessage("Invalid choice.");
                }
            }
        } catch (Exception e) {
            System.out.println("System error: " + e.getMessage());
        }
    }
}
