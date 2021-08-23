import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LogInGUI implements ActionListener {

    private JFrame letStart;
    private JFrame logIn;


    /*
       It implement first JFrame when we run the program
       It is used to display the welcome message
       It contains the two buttons for further process
       "let's start" and "Exit"
    */

    public void startingPage()
    {
        letStart = new JFrame("ATM");
        letStart.setLayout(new GridLayout(3,1,0,50));

       JPanel panel1 = new JPanel();
        panel1.setBackground(Color.GREEN.darker());
        panel1.setPreferredSize(new Dimension(0,250));

        JLabel otp = new JLabel("otpbank");
        otp.setFont(new Font("New Roman",Font.BOLD+Font.ITALIC,130));
        otp.setForeground(Color.WHITE);
        ImageIcon image = new ImageIcon(new ImageIcon("otpb.png").getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        otp.setIcon(image);
        panel1.add(otp,BorderLayout.CENTER);
        letStart.add(panel1,BorderLayout.NORTH);

        JPanel panel2 = new JPanel();
        JLabel jLable2 = new JLabel();
        jLable2.setText("Welcome to ATM!");
        jLable2.setFont(new Font("New Roman",Font.BOLD,42));
        panel2.add(jLable2);
        letStart.add(panel2);

        JPanel panel3 = new JPanel();
        JButton start  = new JButton("Let's start");
         start.setPreferredSize(new Dimension(130,50));
         start.setFont(new Font("New Roman",Font.BOLD,20));
         start.setBackground(Color.GREEN.darker());
        JButton exit = new JButton("Exit");
         exit.setFont(new Font("New Roman",Font.BOLD,20));
         exit.setPreferredSize(new Dimension(90,50));
         exit.setBackground(Color.RED);

         start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LogInGUI().enterPin();
                letStart.dispose();
            }
        });

         exit.addActionListener(this);
         panel3.add(start);
         panel3.add(Box.createRigidArea(new Dimension(150,0)));
         panel3.add(exit);
        letStart.add(panel3);
        letStart.setExtendedState(JFrame.MAXIMIZED_BOTH);
        letStart.setVisible(true);
        letStart.setDefaultCloseOperation(1);


    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        letStart.dispose();

    }

    /*
      It is used to implement the JFrame for the log in
      It is in action listener of the start button in the startingPage method
      If user enter correct the pin a next window will pop up
      If user enter wrong pin the a warning message will pop up Which displays: " Invalid pin"
     */
    public void enterPin()
    {
     logIn = new JFrame("Log in");
     logIn.setLayout(new GridLayout(3,0,0,30));
     logIn.getContentPane().setBackground(Color.GREEN.darker());
     JPanel upperPanel = new JPanel();
     upperPanel.setPreferredSize(new Dimension(100,170));
     upperPanel.setBackground(Color.GREEN.darker());
     upperPanel.setBorder(new LineBorder(Color.WHITE));

     JLabel otp = new JLabel("otpbank");
     otp.setFont(new Font("New Roman",Font.BOLD+Font.ITALIC,130));
     otp.setForeground(Color.WHITE);
     ImageIcon image = new ImageIcon(new ImageIcon("otpb.png").getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
     otp.setIcon(image);
     upperPanel.add(otp,BorderLayout.CENTER);

     JPanel middlePanel = new JPanel();
     middlePanel.setBackground(Color.GREEN.darker());
     JLabel pin = new JLabel("Enter your pin:");
     pin.setFont(new Font("New Roman",Font.BOLD,28));
     JPasswordField passwordField = new JPasswordField();
     passwordField.setPreferredSize(new Dimension(150,50));
     passwordField.setFont(new Font("",Font.BOLD,38));
     middlePanel.add(pin);
     middlePanel.add(passwordField);

     JPanel lowerPanel = new JPanel();
     lowerPanel.setPreferredSize(new Dimension(0,650));
     lowerPanel.setBackground(Color.GREEN.darker());
     lowerPanel.setLayout(new GridLayout(4,4,0,0));

      for(int i=1;i<=12;i++)
      {
          JButton button = new JButton();
          if(i==10)
          {
              button.setText("Cancel");
              button.setBackground(Color.YELLOW);
              button.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      String passwordText = passwordField.getText();
                      StringBuffer str = new StringBuffer(passwordText);
                    try {
                        passwordField.setText(String.valueOf(str.deleteCharAt(str.length() - 1)));
                    }catch (StringIndexOutOfBoundsException er)
                    {
                        er.printStackTrace();
                    }
                  }
              });
          }
          else if(i==11)
          {
              button.setText("0");
              button.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      String prev = passwordField.getText();
                      passwordField.setText(prev+button.getText());
                  }
              });
          }
          else if(i==12)
          {
              button.setText("Enter");
              button.setBackground(Color.RED.darker());
              button.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      if (new ATM().validatePin(passwordField.getText())==true)
                      {
                          new AtmOperationsGUI().listOfOperation();
                          logIn.dispose();

                      } else
                      {
                          JOptionPane optionPane = new JOptionPane("Please enter valid pin..", JOptionPane.WARNING_MESSAGE);
                          JDialog dialog = optionPane.createDialog("Invalid pin!");
                          dialog.setVisible(true);
                          passwordField.setText(null);
                      }

                  }
              });
          }
          else
          {
              button.setText(String.valueOf(i));
              button.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      String prev = passwordField.getText();
                      passwordField.setText(prev+button.getText());

                  }
              });

          }
          button.setFont(new Font("",Font.BOLD,18));
          lowerPanel.add(button);
      }

     logIn.add(upperPanel,BorderLayout.NORTH);
     logIn.add(middlePanel,BorderLayout.CENTER);
     logIn.add(lowerPanel,BorderLayout.SOUTH);
     logIn.setExtendedState(JFrame.MAXIMIZED_BOTH);
     logIn.setVisible(true);
     logIn.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }



    /*
         It is used to implement the JFrame for the change pin
         It is called in action listener of the buttons change pin used
         in the method listOfOperation in the class AtmOperationsGUI
        */
    public void changePin()
    {
        JFrame changePin = new JFrame("Change pin");
        changePin.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN.darker());
        panel.setPreferredSize(new Dimension(0,430));

        JLabel previousPin = new JLabel("Enter previous pin:  ");
        previousPin.setFont(new Font("New Roman",Font.BOLD,22));
        panel.add(Box.createVerticalStrut(350));
        panel.add(previousPin);
        JPasswordField prevPassword = new JPasswordField();
        prevPassword.setPreferredSize(new Dimension(150,50));
        prevPassword.setFont(new Font("New Roman",Font.BOLD,38));
        panel.add(prevPassword);
        panel.add(Box.createRigidArea(new Dimension(150,0)));

        JLabel newPin = new JLabel("Enter new pin:  ");
        newPin.setFont(new Font("New Roman",Font.BOLD,22));
        panel.add(newPin);
        JPasswordField newPassword = new JPasswordField();
        newPassword.setFont(new Font("New Roman",Font.BOLD,38));
        newPassword.setPreferredSize(new Dimension(150,50));
        panel.add(newPassword,BorderLayout.NORTH);

        JPanel lowerPanel = new JPanel();
        lowerPanel.setBackground(Color.GREEN.darker());
        lowerPanel.setPreferredSize(new Dimension(0,370));
        JButton confirm = new JButton("Confirm");
        confirm.setFont(new Font("New Roman",Font.BOLD,20));
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(new ATM().changePin(prevPassword.getText(),newPassword.getText()))
                {
                    JOptionPane optionPane = new JOptionPane("Pin has successfully changed ",JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Change Pin");
                    dialog.setVisible(true);
                    changePin.dispose();
                }
                else if(newPassword.getText().length()!=4)
                {
                    JOptionPane optionPane = new JOptionPane("Pin should contain 4 character..", JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Invalid pin!");
                    dialog.setVisible(true);
                    newPassword.setText(null);
                    newPassword.setText(null);


                }
                else
                {
                    JOptionPane optionPane = new JOptionPane("Previous pin is incorrect..", JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Invalid pin!");
                    dialog.setVisible(true);
                    newPassword.setText(null);
                    newPassword.setText(null);
                }
            }
        });
        lowerPanel.add(confirm);

        changePin.add(panel);
        changePin.add(lowerPanel,BorderLayout.SOUTH);

       changePin.setVisible(true);
       changePin.setDefaultCloseOperation(1);
    }
}
