package Sess.Sess.database.bridge.semester_course;

import Sess.Sess.database.bridge.Bridge;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.HashMap;

public class Semester_Course extends Bridge {

    static private Semester_Course instance = null;

    private Semester_Course() {}

    static public Semester_Course getInstance() {
        if (instance == null) {
            instance = new Semester_Course();
        }

        return instance;
    }

    @Override
    public int add(int semesterId, int courseId) {
        String line = "semesterId:" + semesterId + "," +
                "courseId:" + courseId;
        return this.appendToFile("semester_course\\Semester_Course.txt", line);
    }

    @Override
    public ArrayList<Integer> retrieve(@NotNull String key, int id) {
        ArrayList<String> lines = this.readlines("semester_course\\Semester_Course.txt");
        ArrayList<Integer> targetIds = new ArrayList<>();
        String targetKey;

        switch (key) {
            case "semesterId":
                targetKey = "courseId";
                break;
            case "courseId":
                targetKey = "semesterId";
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
