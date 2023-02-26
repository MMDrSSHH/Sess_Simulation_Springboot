package Sess.Sess.users.Student;


import Sess.Sess.database.Database;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
    @GetMapping
    public ResponseEntity<ArrayList<Student>> getAllStudents() {
        Database db = Database.getInstance();
        return new ResponseEntity<>(db.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable int studentId) {
        Database db = Database.getInstance();
        return new ResponseEntity<>(db.getStudentById(studentId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody HashMap<String, String> student) {
        Database db = Database.getInstance();
        String firstName = student.get("firstName");
        String lastName = student.get("lastName");
        int studentId = db.addStudent(new Student(firstName, lastName));
        return new ResponseEntity<>(db.getStudentById(studentId), HttpStatus.OK);
    }


}
