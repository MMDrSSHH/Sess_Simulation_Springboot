package Sess.Sess.database.bridge.professor_course;

import Sess.Sess.database.bridge.Bridge;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Professor_Course extends Bridge {

    static private Professor_Course instance = null;

    private Professor_Course() {}

    static public Professor_Course getInstance() {
        if (instance == null) {
            instance = new Professor_Course();
        }

        return instance;
    }

    @Override
    public int add(int professorId, int courseId) {
        String line = "professorId:" + professorId + "," +
                "courseId:" + courseId;
        return this.appendToFile("professor_course\\Professor_Course.txt", line);
    }

    @Override
    public ArrayList<Integer> retrieve(@NotNull String key, int id) {
        ArrayList<String> lines = this.readlines("professor_course\\Professor_Course.txt");
        ArrayList<Integer> targetIds = new ArrayList<>();
        String targetKey;

        switch (key) {
            case "professorId":
                targetKey = "courseId";
                break;
            case "courseId":
                targetKey = "professorId";
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
