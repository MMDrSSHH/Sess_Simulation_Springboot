package Sess.Sess.database;

import Sess.Sess.CustomDate;
import Sess.Sess.course.Course;
import Sess.Sess.exam.Exam;
import Sess.Sess.problems.Problem;
import Sess.Sess.semester.Semester;
import Sess.Sess.users.Professor.Professor;
import Sess.Sess.users.Student.Student;
import Sess.Sess.users.User;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Database {
    static private Database instance = null;

    private Database() {
    }


    static public Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * <p>Gets a line and pareses it into a HashMap String, String</p>
     *
     * @param line String
     * @return HashMap String, String
     */
    private @NotNull HashMap<String, String> lineParser(@NotNull String line) {
        HashMap<String, String> propertyMap = new HashMap<>();
        for (String property : line.trim().split(",")) {
            String key = property.trim().split(":")[0];
            String value = property.trim().split(":")[1];
            propertyMap.put(key, value);
        }

        return propertyMap;
    }


    /**
     * <p>If This method find's the user it returns the user otherwise null</p>
     *
     * @param userId int
     * @return User user
     */
    public User getUserById(int userId) {
        User user = null;


        ArrayList<String> lines = this.readlines("Users.txt");

        for (String line : lines) {
            HashMap<String, String> userProperties = this.lineParser(line);
            if (userProperties.get("id").equals(Integer.toString(userId))) {
                String firstName = userProperties.get("firstName");
                String lastName = userProperties.get("lastName");
                String roll = userProperties.get("roll");
                user = new User(userId, firstName, lastName, roll);
            }
        }

        return user;
    }

    public Student getStudentById(int studentId) {
        Student student = null;


        ArrayList<String> lines = this.readlines("Students.txt");

        for (String line : lines) {
            HashMap<String, String> studentProperties = this.lineParser(line);
            if (studentProperties.get("id").equals(Integer.toString(studentId))) {
                String firstName = studentProperties.get("firstName");
                String lastName = studentProperties.get("lastName");
                String roll = studentProperties.get("roll");
                student = new Student(studentId, firstName, lastName);
            }
        }

        return student;
    }

    public Professor getProfessorById(int userId) {
        Professor professor = null;


        ArrayList<String> lines = this.readlines("Professors.txt");

        for (String line : lines) {
            HashMap<String, String> professorProperties = this.lineParser(line);
            if (professorProperties.get("id").equals(Integer.toString(userId))) {
                String firstName = professorProperties.get("firstName");
                String lastName = professorProperties.get("lastName");
                String roll = professorProperties.get("roll");
                professor = new Professor(userId, firstName, lastName);
            }
        }

        return professor;
    }

    public Course getCourseById(int courseId) {
        Course course = null;


        ArrayList<String> lines = this.readlines("Courses.txt");

        for (String line : lines) {
            HashMap<String, String> courseProperties = this.lineParser(line);
            if (courseProperties.get("id").equals(Integer.toString(courseId))) {

                String title = courseProperties.get("title");
                String syllabus = courseProperties.get("syllabus");
                String reference = courseProperties.get("reference");
                LocalDate midtermDate = CustomDate.getLocaleDate(courseProperties.get("midterm"));
                LocalDate finalDate = CustomDate.getLocaleDate(courseProperties.get("final"));

                course = new Course(courseId, title, syllabus, reference, midtermDate, finalDate);
            }
        }

        return course;
    }

    public Exam getExamById(int examId) {
        Exam exam = null;


        ArrayList<String> lines = this.readlines("Exams.txt");

        for (String line : lines) {
            HashMap<String, String> properties = this.lineParser(line);
            if (properties.get("id").equals(Integer.toString(examId))) {

                String title = properties.get("title");


                exam = new Exam(examId, title);
            }
        }

        return exam;
    }

    public Semester getSemesterById(int semesterid) {
        Semester semester = null;


        ArrayList<String> lines = this.readlines("Semester.txt");

        for (String line : lines) {
            HashMap<String, String> properties = this.lineParser(line);
            if (properties.get("id").equals(Integer.toString(semesterid))) {

                String title = properties.get("title");
                LocalDate gradesSubmit = CustomDate.getLocaleDate(properties.get("gradesSubmit"));
                LocalDate startDate = CustomDate.getLocaleDate(properties.get("startDate"));
                LocalDate endDate = CustomDate.getLocaleDate(properties.get("endDate"));

                semester = new Semester(semesterid, title, gradesSubmit, startDate, endDate);
            }
        }

        return semester;
    }

    public Problem getProblemById(int problemId) {
        Problem problem = null;


        ArrayList<String> lines = this.readlines("Problems.txt");

        for (String line : lines) {
            HashMap<String, String> properties = this.lineParser(line);
            if (properties.get("id").equals(Integer.toString(problemId))) {

                String title = properties.get("title");
                String solution = properties.get("solution");
                String problemString = properties.get("problem");
                String problemType = properties.get("problemType");


                problem = new Problem(problemId, title, solution, problemString, problemType);
            }
        }

        return problem;
    }

    /**
     * @return A list of users
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<String> lines = this.readlines("Users.txt");

        for (String line : lines) {
            HashMap<String, String> properties = this.lineParser(line);
            int id = Integer.parseInt(properties.get("id"));
            String firstName = properties.get("firstName");
            String lastName = properties.get("lastName");
            String roll = properties.get("roll");

            users.add(new User(id, firstName, lastName, roll));
        }

        return users;
    }

    public ArrayList<Professor> getAllProfessors() {
        ArrayList<Professor> professors = new ArrayList<>();
        ArrayList<String> lines = this.readlines("Professors.txt");

        for (String line : lines) {
            HashMap<String, String> properties = this.lineParser(line);
            int id = Integer.parseInt(properties.get("id"));
            String firstName = properties.get("firstName");
            String lastName = properties.get("lastName");

            professors.add(new Professor(id, firstName, lastName));
        }

        return professors;
    }


    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<String> lines = this.readlines("Students.txt");

        for (String line : lines) {
            HashMap<String, String> properties = this.lineParser(line);
            int id = Integer.parseInt(properties.get("id"));
            String firstName = properties.get("firstName");
            String lastName = properties.get("lastName");

            students.add(new Student(id, firstName, lastName));
        }

        return students;
    }

    public HashMap<String, String> getDepartmentExpert() {
        return this.lineParser(this.readlines("DepatmentExpert.txt")
                .get(0));
    }

    /**
     * <p>Gets a user and add it to users table (file)</p>
     *
     * @param user
     */
    private int addUser(@NotNull User user) {
        return this.appendToFile("Users.txt", user.toString());
    }

    /**
     * <p>Gets a student and add it to the students table (file)</p>
     * <br/>
     * <p>It also insert the user into the users table</p>
     *
     * @param student
     */
    public int addStudent(@NotNull Student student) {
        int id = this.generateId();
        student.setId(id);

        if (this.appendToFile("Students.txt", student.toString()) == 0
                && this.addUser(student) == 0) {
            return id;
        }

        return -1;
    }

    /**
     * <p>Gets a professor and adds it to the professor table (file)</p>
     * <br/>
     * <p>It also inserts the user into the users table</p>
     *
     * @param professor
     */
    public int addProfessor(@NotNull Professor professor) {
        int id = this.generateId();
        professor.setId(id);

        if (this.appendToFile("Professors.txt", professor.toString()) == 0
                && this.addUser(professor) == 0) {
            return id;
        }

        return -1;
    }

    /**
     * <p>
     * reads the ids file, retrieves id from it, increaments the the id within the file
     * returns the non-increamented id to be used as a unique id
     * in database
     * </p>
     *
     * @return A unique id
     */
    private int generateId() {
        File idFile = new File("G:\\Sess\\src\\main\\java\\Sess\\Sess\\database\\Ids.txt");
        int id;
        try {
            BufferedReader br = new BufferedReader(new FileReader(idFile));
            String line = br.readLine();
            HashMap<String, String> idMap = this.lineParser(line);

            id = Integer.parseInt(idMap.get("ID"));

            BufferedWriter bw = new BufferedWriter(new FileWriter(idFile));
            String newLine = "ID" + ":" + (id + 1);
            bw.write(newLine);

            br.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return id;
    }

    /**
     * <p>
     * gets the filename and the string (line)
     * then appends the line in the last line of
     * the specified file (filename)
     * </p>
     *
     * @param fileName
     * @param line
     */
    private int appendToFile(String fileName, String line) {
        int status = 0;
        File file = new File("G:\\Sess\\src\\main\\java\\Sess\\Sess\\database\\" + fileName);
        try (FileWriter writer = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(writer);) {

            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            status = -1;
            throw new RuntimeException(e);
        }

        return status;
    }

    /**
     * <p>
     * Reads lines in the specified file and returns the lines in an ArrayList
     * </p>
     *
     * @param fileName Name of the file
     * @return An ArrayList of lines from the specified file
     */
    private @NotNull ArrayList<String> readlines(String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        try {

            File file = new File("G:\\Sess\\src\\main\\java\\Sess\\Sess\\database\\" + fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

    /**
     * <p>
     * Adds semester to the database
     * </p>
     *
     * @param semester semester A new semester to be added into the database
     * @return An int for error handling
     */
    public int addSemester(@NotNull Semester semester) {
        int id = this.generateId();
        semester.setId(id);
        if (this.appendToFile("Semester.txt", semester.toString()) == 0) {
            return id;
        }

        return -1;
    }


    public int addProblem(@NotNull Problem problem) {
        int id = this.generateId();
        problem.setId(id);
        if (this.appendToFile("Problems.txt", problem.toString()) == 0) {
            return id;
        }

        return -1;
    }


    public int addExam(@NotNull Exam exam) {
        int id = this.generateId();
        exam.setId(id);
        if (this.appendToFile("Exams.txt", exam.toString()) == 0) {
            return id;
        }

        return -1;
    }

    public int addCourse(@NotNull Course course) {
        int id = this.generateId();
        course.setId(id);

        if (this.appendToFile("Courses.txt", course.toString()) == 0) {
            return id;
        }

        return -1;
    }

    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<String> lines = this.readlines("Courses.txt");

        for (String line : lines) {

            HashMap<String, String> properties = this.lineParser(line);

            int id = Integer.parseInt(properties.get("id"));
            String title = properties.get("title");
            String syllabus = properties.get("syllabus");
            String reference = properties.get("reference");
            LocalDate midtermDate = LocalDate.parse(properties.get("midterm"));
            LocalDate finalDate = LocalDate.parse(properties.get("final"));

            courses.add(new Course(id, title, syllabus, reference, midtermDate, finalDate));
        }

        return courses;
    }

    public ArrayList<Exam> getAllExams() {
        ArrayList<Exam> exams = new ArrayList<>();
        ArrayList<String> lines = this.readlines("Exams.txt");

        for (String line : lines) {

            HashMap<String, String> properties = this.lineParser(line);

            int id = Integer.parseInt(properties.get("id"));
            String title = properties.get("title");

            exams.add(new Exam(id, title));
        }

        return exams;
    }

    public ArrayList<Semester> getAllSemesters() {
        ArrayList<Semester> semesters = new ArrayList<>();
        ArrayList<String> lines = this.readlines("Semester.txt");

        for (String line : lines) {

            HashMap<String, String> properties = this.lineParser(line);

            int id = Integer.parseInt(properties.get("id"));
            String title = properties.get("title");
            LocalDate gradesSubmit = CustomDate.getLocaleDate(properties.get("gradesSubmit"));
            LocalDate startDate = CustomDate.getLocaleDate(properties.get("startDate"));
            LocalDate endDate = CustomDate.getLocaleDate(properties.get("endDate"));

            semesters.add(new Semester(id, title, gradesSubmit, startDate, endDate));
        }

        return semesters;
    }

}