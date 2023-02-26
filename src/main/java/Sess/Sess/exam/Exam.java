package Sess.Sess.exam;

import Sess.Sess.database.Database;
import Sess.Sess.database.bridge.exam_problem.Exam_Problem;
import Sess.Sess.problems.Problem;

import java.util.ArrayList;

public class Exam {
    private int id;
    private String title;
    private ArrayList<Problem> problems;

    public Exam() {
    }

    public Exam(String title) {
        this.title = title;
    }

    public Exam(int id, String title) {
        this.id = id;
        this.title = title;
    }

//    public ArrayList<Problem> getProblems() {
//        return problems;
//    }
//
//    public void setProblems() {
//        Exam_Problem ep = Exam_Problem.getInstance();
//        ArrayList<Integer> problemIds = ep.retrieve("examId", this.getId());
//
//        Database db = Database.getInstance();
//
//        for (Integer id : problemIds) {
//            Problem problem = db.getProblemById(id);
//        }
//    }

    public ArrayList<Problem> getProblems() {
        Exam_Problem ep = Exam_Problem.getInstance();
        ArrayList<Integer> problemIds = ep.retrieve("examId", this.getId());

        ArrayList<Problem> problems = new ArrayList<>();

        Database db = Database.getInstance();

        for (int problemId : problemIds) {
            problems.add(db.getProblemById(problemId));
        }

        return problems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "id:" + this.getId() + "," +
                "title:" + this.getTitle();
    }
}
