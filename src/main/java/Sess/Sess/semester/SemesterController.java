package Sess.Sess.semester;

import Sess.Sess.CustomDate;
import Sess.Sess.database.Database;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/semesters")
@CrossOrigin(origins = "http://localhost:3000")
public class SemesterController {

    @GetMapping
    public ResponseEntity<ArrayList<Semester>> getAllSemesters() {
        Database db = Database.getInstance();
        return new ResponseEntity<>(db.getAllSemesters(), HttpStatus.OK);
    }

    @GetMapping("/{semesterId}")
    public ResponseEntity<Semester> getSemesterById(@PathVariable int semesterId) {
        Database db = Database.getInstance();
        return new ResponseEntity<>(db.getSemesterById(semesterId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Semester> createSemester(@RequestBody HashMap<String, String> semester) {
        String title = semester.get("title");
        LocalDate gradesSubmit = CustomDate.getLocaleDate(semester.get("gradesSubmit"));
        LocalDate startDate = CustomDate.getLocaleDate(semester.get("startDate"));
        LocalDate endDate = CustomDate.getLocaleDate(semester.get("endDate"));

        Database db = Database.getInstance();

        int semesterId = db.addSemester(new Semester(title, gradesSubmit, startDate, endDate));

        return new ResponseEntity<>(db.getSemesterById(semesterId), HttpStatus.OK);
    }
}

