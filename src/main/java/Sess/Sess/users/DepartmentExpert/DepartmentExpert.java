package Sess.Sess.users.DepartmentExpert;
import Sess.Sess.database.Database;
import Sess.Sess.semester.Semester;
import Sess.Sess.users.Professor.Professor;
import Sess.Sess.users.Student.Student;
import Sess.Sess.users.User;

import java.util.HashMap;

public class DepartmentExpert extends User {
    static private DepartmentExpert instance = null;
    private DepartmentExpert(int id, String firstName, String lastName, String roll) {
        super(id, firstName, lastName, roll);
    }

    static public DepartmentExpert getInstance() {
        if (instance == null) {
            Database db = Database.getInstance();
            HashMap<String, String> properties = db.getDepartmentExpert();

            int id = Integer.parseInt(properties.get("id"));
            String firstName = properties.get("firstName");
            String lastName = properties.get("lastName");
            String roll = properties.get("roll");

            instance = new DepartmentExpert(id, firstName, lastName, roll);
        }
        return instance;
    }

    public int addStudent(Student student) {
        Database db = Database.getInstance();
        return db.addStudent(student);
    }

    public int addProfessor(Professor professor) {
        Database db = Database.getInstance();
        return db.addProfessor(professor);
    }

    public int addSemester(Semester semester) {
        Database db = Database.getInstance();
        return db.addSemester(semester);
    }
}
