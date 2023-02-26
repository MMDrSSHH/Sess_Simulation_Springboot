package Sess.Sess.problems;

public class Problem {
    private int id;
    private String title;
    private String solution;
    private String problem;
    private String problemType;

    public Problem() {
    }

    public Problem(int id, String title, String solution, String problem, String problemType) {
        this.id = id;
        this.title = title;
        this.solution = solution;
        this.problem = problem;
        this.problemType = problemType;
    }

    public Problem(String title, String solution, String problem, String problemType) {
        this.title = title;
        this.solution = solution;
        this.problem = problem;
        this.problemType = problemType;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
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

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    @Override
    public String toString() {
        return "id:" + this.getId() + ","
                + "title:" + this.getTitle() + ","
                + "problem:" + this.getProblem() + ","
                + "solution:" + this.getSolution() + ","
                + "problemType:" + this.getProblemType();
    }
}
