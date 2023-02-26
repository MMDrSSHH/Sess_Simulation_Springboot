package Sess.Sess.users.Student;

import Sess.Sess.course.Course;
import Sess.Sess.database.Database;
import Sess.Sess.database.bridge.student_course.Student_Course;
import Sess.Sess.users.User;

import java.util.ArrayList;

public class Student extends User {

    private ArrayList<Course> courses;
    public Student(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setRoll("student");
    }

    public Student(int id, String firstName, String lastName) {
        this(firstName, lastName);
        this.setId(id);
    }

    public int signUp(int courseId) {
        Student_Course sc = Student_Course.getInstance();
        return sc.add(this.getId(), courseId);
    }

    public ArrayList<Course> getCourses() {
        Student_Course sc = Student_Course.getInstance();
        ArrayList<Integer> courseIds = sc.retrieve("studentId", this.getId());
        ArrayList<Course> courses = new ArrayList<>();

        Database db = Database.getInstance();

        for (int courseId : courseIds) {
            courses.add(db.getCourseById(courseId));
        }

        return courses;
    }
}
