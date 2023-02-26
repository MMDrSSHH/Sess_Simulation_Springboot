package Sess.Sess.database.bridge.student_course;

import Sess.Sess.database.bridge.Bridge;

import org.jetbrains.annotations.NotNull;

import java.util.*;


public class Student_Course extends Bridge {

    static private Student_Course instance = null;

    private Student_Course() {}

    static public Student_Course getInstance() {
        if (instance == null) {
            instance = new Student_Course();
        }

        return instance;
    }

    @Override
    public int add(int studentId, int courseId) {
        String line = "studentId:" + studentId + "," +
                "courseId:" + courseId;
        return this.appendToFile("student_course\\Student_Course.txt", line);
    }

    @Override
    public ArrayList<Integer> retrieve(@NotNull String key, int id) {
        ArrayList<String> lines = this.readlines("student_course\\Student_Course.txt");
        ArrayList<Integer> targetIds = new ArrayList<>();
        String targetKey;

        switch (key) {
            case "studentId":
                targetKey = "courseId";
                break;
            case "courseId":
                targetKey = "studentId";
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
