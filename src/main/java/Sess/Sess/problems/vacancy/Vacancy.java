package Sess.Sess.problems.vacancy;

import Sess.Sess.problems.Problem;

public class Vacancy extends Problem {
    public Vacancy(String title, String solution, String problem) {
        super(title, solution, problem, "vacancy");
    }

    public Vacancy(int id, String title, String solution, String problem) {
        super(id, title, solution, problem, "vacancy");
    }
}
