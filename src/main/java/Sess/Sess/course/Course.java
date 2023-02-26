package Sess.Sess.course;

import Sess.Sess.database.Database;
import Sess.Sess.database.bridge.course_exam.Course_Exam;
import Sess.Sess.exam.Exam;

import java.time.LocalDate;
import java.util.ArrayList;

public class Course {
    private int id;
    private String title;
    private String syllabus;
    private String reference;
    private LocalDate midtermDate;
    private LocalDate finalDate;

    private ArrayList<Exam> exams;

    public Course(String title, String syllabus, String reference, LocalDate midtermDate, LocalDate finalDate) {
        this.title = title;
        this.syllabus = syllabus;
        this.reference = reference;
        this.midtermDate = midtermDate;
        this.finalDate = finalDate;
    }

    public Course(int id, String title, String syllabus, String reference, LocalDate midtermDate, LocalDate finalDate) {
        this(title, syllabus, reference, midtermDate, finalDate);
        this.id = id;
    }

    public ArrayList<Exam> getExams() {
        Course_Exam ce = Course_Exam.getInstance();
        ArrayList<Integer> examIds = ce.retrieve("courseId", this.getId());
        ArrayList<Exam> exams = new ArrayList<>();
        Database db = Database.getInstance();

        for (int examId : examIds) {
            exams.add(db.getExamById(examId));
        }

        return exams;
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

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getMidtermDate() {
        return midtermDate;
    }

    public void setMidtermDate(LocalDate midtermDate) {
        this.midtermDate = midtermDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    @Override
    public String toString() {
        return "id:" + this.getId() + "," +
                "title:" + this.getTitle() + "," +
                "syllabus:" + this.getSyllabus() + "," +
                "reference:" + this.getReference() + "," +
                "midterm:" + this.getMidtermDate() + "," +
                "final:" + this.getFinalDate();
    }
}
