import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;


public class Edu {

    public static final int No_Such_Course = 0;
    public static final int No_Such_Student = 1;
    public static final int Duplicate_Course = 1;
    public static final int Success = 4;
    public static final int Course_Finalized = 3;
    public static final int Course_is_W = 5;
    public static final int Invalid_Grade = 6;


    static volatile boolean signed_in = false;
    public static String Signed_ID;

    public enum privilege {Admin , Student};


    static privilege user_type = privilege.Admin;

    static ArrayList <Course> Courses = new ArrayList<Course>();
    static ArrayList <Student> Students = new ArrayList<Student>();
    static ArrayList <String> Admins = new ArrayList<String>();


    public static boolean Enable_Remove_Course_For_Students = false;
    public static boolean Enable_Add_Course_For_Students = false;
    public static boolean Enable_W_Course_For_Students = false;


    static SignInForm login_frame;
    static AdminForm admin_frame;
    static StudentForm student_frame;


    public static void Initial_The_GUI(){
        login_frame = new SignInForm();
        login_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    public static void main(String[] args) {
       Initial_The_GUI();
       Show_Sign_In_Form ();
       Wait_Until_Signed_In();

        //test
       // Signed_ID = "93101135";


        Create_Course(25076 , "AP" , "Dr.SH" , "2016-1-5" , 2);
        Create_Course(25077 , "FPGA" , "Dr.HA","2016-1-8" , 3);
        Create_Course(25078 , "Micro" , "Dr.JA" , "2016-1-9" , 4);

        Edu.Create_Student(93101135 , "Amin" , "EE");


        Edu.Add_Course_For_Student(93101135 , 25077);
        Edu.Set_Grade_For_Student(93101135 , 25077 , 18.5);

        Edu.Add_Course_For_Student(93101135 , 25076);
        Edu.Set_Grade_For_Student(93101135 , 25076 , 8);

        //user_type = privilege.Admin;

        if (user_type == privilege.Admin) Show_Admin_Form();
        else Show_Student_Form();




    }

    public static void Show_Admin_Form (){
        admin_frame = new AdminForm();
        admin_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        admin_frame.setVisible(true);
    }

    public static void Show_Student_Form (){
        student_frame = new StudentForm();
        student_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        student_frame.setVisible(true);

    }

    public static void Create_Course (int Course_ID , String Name , String Teacher , String Exam_Date , int Credits){
        Course new_course = Course.Find_Course_by_ID(Course_ID);
        if (new_course == null){
            Edu.Courses.add(new Course(Course_ID , Name , Teacher , Exam_Date , Credits));
            ShowMessage("Course Added Successfully");
        } else {
            ShowMessage("Course Exists");
        }

    }


    public static void Edit_Course (int Course_ID , String Name , String Teacher , String Exam_Date , int Credits){
        Course edit_course = Course.Find_Course_by_ID(Course_ID);
        if (edit_course == null){
            ShowMessage("No Such Course");
        } else {
            edit_course.Teacher = Teacher;
            edit_course.Name = Name;
            edit_course.Credits = Credits;
            try {
                edit_course.Exam_Date = Course.data_format.parse(Exam_Date);
            } catch (ParseException e) {
                ShowMessage("Exam Date is wrong");
            }
            ShowMessage("Changed Successfully");
        }

    }

    public static void Delete_Course (int Course_ID){
        Course course = Course.Find_Course_by_ID(Course_ID);
        if (course == null){
            ShowMessage("No Such Course");
        } else {

            if (course.number_of_students_in_course != 0){
                ShowMessage("Course has students");
            } else {
                int index = Find_Course_Index(Course_ID);
                Courses.remove(index);
                ShowMessage("Course Deleted Successfully");
            }
        }

    }



    public static int Find_Course_Index (int Course_ID){
        for (int i=0;i < Course.number_of_courses ; i++){
            if (Courses.get(i).ID == Course_ID){
                return i;
            }
        }
        return -1;
    }

    public static int Find_Student_Index (int Student_ID){
        for (int i=0;i < Student.number_of_students ; i++){
            if (Courses.get(i).ID == Student_ID){
                return i;
            }
        }
        return -1;
    }

    public static void Create_Student (int Student_ID, String Name , String Major){
        Student new_student = Student.Find_Student_By_ID(Student_ID);
        if (new_student == null ){
            ShowMessage("Student Added Successfully");
            Edu.Students.add(new Student(Student_ID , Name , Major));
        } else {
            ShowMessage("Duplicate Student not permitted");
        }

    }

    public static void Show_Sign_In_Form (){

        login_frame.setVisible(true);

    }

    public static void Wait_Until_Signed_In() {
        while (!signed_in){
            ;
        }


    }

    public static void Check_Password(privilege user_type, String user_name , String password){
        String correct_name = "93101135";

        String correct_password = "123456";

        String correct_admin_name = "Amin";
        String correct_admin_pass = "123456";


        if (((user_name.equals(correct_name) & password.equals(correct_password))& user_type == privilege.Student) |
                ((user_name.equals(correct_admin_name) & password.equals(correct_admin_pass))& user_type == privilege.Admin)){
            ShowMessage("Sign In Confirmed");
            Edu.user_type = user_type;
            Signed_ID = user_name;
            Edu.signed_in = true;
            login_frame.setVisible(false);



        } else {
            JOptionPane.showMessageDialog(null,"Error" , "Wrong Info; Please try again.", JOptionPane.WARNING_MESSAGE);
        }

    }

    public static void Find_Average (int Student_ID){
        Student student = Student.Find_Student_By_ID(Student_ID);
        if (student == null) {

            ShowMessage( "No Such Student in List");
        } else {
            double Average = (Student.Find_Student_By_ID(Student_ID).Find_Average());
            if (Average == (double) -1)
                ShowMessage(" No grades stored for this student");
            else
                ShowMessage("The Average is " + Average);
        }

    }

    public static void Add_Course_For_Student (int Student_ID , int Course_ID){
        Student student = Student.Find_Student_By_ID(Student_ID);
        if (student == null) {

            ShowMessage("No Such Student in List");
        } else {
            int result = student.add_Course(Course_ID);
            if (result == No_Such_Course){
                ShowMessage("No such Course!");
            } else if (result == Duplicate_Course) {
                ShowMessage("Duplicate Course");
            } else if (result == Success){
                ShowMessage(Course.Find_Course_by_ID(Course_ID).Name + " Course Added Successfully for Student " + student.ID);
                Course.Find_Course_by_ID(Course_ID).Add_Student_To_Course(student);
            }




        }
    }

    public static void Withdraw_Course_For_Student (int Student_ID , int Course_ID){
        Student student = Student.Find_Student_By_ID(Student_ID);
        if (student == null) {
            ShowMessage("No Such Student in List");
        } else {
            int result = student.Withdraw_Course(Course_ID);
            if (result == No_Such_Course){
                ShowMessage("No such Course!");
            } else if (result == Course_Finalized) {
                ShowMessage("Course has been finalized , you cannot withdraw it");
            } else if (result == Success){
                ShowMessage(Course.Find_Course_by_ID(Course_ID).Name + " Course W Successful");
            }

        }
    }

    public static void Remove_Course_For_Student (int Student_ID , int Course_ID){
        Student student = Student.Find_Student_By_ID(Student_ID);
        if (student == null) {
            ShowMessage("No Such Student in List");
        } else {
            int result = student.remove_Course(Course_ID);
            if (result == No_Such_Course){
                ShowMessage("No such Course!");
            } else if (result == Success){
                ShowMessage(Course.Find_Course_by_ID(Course_ID).Name + " Course removed Successfully");
            } else if (result == Course_Finalized){
                ShowMessage("The Course Have been finalized , you can't remove it");
            }

        }
    }

    public static void Show_All_Courses () {
        String Courses_List = "";
        if (Course.number_of_courses == 0) {
            ShowMessage("No Course");
        }
        for (int i = 0; i < Course.number_of_courses ; i++){
            Courses_List += "\n Course Name : " + Edu.Courses.get(i).Name + "  **  ID: " + Edu.Courses.get(i).ID + "  **  Teacher : " +
                    Edu.Courses.get(i).Teacher + "  **  Exam Date = " + Course.data_format.format(Edu.Courses.get(i).Exam_Date) +
                    "  **  Credits:" + Edu.Courses.get(i).Credits;

        }
        ShowMessage( Courses_List);
    }

    public static void Show_All_Students () {
        String Student_List = "";
        if (Student.number_of_students == 0) {
            ShowMessage("No Student");
        }
        for (int i = 0; i < Student.number_of_students ; i++){
            Student_List += "\n Student Name : " + Edu.Students.get(i).name + "  **  ID: " + Edu.Students.get(i).ID + "  **  Average : " +
                    Edu.Students.get(i).Find_Average() + "  **  Major: " + Edu.Students.get(i).Major;

        }
        ShowMessage( Student_List);
    }

    public static void Show_Student_Courses (int Student_ID){
        Student student = Student.Find_Student_By_ID(Student_ID);
        if (student == null){
            ShowMessage("No Such Student");
        } else {
            String Student_Courses_List = "";
            String Status_Grade = "";
            if (student.number_of_this_student_courses == 0){
                ShowMessage("No Courses For this Student");
            } else {
                for (int i = 0; i < student.number_of_this_student_courses; i++) {

                    if (student.Courses_Status_List.get(i) == Student.Course_Status.NA) {
                        Status_Grade = "  **  Status: Not Availabe " + "  **  Grade: No Grades";
                    }
                    if (student.Courses_Status_List.get(i) == Student.Course_Status.Pass) {
                        Status_Grade = "  **  Status: Passed "
                                + "  **  Grade: " + student.Courses_Grades_List.get(i);
                    }
                    if (student.Courses_Status_List.get(i) == Student.Course_Status.Fail) {
                        Status_Grade = "  **  Status: Failed "
                                + "  **  Grade: " + student.Courses_Grades_List.get(i);
                    }
                    if (student.Courses_Status_List.get(i) == Student.Course_Status.W) {
                        Status_Grade = "  **  Status: W "
                                + "  **  Grade: " + "No Grade";
                    }

                    Student_Courses_List += "\n Course Name : " + student.Courses_List.get(i).Name + "  **  ID: " + student.Courses_List.get(i).ID + "  **  Teacher : " +
                            student.Courses_List.get(i).Teacher + Status_Grade + "  **  Exam Date = "
                            + Course.data_format.format( student.Courses_List.get(i).Exam_Date) +
                            "  **  Credits = " + student.Courses_List.get(i).Credits;

                }
                ShowMessage(Student_Courses_List);
            }
            }


    }

    public static void Show_Course_Students (int Course_ID){

        Course course = Course.Find_Course_by_ID(Course_ID);
        if (course == null){
            ShowMessage("No Such Course");
        } else {
            String Course_Students_List = "";
            String Status_Grade = "";

            if (course.number_of_students_in_course == 0){
                ShowMessage("No Students in this Course");
            } else {
                for (int i = 0; i < course.number_of_students_in_course; i++) {
                    int index = course.Students_List.get(i).Find_Course_index_for_Student(course.Students_List.get(i), Course_ID);
                    if (course.Students_List.get(i).Courses_Status_List.get(index) == Student.Course_Status.NA) {

                        Status_Grade = "  **  Status: Not Available " + "  **  Grade: No Grades";
                    }
                    if (course.Students_List.get(i).Courses_Status_List.get(index) == Student.Course_Status.Pass) {
                        Status_Grade = "  **  Status: Passed "
                                + "  **  Grade: " + course.Students_List.get(i).Courses_Grades_List.get(index);
                    }
                    if (course.Students_List.get(i).Courses_Status_List.get(index) == Student.Course_Status.Fail) {
                        Status_Grade = "  **  Status: Failed "
                                + "  **  Grade: " + course.Students_List.get(i).Courses_Grades_List.get(index);
                    }
                    if (course.Students_List.get(i).Courses_Status_List.get(index) == Student.Course_Status.W) {
                        Status_Grade = "  **  Status: W "
                                + "  **  Grade: " + "No Grade";
                    }

                    Course_Students_List += "\n Student name: " + course.Students_List.get(i).name + "  **  ID: " + course.Students_List.get(i).ID
                            + Status_Grade +" **  Major: " + course.Students_List.get(i).Major;

                }
                ShowMessage(Course_Students_List);
            }
            }


    }

    public static void ShowMessage (String Message){
        JOptionPane.showMessageDialog(null , Message);

    }

    public static void Set_Grade_For_Student (int Student_ID , int Course_ID , double Grade){
        Student student = Student.Find_Student_By_ID(Student_ID);
        if (student == null){
            ShowMessage("No Such Student");
        } else {
            int result = student.set_Grade(Course_ID , Grade);
            if (result == No_Such_Course){
                ShowMessage("No Such Course for this Student");
            } else if (result == Course_is_W){
                ShowMessage("Course is already W");
            } else if (result == Success){
                ShowMessage("Grade was Successfully added");
            }
        }
    }

    public static void Show_Student_Details (int Student_ID){
        Student student = Student.Find_Student_By_ID(Student_ID);
        if (student == null){
            ShowMessage("No Such Student");
        } else {
            ShowMessage("Student Number = " + student.ID + "  **  Name = " + student.name + "  **  Average = " + student.Find_Average());
        }
    }

    public static void Show_Course_Details (int Course_ID){
        Course course = Course.Find_Course_by_ID(Course_ID);
        if (course == null){
            ShowMessage("No Such Course");
        } else {
            ShowMessage("ID : " + course.ID + "  **  Name = " + course.Name + "  **  Teacher : " + course.Teacher + "  **  Exam Date = "
                    + Course.data_format.format(course.Exam_Date) + "  **  Credits : " + course.Credits);
        }
    }

    public static void Enable_Add_Course_For_Students (){

        if (Enable_Add_Course_For_Students == true){
            ShowMessage("Add Course has been disabled");
            Enable_Add_Course_For_Students = false;
        } else {
            Enable_Add_Course_For_Students = true;
            ShowMessage("Add Course has been enabled");
        }
    }

    public static void Enable_W_Course_For_Students (){

        if (Enable_W_Course_For_Students == true){
            ShowMessage("W Course has been disabled");
            Enable_W_Course_For_Students = false;
        } else {
            Enable_W_Course_For_Students = true;
            ShowMessage("W Course has been enabled");
        }
    }

    public static void Enable_Remove_Course_For_Students (){

        if (Enable_Remove_Course_For_Students == true){
            ShowMessage("Remove Course has been disabled");
            Enable_Remove_Course_For_Students = false;
        } else {
            Enable_Remove_Course_For_Students = true;
            ShowMessage("Remove Course has been enabled");
        }
    }




}
