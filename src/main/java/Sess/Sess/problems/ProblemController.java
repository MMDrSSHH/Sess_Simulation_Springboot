package Sess.Sess.problems;

import Sess.Sess.database.Database;
import Sess.Sess.database.bridge.exam_problem.Exam_Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/problems")
@CrossOrigin(origins = "http://localhost:3000")
public class ProblemController {

    @PostMapping
    public ResponseEntity<Problem> createProblem(@RequestBody HashMap<String, String> problemMap) {
        String title = problemMap.get("title");
        String solution = problemMap.get("solution");
        String problem = problemMap.get("problem");
        String problemType = problemMap.get("problemType");
        int examId = Integer.parseInt(problemMap.get("examId"));

        Exam_Problem ep = Exam_Problem.getInstance();
        Database db = Database.getInstance();

        int problemId = db.addProblem(new Problem(title, solution, problem, problemType));

        ep.add(examId, problemId);

        return new ResponseEntity<>(db.getProblemById(problemId), HttpStatus.OK);
    }
}
