package Sess.Sess.problems.test;

import Sess.Sess.problems.Problem;

public class Test extends Problem {

    public Test(int id, String title, String solution, String problem) {
        super(id, title, solution, problem, "test");
    }

    public Test(String title, String solution, String problem) {
        super(title, solution, problem, "test");
    }
}
