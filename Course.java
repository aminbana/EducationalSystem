import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;


public class Course {

    public int number_of_students_in_course;
    public ArrayList<Student> Students_List = new ArrayList<Student>();

    public String Name;
    public String Teacher;
    int ID;
    int Credits;

    static SimpleDateFormat data_format = new SimpleDateFormat("yyyy-MM-dd" );
    Date Exam_Date;







    public static int number_of_courses = 0;

    public Course (int ID , String Name , String Teacher , String exam_date , int Credits){
        number_of_courses++;
        this.Name= Name;
        this.ID = ID;
        this.Teacher = Teacher;
        this.Credits = Credits;



        try {
            this.Exam_Date = data_format.parse(exam_date);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null , "Incorrect Date");
        }

    }


    public static Course Find_Course_by_ID (int ID){
        for (int i=0;i<Course.number_of_courses;i++){
            if (Edu.Courses.get(i).ID == ID)
                return Edu.Courses.get(i);
        }
        return null;
    }


    public void Add_Student_To_Course (Student new_student){
        Students_List.add(new_student);
        number_of_students_in_course++;
    }




    //Date Exam_Date = new Date();

    //Course Name”, “Course ID”, “Exam date”, “Class time and days” and “Credits

}
