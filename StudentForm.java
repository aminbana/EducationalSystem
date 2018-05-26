import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class StudentForm extends JFrame {
    JLabel Student_Number;
    JLabel Choose_Label;
    JButton Calc_Average;
    JButton Add_Course;
    JButton Show_My_Courses;
    JButton Show_Courses;
    JButton Remove_Course;
    JButton Withdraw_Course;
    //JButton Remove_Course;


    public StudentForm() {

        super("Student Page");


        Student_Number = new JLabel("You are signed in as : " + Edu.Signed_ID);
        Choose_Label = new JLabel("Choose What to do");
        setLayout(new FlowLayout()); // set frame layout
        Calc_Average = new JButton("Average");
        Add_Course = new JButton("Add Course");
        Remove_Course = new JButton("Remove My Course");
        Show_Courses = new JButton("Show Courses");
        Show_My_Courses = new JButton("Show My Courses");
        Withdraw_Course = new JButton("Withdraw Course");


        this.add(Student_Number);
        this.add(Choose_Label);
        this.add(Calc_Average);
        this.add(Show_Courses);
        this.add(Show_My_Courses);
        this.add(Add_Course);
        this.add(Remove_Course);
        this.add(Withdraw_Course);

        Remove_Course.setEnabled(Edu.Enable_Remove_Course_For_Students);
        Add_Course.setEnabled(Edu.Enable_Add_Course_For_Students);
        Withdraw_Course.setEnabled(Edu.Enable_W_Course_For_Students);

        this.setLocation(200 , 200);
        this.setSize(190 , 400);

        this.setResizable(false);

        Calc_Average.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Edu.Find_Average(Integer.parseInt(Edu.Signed_ID));
            }
        });

        Show_Courses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edu.Show_All_Courses();
            }
        });


        Show_My_Courses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edu.Show_Student_Courses(Integer.parseInt(Edu.Signed_ID));
            }
        });


        Add_Course.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Course_ID = JOptionPane.showInputDialog("Enter Course ID");
                Edu.Add_Course_For_Student(Integer.parseInt(Edu.Signed_ID) , Integer.parseInt(Course_ID));
            }
        });


        Withdraw_Course.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Course_ID = JOptionPane.showInputDialog("Enter Course ID");
                Edu.Withdraw_Course_For_Student(Integer.parseInt(Edu.Signed_ID) , Integer.parseInt(Course_ID));
            }
        });


        Remove_Course.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Course_ID = JOptionPane.showInputDialog("Enter Course ID");
                Edu.Remove_Course_For_Student(Integer.parseInt(Edu.Signed_ID) , Integer.parseInt(Course_ID));
            }
        });

    }
}
