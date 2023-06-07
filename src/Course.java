public class Course {
    private String courseName;
    private int numStudents;
    private String instructorName;


    public Course(String courseName, int numStudents) {
        this.courseName = courseName;
        this.numStudents = numStudents;
    }

    public Course(String courseName, String instructorName) {
        this.courseName = courseName;
        this.instructorName = instructorName;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public String getInstructorName() {
        return instructorName;
    }

}