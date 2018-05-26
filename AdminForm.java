import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import javax.swing.*;


public class AdminForm extends JFrame {
    JLabel Personal_ID;
    JLabel Choose_Label;
    JButton Calc_Student_Average;
    JButton Create_Course;
    JButton Edit_Course;
    JButton Delete_Course;

    JButton Show_Student_Courses;

    JButton Show_Course_Students;
    JButton Show_Students;
    JButton Show_Courses;
    JButton Create_Student;
    JButton Remove_Course_For_Student;
    JButton Add_Course_For_Student;

    JButton Show_Student_Details;
    JButton Add_for_Students_Enable;
    JButton W_for_Students_Enable;
    JButton Remove_for_Students_Enable;
    JButton Show_Course_Details;


    public AdminForm() {

        super("Admin Page");


        Personal_ID = new JLabel("You are signed in as : Admin");
        Choose_Label = new JLabel("Choose What to do");

        setLayout(new FlowLayout()); // set frame layout


        Calc_Student_Average = new JButton("Average For a Student");
        Create_Course = new JButton("Create Course");
        Edit_Course = new JButton("Edit Course");
        Delete_Course = new JButton("Delete Course");
        Show_Student_Courses = new JButton("Show Courses For Student");
        Show_Course_Students = new JButton("Show Students For Course");
        Show_Courses = new JButton("Show All Courses");
        Show_Students = new JButton("Show All Students");
        Add_Course_For_Student = new JButton("Add Course For Student");
        Remove_Course_For_Student = new JButton("Remove Course For Student");

        Show_Student_Details = new JButton("Show Student Details");
        Create_Student = new JButton("Create Students");
        Show_Course_Details = new JButton("Show Course Details");
        Add_for_Students_Enable = new JButton("Enable/Disable Add Course");
        W_for_Students_Enable = new JButton("Enable/Disable W Course");
        Remove_for_Students_Enable = new JButton("Enable/Disable Remove Course");



        this.add(Personal_ID);
        this.add(Choose_Label);

        this.add(Calc_Student_Average);
        this.add(Create_Course);
        this.add(Edit_Course);
        this.add(Delete_Course);
        this.add(Show_Student_Courses);
        this.add(Show_Course_Students);
        this.add(Show_Courses);
        this.add(Show_Students);
        this.add(Add_Course_For_Student);
        this.add(Remove_Course_For_Student);
        this.add(Create_Student);
        this.add(Show_Student_Details);
        this.add(Show_Course_Details);
        this.add(Add_for_Students_Enable);
        this.add(W_for_Students_Enable);
        this.add(Remove_for_Students_Enable);



        this.setLocation(200 , 200);
        this.setSize(300 , 450);
        this.setResizable(false);



        Calc_Student_Average.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Student_ID;
                Student_ID = JOptionPane.showInputDialog(null , "Enter Student Number");
                Edu.Find_Average(Integer.parseInt(Student_ID));
            }
        });


        Create_Course.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Course_ID;
                String Course_Name;
                String Course_Teacher;
                String Course_Exam_Date;
                String Credits;

                Course_ID = JOptionPane.showInputDialog(null , "Enter Course ID");
                Course_Name = JOptionPane.showInputDialog(null , "Enter Course Name");
                Course_Teacher = JOptionPane.showInputDialog(null , "Enter Course Teacher");
                Credits = JOptionPane.showInputDialog(null , "Enter Course Credits");
                Course_Exam_Date = JOptionPane.showInputDialog(null , "Enter Course Exam Date as 2016-1-5");
                Edu.Create_Course(Integer.parseInt(Course_ID) , Course_Name , Course_Teacher , Course_Exam_Date , Integer.parseInt(Credits));



            }
        });


        Delete_Course.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Course_ID;
                Course_ID = JOptionPane.showInputDialog(null , "Enter Course ID");

                Edu.Delete_Course(Integer.parseInt(Course_ID));

            }
        });


        Show_Student_Courses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Student_ID;
                Student_ID = JOptionPane.showInputDialog(null , "Enter Student ID");

                Edu.Show_Student_Courses(Integer.parseInt(Student_ID));

            }
        });


        Show_Course_Students.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Course_ID;
                Course_ID = JOptionPane.showInputDialog(null , "Enter Course ID");

                Edu.Show_Course_Students(Integer.parseInt(Course_ID));

            }
        });


        Show_Courses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Edu.Show_All_Courses();

            }
        });


        Show_Students.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Edu.Show_All_Students();

            }
        });


        Add_Course_For_Student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Course_ID;
                String Student_ID;

                Student_ID = JOptionPane.showInputDialog(null , "Enter Student ID");
                Course_ID = JOptionPane.showInputDialog(null , "Enter Course ID");


                Edu.Add_Course_For_Student(Integer.parseInt(Student_ID) , Integer.parseInt(Course_ID));


            }
        });


        Remove_Course_For_Student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Course_ID;
                String Student_ID;

                Student_ID = JOptionPane.showInputDialog(null , "Enter Student ID");
                Course_ID = JOptionPane.showInputDialog(null , "Enter Course ID");

                Edu.Remove_Course_For_Student(Integer.parseInt(Student_ID) , Integer.parseInt(Course_ID));


            }
        });


        Show_Student_Details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Student_ID;

                Student_ID = JOptionPane.showInputDialog(null , "Enter Student ID");

                Edu.Show_Student_Details(Integer.parseInt(Student_ID));


            }
        });


        Show_Course_Details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Course_ID;

                Course_ID = JOptionPane.showInputDialog(null , "Enter Course ID");

                Edu.Show_Course_Details(Integer.parseInt(Course_ID));


            }
        });


        W_for_Students_Enable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            Edu.Enable_W_Course_For_Students();

            }
        });


        Add_for_Students_Enable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Edu.Enable_Add_Course_For_Students();


            }
        });


        Remove_for_Students_Enable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Edu.Enable_Remove_Course_For_Students();

            }
        });


       Edit_Course.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Course_ID;
                String Course_Name;
                String Course_Teacher;
                String Course_Exam_Date;
                String Course_Credits;
                Course_ID = JOptionPane.showInputDialog(null , "Enter Course ID to Edit");
                Course course = Course.Find_Course_by_ID(Integer.parseInt(Course_ID));
                if (course == null) {
                    Edu.ShowMessage("No Such Course");
                } else {
                    Course_Name = JOptionPane.showInputDialog(null, "Enter Course Name" , course.Name );
                    Course_Credits = JOptionPane.showInputDialog(null, "Enter Course Credits" , course.Credits );
                    Course_Teacher = JOptionPane.showInputDialog(null, "Enter Course Teacher" , course.Teacher);
                    Course_Exam_Date = JOptionPane.showInputDialog(null, "Enter Course Exam Date as yyyy-mm-dd" , Course.data_format.format(course.Exam_Date));
                    Edu.Edit_Course (Integer.parseInt(Course_ID) , Course_Name , Course_Teacher , Course_Exam_Date , Integer.parseInt(Course_Credits));
                }




            }
        });


        Create_Student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Student_ID;
                String Student_Name;
                String Student_Major;

                Student_ID = JOptionPane.showInputDialog(null , "Enter Student ID:");
                Student_Name = JOptionPane.showInputDialog(null , "Enter Student Name:");
                Student_Major = JOptionPane.showInputDialog(null , "Enter Student Major:");





                Edu.Create_Student(Integer.parseInt(Student_ID) , Student_Name , Student_Major);





            }
        });


    }
}

