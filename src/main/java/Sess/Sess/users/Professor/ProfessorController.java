package Sess.Sess.users.Professor;

import Sess.Sess.database.Database;
import Sess.Sess.users.Student.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/professors")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfessorController {
    @GetMapping
    public ResponseEntity<ArrayList<Professor>> getAllProfessors() {
        Database db = Database.getInstance();
        return new ResponseEntity<>(db.getAllProfessors(), HttpStatus.OK);
    }

    @GetMapping("/{professorId}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable int professorId) {
        Database db = Database.getInstance();
        return new ResponseEntity<>(db.getProfessorById(professorId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody HashMap<String, String> professor) {
        Database db = Database.getInstance();
        String firstName = professor.get("firstName");
        String lastName = professor.get("lastName");
        int professorId = db.addProfessor(new Professor(firstName, lastName));
        return new ResponseEntity<>(db.getProfessorById(professorId), HttpStatus.OK);
    }
}
