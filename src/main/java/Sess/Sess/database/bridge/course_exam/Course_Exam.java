package Sess.Sess.database.bridge.course_exam;

import Sess.Sess.database.bridge.Bridge;
import Sess.Sess.database.bridge.semester_course.Semester_Course;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class Course_Exam extends Bridge {

    static private Course_Exam instance = null;

    private Course_Exam() {}

    static public Course_Exam getInstance() {
        if (instance == null) {
            instance = new Course_Exam();
        }

        return instance;
    }

    @Override
    public int add(int courseId, int examId) {
        String line = "courseId:" + courseId + "," +
                "examId:" + examId;
        return this.appendToFile("course_exam\\Course_Exam.txt", line);
    }

    @Override
    public ArrayList<Integer> retrieve(@NotNull String key, int id) {
        ArrayList<String> lines = this.readlines("course_exam\\Course_Exam.txt");
        ArrayList<Integer> targetIds = new ArrayList<>();
        String targetKey;

        switch (key) {
            case "examId":
                targetKey = "courseId";
                break;
            case "courseId":
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
