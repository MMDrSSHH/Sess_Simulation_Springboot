package Sess.Sess.exam;

import Sess.Sess.database.Database;
import Sess.Sess.database.bridge.course_exam.Course_Exam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;


@RestController
@RequestMapping("/exams")
@CrossOrigin(origins = "http://localhost:3000")
public class ExamController {
    @GetMapping
    public ResponseEntity<ArrayList<Exam>> getAllExams() {
        Database db = Database.getInstance();

        return new ResponseEntity<>(db.getAllExams(), HttpStatus.OK);
    }

    @GetMapping("{examId}")
    public ResponseEntity<Exam> getExamById(@PathVariable int examId) {
        Database db = Database.getInstance();

        return new ResponseEntity<>(db.getExamById(examId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody HashMap<String, String> exam) {

        Database db = Database.getInstance();
        Course_Exam ce = Course_Exam.getInstance();

        String title = exam.get("title");
        int courseId = Integer.parseInt(exam.get("courseId"));

        Exam newExam = new Exam(title);

        int examId = db.addExam(newExam);
        ce.add(courseId, examId);

        return new ResponseEntity<>(db.getExamById(examId), HttpStatus.OK);
    }
}
