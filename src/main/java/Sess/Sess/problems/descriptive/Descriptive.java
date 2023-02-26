package Sess.Sess.problems.descriptive;

import Sess.Sess.problems.Problem;

public class Descriptive extends Problem {

    public Descriptive(int id, String title, String solution, String problem) {
        super(id, title, solution, problem, "descriptive");
    }

    public Descriptive(String title, String solution, String problem) {
        super(title, solution, problem, "descriptive");
    }
}
