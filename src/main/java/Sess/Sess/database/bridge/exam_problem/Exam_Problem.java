package Sess.Sess.database.bridge.exam_problem;

import Sess.Sess.database.bridge.Bridge;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class Exam_Problem extends Bridge {


    static private Exam_Problem instance = null;

    private Exam_Problem() {}

    static public Exam_Problem getInstance() {
        if (instance == null) {
            instance = new Exam_Problem();
        }

        return instance;
    }

    @Override
    public int add(int examId, int problemId) {
        String line = "examId:" + examId + "," +
                "problemId:" + problemId;
        return this.appendToFile("exam_problem\\Exam_Problem.txt", line);
    }

    @Override
    public ArrayList<Integer> retrieve(@NotNull String key, int id) {
        ArrayList<String> lines = this.readlines("exam_problem\\Exam_Problem.txt");
        ArrayList<Integer> targetIds = new ArrayList<>();
        String targetKey;

        switch (key) {
            case "examId":
                targetKey = "problemId";
                break;
            case "problemId":
                targetKey = "examId";
                break;
            default:
                return targetIds;
        }

        for (String line : lines) {
            HashMap<String, String> properties = this.lineParser(line);
            if (Integer.parseInt(properties.get(key)) == id) {
                targetIds.add(Integer.parseInt(properties.get(targetKey)));
            }
        }

        return targetIds;
    }
}
