import javax.swing.*;
import java.util.ArrayList;



public class Student {

    public enum Course_Status{
        NA ,W , Pass , Fail
    }

    String name;
    String Major;
    int ID; //Unique ID
    static int number_of_students;
    int number_of_this_student_courses;
    ArrayList<Course> Courses_List= new ArrayList <Course>();
    ArrayList<Course_Status> Courses_Status_List= new ArrayList <Course_Status>();
    ArrayList<Double> Courses_Grades_List= new ArrayList <Double>();


    public int Find_Course_index_for_Student (Student student , int course_ID){
        for (int i = 0; i < number_of_this_student_courses; i++){
            if (student.Courses_List.get(i).ID == course_ID) {

                return i;
            }
        }
        return -1;
    }

    public int set_Grade (int Course_ID , double Grade){
        int course_index = Find_Course_index_for_Student(this, Course_ID);
        if (course_index == -1){
            return Edu.No_Such_Course;
        } else if (Grade < 0 | Grade > 20){
            return Edu.Invalid_Grade;
        } else if (this.Courses_Status_List.get(course_index) == Course_Status.W)
        {
            return Edu.Course_is_W;
        } else if (Grade >= (double) 10) {
            this.Courses_Grades_List.set(course_index, Grade);
            this.Courses_Status_List.set(course_index, Course_Status.Pass);
            return Edu.Success;
        } else {
            this.Courses_Grades_List.set(course_index, Grade);
            this.Courses_Status_List.set(course_index, Course_Status.Fail);
            return Edu.Success;
        }
    }


    public int remove_Course (int Course_ID) {

        Course new_Course = Course.Find_Course_by_ID(Course_ID);


        if (new_Course == null) return Edu.No_Such_Course;

        int index = Find_Course_index_for_Student(this , new_Course.ID);
        if (index == -1){
            return Edu.No_Such_Course;
        } else if (Courses_Status_List.get(index) == Course_Status.Pass |
                Courses_Status_List.get(index) == Course_Status.Fail |
                Courses_Status_List.get(index) == Course_Status.W ){

            return Edu.Course_Finalized;

        }else{
            Courses_List.remove(index);
            Courses_Status_List.remove(index);
            Courses_Grades_List.remove(index);
            this.number_of_this_student_courses--;
            return Edu.Success;
        }
    }

    public int Withdraw_Course (int ID) {

        Course new_Course = Course.Find_Course_by_ID(ID);


        if (new_Course == null) return Edu.No_Such_Course;

        int index = Find_Course_index_for_Student(this , new_Course.ID);
        if (index == -1){
            return Edu.No_Such_Course;
        } else if (Courses_Status_List.get(index) == Course_Status.Pass |
                Courses_Status_List.get(index) == Course_Status.Fail |
                Courses_Status_List.get(index) == Course_Status.W ){

            return Edu.Course_Finalized;

        }else{
            Courses_Status_List.set(index , Course_Status.W);
            return Edu.Success;
        }
    }

    public int add_Course (int ID) {

        Course new_Course = Course.Find_Course_by_ID(ID);


        if (new_Course == null) return Edu.No_Such_Course;

        else if (Find_Course_index_for_Student(this , new_Course.ID) != -1){
            return Edu.Duplicate_Course;
        } else {
            Courses_List.add(new_Course);
            Courses_Status_List.add(Course_Status.NA);
            Courses_Grades_List.add((double) -1);
            this.number_of_this_student_courses++;
            return Edu.Success;
        }
    }

    public Student (int ID , String name , String Major){

        this.ID = ID;
        this.name = name;
        this.Major = Major;
        number_of_students++;
    }

    public double Find_Average (){
        double Sum = 0;
        int number_of_grades = 0;
        for (int i = 0; i<this.number_of_this_student_courses;i++){
            if (this.Courses_Status_List.get(i) == Course_Status.Pass | this.Courses_Status_List.get(i) == Course_Status.Fail)
            {
                number_of_grades +=  this.Courses_List.get(i).Credits;
                Sum += this.Courses_Grades_List.get(i) * this.Courses_List.get(i).Credits;

            }

        }

        if (number_of_grades == 0)
            return (double) -1;
        else
            return (double)(Sum / number_of_grades);
    }

    public static Student Find_Student_By_ID (int ID){
        for (int i= 0 ; i < Student.number_of_students ; i++){
            if (Edu.Students.get(i).ID == ID){
                return Edu.Students.get(i);
            }
        }
        return null;
    }

}
