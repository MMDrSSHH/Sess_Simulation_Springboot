package Sess.Sess.semester;

import java.time.LocalDate;

public class Semester {
    private int id;
    private String title;
    private LocalDate gradesSubmit;
    private LocalDate startDate;
    private LocalDate endDate;

    public Semester(String title, LocalDate gradesSubmit, LocalDate startDate, LocalDate endDate) {
        this.setTitle(title);
        this.setGradesSubmit(gradesSubmit);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }

    public Semester(int id, String title, LocalDate gradesSubmit, LocalDate startDate, LocalDate endDate) {
        this(title, gradesSubmit, startDate, endDate);
        this.setId(id);
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

    public LocalDate getGradesSubmit() {
        return gradesSubmit;
    }

    public void setGradesSubmit(LocalDate gradesSubmit) {
        this.gradesSubmit = gradesSubmit;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "id:" + this.getId() + ","
                + "title:" + this.getTitle() + ","
                + "gradesSubmit:" + this.getGradesSubmit() + ","
                + "startDate:" + this.getStartDate() + ","
                + "endDate:" + this.getEndDate();
    }
}
