package Sess.Sess.course;

import Sess.Sess.CustomDate;
import Sess.Sess.database.Database;
import Sess.Sess.database.bridge.professor_course.Professor_Course;
import Sess.Sess.users.Professor.Professor;
import Sess.Sess.users.Student.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {
    @GetMapping
    public ResponseEntity<ArrayList<Course>> getAllCourses() {
        Database db = Database.getInstance();

        return new ResponseEntity<>(db.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable int courseId) {
        Database db = Database.getInstance();
        return new ResponseEntity<>(db.getCourseById(courseId), HttpStatus.OK);
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<ArrayList<Course>> getCourse_Professor(@PathVariable int professorId) {

        Database db = Database.getInstance();

        Professor professor = db.getProfessorById(professorId);

        return new ResponseEntity<>(professor.getCourses(), HttpStatus.OK);
    }

    @PostMapping("/professor/{professorId}")
    public ResponseEntity<Course> createCourse(@PathVariable int professorId
            , @RequestBody HashMap<String, String> course) {


        Database db = Database.getInstance();
        Professor_Course pc = Professor_Course.getInstance();

        String title = course.get("title");
        String syllabus = course.get("syllabus");
        String reference = course.get("reference");
        LocalDate midtermDate = CustomDate.getLocaleDate(course.get("midterm"));
        LocalDate finalDate = CustomDate.getLocaleDate(course.get("final"));

        Course newCourse = new Course(title, syllabus, reference, midtermDate, finalDate);

        int courseId = db.addCourse(newCourse);

        pc.add(professorId, courseId);

        return new ResponseEntity<>(db.getCourseById(courseId), HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ArrayList<Course>> getCourse_Student(@PathVariable int studentId) {
        Database db = Database.getInstance();

        Student student = db.getStudentById(studentId);

        return new ResponseEntity<>(student.getCourses(), HttpStatus.OK);
    }

    @PostMapping("/{courseId}/student/{studentId}")
    public ResponseEntity<Course> signUp(@PathVariable int courseId, @PathVariable int studentId) {
        Database db = Database.getInstance();

        Student student = db.getStudentById(studentId);
        student.signUp(courseId);

        return new ResponseEntity<>(db.getCourseById(courseId), HttpStatus.OK);
    }
}
