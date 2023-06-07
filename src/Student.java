class Student {
    private int student_id;
    private String firstName;
    private String lastName;
    private String email;
    private String courseName;


    public Student(String firstName, String lastName, String email) {
        this(firstName, lastName, email, null);
    }
    public Student(int student_id, String firstName, String lastName) {

        this.student_id = student_id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(String firstName, String lastName, String email, String courseName) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.courseName = courseName;
    }

    public int getStudentId() {
        return student_id;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCourseName() {
        return courseName;
    }

}