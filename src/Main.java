import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/myfirstdatabase", "root", "my secret password");
            System.out.println("Connected to the database.");

            Statement statement = connection.createStatement();

            // SQL query to retrieve students born in the year 1994
            String query = "SELECT first_name, last_name, email FROM students WHERE YEAR(date_of_birth) = 1994";

            ResultSet resultSet = statement.executeQuery(query);

            List<Student> students = new ArrayList<>();

            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");

                Student student = new Student(firstName, lastName, email);
                students.add(student);
            }

            for (Student student : students) {
                System.out.println("First Name: " + student.getFirstName());
                System.out.println("Last Name: " + student.getLastName());
                System.out.println("Email: " + student.getEmail());
                System.out.println("-----------------------");
            }
            //query 2
            // SQL query to retrieve courses name and number od students in each course
            ResultSet resultSet2 = statement.executeQuery(
                    "SELECT Courses.course_name, COUNT(StudentCourse.student_id) AS num_students " +
                            "FROM Courses, StudentCourse " +
                            "WHERE Courses.course_id = StudentCourse.course_id " +
                            "GROUP BY Courses.course_name");

            List<Course> results = new ArrayList<>();

            while (resultSet2.next()) {
                String courseName = resultSet2.getString("course_name");
                int numStudents = resultSet2.getInt("num_students");

                Course course = new Course(courseName, numStudents);

                results.add(course);
            }

            for (Course course : results) {
                System.out.println("Course: " + course.getCourseName());
                System.out.println("Number of Students: " + course.getNumStudents());
                System.out.println();
            }


            //query 3
            //query to retrieve the first name, last name, and course name of all students
            // enrolled in the course "Computer Science
            String query3 = "SELECT Students.first_name, Students.last_name, Courses.course_name " +
                    "FROM Students " +
                    "JOIN StudentCourse ON Students.student_id = StudentCourse.student_id " +
                    "JOIN Courses ON StudentCourse.course_id = Courses.course_id " +
                    "WHERE Courses.course_name = 'Computer Science'";
            ResultSet resultSet3 = statement.executeQuery(query3);

            List<Student> csstudents = new ArrayList<>();
            while (resultSet3.next()) {
                String firstName = resultSet3.getString("first_name");
                String lastName = resultSet3.getString("last_name");
                String courseName = resultSet3.getString("course_name");

                Student student = new Student(firstName, lastName,"",courseName);
                csstudents.add(student);
            }

            for (Student student : csstudents) {
                System.out.println(student.getFirstName() + " " + student.getLastName()+ " "+student.getCourseName());
            }

            //query4
            // retrieve the email addresses of students whose first name starts with the letter 'J'
            // and last name starts with the letter 'D'
            String query4 = "SELECT email " +
                    "FROM Students " +
                    "WHERE first_name LIKE 'J%' AND last_name LIKE 'D%'";

            ResultSet resultSet4 = statement.executeQuery(query4);

            List<String> emailList = new ArrayList<>();

            while (resultSet4.next()) {
                String email = resultSet4.getString("email");
                emailList.add(email);
            }

            for (String email : emailList) {
                System.out.println("Email: " + email);
            }
            //query 5
            //query to retrieve the course names and instructor names of all courses taught by Professor Brown

            String query5 = "SELECT course_name, instructor_name " +
                    "FROM Courses " +
                    "WHERE instructor_name = 'Professor Brown'";

            ResultSet resultSet5 = statement.executeQuery(query5);

            List<Course> coursesList = new ArrayList<>();

            while (resultSet5.next()) {
                String courseName = resultSet5.getString("course_name");
                String instructorName = resultSet5.getString("instructor_name");

                Course course = new Course(courseName,instructorName);
                coursesList.add(course);
            }

            for (Course course : coursesList) {
                System.out.println("Course: " + course.getCourseName() + ", Instructor: " + course.getInstructorName());
            }

            //query6
            //SQL query to retrieve the student ID, first name, and last name of students
            // who are enrolled in both the course "Mathematics" and the course "History".
            String query6 = "SELECT Students.student_id, Students.first_name, Students.last_name FROM Students " +
                    "JOIN StudentCourse ON Students.student_id = StudentCourse.student_id " +
                    "JOIN Courses ON StudentCourse.course_id = Courses.course_id " +
                    "WHERE Courses.course_name = 'Mathematics' AND Students.student_id IN " +
                    "(SELECT student_id FROM StudentCourse WHERE course_id IN " +
                    "(SELECT course_id FROM Courses WHERE course_name = 'History'))";

            ResultSet resultSet6 = statement.executeQuery(query6);

            List<Student> students2 = new ArrayList<>();


            while (resultSet6.next()) {
                int studentId = resultSet6.getInt("student_id");
                String firstName = resultSet6.getString("first_name");
                String lastName = resultSet6.getString("last_name");

                Student student = new Student(studentId, firstName, lastName);
                students2.add(student);
            }

            for (Student student :students2 ) {
                System.out.println("Student ID: " + student.getStudentId());
                System.out.println("First Name: " + student.getFirstName());
                System.out.println("Last Name: " + student.getLastName());
                System.out.println("------------");
            }

            //write content of query into file
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter("fileNameOrPath"));
                for (Student student : students2) {
                    String line = student.getStudentId() + "," + student.getFirstName() + "," + student.getLastName();
                    writer.write(line);
                    writer.write(System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //query7
            // SQL query to retrieve the student ID, first name, and last name of students
            // who are not enrolled in any course
            String query7 = "SELECT student_id, first_name, last_name FROM Students " +
                    "WHERE student_id NOT IN (SELECT student_id FROM StudentCourse)";

            ResultSet resultSet7 = statement.executeQuery(query7);

            List<Student> students3 = new ArrayList<>();

            while (resultSet7.next()) {
                int studentId = resultSet7.getInt("student_id");
                String firstName = resultSet7.getString("first_name");
                String lastName = resultSet7.getString("last_name");

                Student student = new Student(studentId, firstName, lastName);
                students3.add(student);
            }

            for (Student student : students3) {
                System.out.println(student.getStudentId() + " " + student.getFirstName() + " " + student.getLastName());
            }

            resultSet.close();
            resultSet2.close();
            resultSet3.close();
            resultSet4.close();
            resultSet5.close();
            resultSet6.close();
            resultSet7.close();

            statement.close();

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   }