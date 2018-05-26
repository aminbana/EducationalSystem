import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Amin on 6/26/2017.
 */
public class SignInForm extends JFrame {
    private final JLabel Sign_in_label;
    private final JTextField Username_Field;
    private final JPasswordField Password_Field;
    private final JButton Sign_In_Button;
    private final JRadioButton Student_Radio;
    private final JRadioButton Admin_Radio;
    public SignInForm(){
        super("Log In");


        setLayout(new FlowLayout()); // set frame layout
        Sign_in_label = new JLabel("Please Enter User name and Password");

        Username_Field = new JTextField("",20);
        Password_Field = new JPasswordField("",20);
        Student_Radio = new JRadioButton("Student",false);
        Admin_Radio = new JRadioButton("Admin",true);
        Sign_In_Button = new JButton("Sign In");

        this.add(Sign_in_label); // add label1 to JFrame
        this.add(Username_Field); // add label1 to JFrame
        this.add(Password_Field); // add label1 to JFrame
        this.add(Sign_In_Button);

        ButtonGroup Privilege;
        Privilege = new ButtonGroup(); // create ButtonGroup
        Privilege.add(Admin_Radio); // add plain to group
        Privilege.add(Student_Radio); // add bold to group


        this.add(Admin_Radio);
        this.add(Student_Radio);
        this.setSize(300,160);

        this.setLocation(200 , 200);
        this.setResizable(false);

        this.getRootPane().setDefaultButton(Sign_In_Button);
        //this.pack();


        Sign_In_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edu.privilege user_type = Edu.privilege.Student;

                if (Student_Radio.isSelected()) {user_type = Edu.privilege.Student;}
                else if (Admin_Radio.isSelected()) {user_type = Edu.privilege.Admin;}

                Edu.Check_Password(user_type , Username_Field.getText() , Password_Field.getText());
            }
        });



    }
}
