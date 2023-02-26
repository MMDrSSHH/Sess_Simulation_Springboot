package Sess.Sess.users.Professor;

import Sess.Sess.course.Course;
import Sess.Sess.database.Database;
import Sess.Sess.database.bridge.course_exam.Course_Exam;
import Sess.Sess.database.bridge.professor_course.Professor_Course;
import Sess.Sess.database.bridge.student_course.Student_Course;
import Sess.Sess.exam.Exam;
import Sess.Sess.users.User;

import java.util.ArrayList;

public class Professor extends User {

    ArrayList<Course> courses;

    public Professor(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setRoll("professor");
    }

    public Professor(int id, String firstName, String lastName) {
        this(firstName, lastName);
        this.setId(id);
    }

    public ArrayList<Course> getCourses() {
        Professor_Course pc = Professor_Course.getInstance();
        ArrayList<Integer> courseIds = pc.retrieve("professorId", this.getId());
        ArrayList<Course> courses = new ArrayList<>();

        Database db = Database.getInstance();

        for (int courseId : courseIds) {
            courses.add(db.getCourseById(courseId));
        }

        return courses;
    }

    public int createExam(Exam exam, Course course) {
        Database db = Database.getInstance();
        Course_Exam ce = Course_Exam.getInstance();

        int courseId = course.getId();
        int examId = db.addExam(exam);
        return ce.add(courseId, examId);
    }
}
