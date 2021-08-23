import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AtmOperationsGUI implements ActionListener {

    private JFrame operations;

    /*
      this method is used to create the JFrame
      it displays the list of option user can select using buttons
      It is called when user enter the correct pin
     */
    public void listOfOperation()
    {
        operations = new JFrame("Operations");
        operations.getContentPane().setBackground(Color.GREEN.darker());
        JPanel upperPanel = new JPanel();
        upperPanel.setPreferredSize(new Dimension(0,250));
        upperPanel.setBackground(Color.GREEN.darker());

        JLabel otp = new JLabel("otpbank");
        otp.setFont(new Font("New Roman",Font.BOLD+Font.ITALIC,130));
        otp.setForeground(Color.WHITE);
        ImageIcon image = new ImageIcon(new ImageIcon("otpb.png").getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        otp.setIcon(image);
        upperPanel.add(otp,BorderLayout.CENTER);
        operations.add(upperPanel,BorderLayout.NORTH);

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(300,0));
        leftPanel.setBackground(Color.GREEN.darker());
        JLabel label = new JLabel("What would you like to do?");
        label.setFont(new Font("New Roman",Font.BOLD,22));
        leftPanel.add(label,BorderLayout.LINE_START);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.GREEN.darker());
        centerPanel.setPreferredSize(new Dimension(400,0));
        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));

        JButton checkBalance = new JButton("Check balance");
        checkBalance.setPreferredSize(new Dimension(130,100));
        checkBalance.setFont(new Font("New Roman",Font.BOLD,20));
        checkBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = null;

                    str = "Your available balance is "+new ATM().getBalance();

                JOptionPane optionPane = new JOptionPane(str,JOptionPane.INFORMATION_MESSAGE);
                 optionPane.setFont(new Font(" New Roman",Font.BOLD,20));
                 JDialog dialog = optionPane.createDialog("Balance");
                 dialog.setSize(new Dimension(600,200));
                 dialog.setVisible(true);
            }
        });
        JButton cashWithdraw = new JButton("Cash withdraw");
        cashWithdraw.setFont(new Font("New Roman",Font.BOLD,20));
        cashWithdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AtmOperationsGUI().cashWithdraw();
            }
        });
        JButton changePin = new JButton("Change pin");
        changePin.setFont(new Font("New Roman",Font.BOLD,20));
        changePin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LogInGUI().changePin();
            }
        });
        centerPanel.add(Box.createRigidArea(new Dimension(100,50)));
        centerPanel.add(checkBalance,BorderLayout.WEST);
        centerPanel.add(Box.createRigidArea(new Dimension(0,130)));
        centerPanel.add(cashWithdraw,BorderLayout.WEST);
        centerPanel.add(Box.createRigidArea(new Dimension(0,130)));
        centerPanel.add(changePin,BorderLayout.WEST);

        JPanel rightPanel  = new JPanel();
        rightPanel.setPreferredSize(new Dimension(550,0));
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));

        JButton deposit = new JButton("Deposit money");
        deposit.setPreferredSize(new Dimension(130,100));
        deposit.setFont(new Font("New Roman",Font.BOLD,20));
        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AtmOperationsGUI().depositMoney();
            }
        });
        JButton transfer = new JButton("Money transfer");
        transfer.setFont(new Font("New Roman",Font.BOLD,20));

        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AtmOperationsGUI().transferMoney();
            }
        });

        JButton logout = new JButton("Log out");
        logout.setFont(new Font("New Roman",Font.BOLD,20));
        logout.addActionListener(this);

        rightPanel.add(Box.createRigidArea(new Dimension(0,50)));
        rightPanel.add(deposit,BorderLayout.WEST);
        rightPanel.add(Box.createVerticalStrut(130));
        rightPanel.add(transfer,BorderLayout.WEST);
        rightPanel.add(Box.createRigidArea(new Dimension(0,130)));
        rightPanel.add(logout,BorderLayout.WEST);



        rightPanel.setBackground(Color.GREEN.darker());
        operations.add(leftPanel,BorderLayout.WEST);
        operations.add(centerPanel,BorderLayout.CENTER);
        operations.add(rightPanel,BorderLayout.EAST);
        operations.setExtendedState(JFrame.MAXIMIZED_BOTH);
        operations.setVisible(true);
        operations.setDefaultCloseOperation(1);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        operations.setVisible(false);
    }


    /*
       It display the JFrame for the cash withdraw
       it is called in the action listener of cash withdraw
       button in the listOfOperatons method
     */
    public void cashWithdraw()
    {
        JFrame cashWithdraw = new JFrame("Cash withdraw");
        cashWithdraw.getContentPane().setBackground(Color.GREEN.darker());
        cashWithdraw.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel Panel = new JPanel();
        Panel.setPreferredSize(new Dimension(0,430));
        Panel.setBackground(Color.GREEN.darker());
        JLabel amount = new JLabel("Enter amount:");
        amount.setFont(new Font("New Roman",Font.BOLD,22));

        JTextField totalAmount = new JTextField();
        totalAmount.setFont(new Font("",Font.BOLD,20));
        totalAmount.setPreferredSize(new Dimension(160,50));
        Panel.add(Box.createVerticalStrut(380));
        Panel.add(amount);
        Panel.add(totalAmount);

        JPanel lowerPanel = new JPanel(new GridLayout(4,1,0,0));
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
                        String enteredAmount = totalAmount.getText();
                        StringBuffer str = new StringBuffer(enteredAmount);
                        try {
                            totalAmount.setText(String.valueOf(str.deleteCharAt(str.length() - 1)));
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
                        String prev = totalAmount.getText();
                        totalAmount.setText(prev+button.getText());
                    }
                });
            }
            else if(i==12)
            {
                button.setText("Enter");
                button.setBackground(Color.RED.darker());
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        double balance = 0;

                            balance = new ATM().getBalance();

                        double amount = Double.parseDouble(totalAmount.getText());
                        if (amount <= balance) {
                            JOptionPane optionPane = new JOptionPane("Transaction is processing...", JOptionPane.INFORMATION_MESSAGE);
                            JDialog dialog = optionPane.createDialog("Transaction");
                            dialog.setSize(new Dimension(400, 250));
                            dialog.setVisible(true);
                            cashWithdraw.dispose();

                        } else {
                            JOptionPane optionPane = new JOptionPane("Transaction cannot proceed\nInsufficient amount of balance...", JOptionPane.WARNING_MESSAGE);
                            JDialog dialog = optionPane.createDialog("Transaction");
                            dialog.setSize(new Dimension(400, 250));
                            dialog.setVisible(true);
                            totalAmount.setText(null);
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
                        String prev = totalAmount.getText();
                        totalAmount.setText(prev+button.getText());
                    }
                });
            }
            button.setFont(new Font("",Font.BOLD,18));
            lowerPanel.add(button);
        }

        cashWithdraw.add(Panel,BorderLayout.NORTH);
        cashWithdraw.add(lowerPanel,BorderLayout.CENTER);

        cashWithdraw.setVisible(true);
        cashWithdraw.setDefaultCloseOperation(1);
    }


    /*
      It display the JFrame for the deposit money
      it is called in the action listener of deposit money
      button in the listOfOperatons method
    */
    public void depositMoney()
    {
        JFrame deposit = new JFrame("Deposit money");
        deposit.getContentPane().setBackground(Color.GREEN.darker());
        deposit.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel Panel = new JPanel();
        Panel.setPreferredSize(new Dimension(0,430));
        Panel.setBackground(Color.GREEN.darker());
        JLabel amount = new JLabel("Enter amount:");
        amount.setFont(new Font("New Roman",Font.BOLD,22));

        JTextField totalAmount = new JTextField();
        totalAmount.setFont(new Font("",Font.BOLD,20));
        totalAmount.setPreferredSize(new Dimension(160,50));
        Panel.add(Box.createVerticalStrut(380));
        Panel.add(amount);
        Panel.add(totalAmount);

        JPanel lowerPanel = new JPanel(new GridLayout(4,1,0,0));
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
                        String enteredAmount = totalAmount.getText();
                        StringBuffer str = new StringBuffer(enteredAmount);
                        try {
                            totalAmount.setText(String.valueOf(str.deleteCharAt(str.length() - 1)));
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
                        String prev = totalAmount.getText();
                        totalAmount.setText(prev+button.getText());
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
                        double money = Double.parseDouble(totalAmount.getText());
                        new ATM().depositMoney(money);
                        JOptionPane optionPane = new JOptionPane("Money has successfully deposited",JOptionPane.INFORMATION_MESSAGE);
                        JDialog dialog = optionPane.createDialog("Transaction");
                        dialog.setVisible(true);
                        deposit.dispose();
                    }
                });
            }
            else
            {
                button.setText(String.valueOf(i));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String prev = totalAmount.getText();
                        totalAmount.setText(prev+button.getText());
                    }
                });
            }
            button.setFont(new Font("",Font.BOLD,18));
            lowerPanel.add(button);
        }

        deposit.add(Panel,BorderLayout.NORTH);
        deposit.add(lowerPanel,BorderLayout.CENTER);

        deposit.setVisible(true);
        deposit.setDefaultCloseOperation(1);
    }


    /*
      It display the JFrame for the transfer money
      it is called in the action listener of money transfer
      button in the listOfOperatons method
    */
    public void transferMoney()
    {
        JFrame transfer = new JFrame("Transfer money");
        JPanel accountPanel = new JPanel();
        accountPanel.setPreferredSize(new Dimension(0,350));
        accountPanel.setBackground(Color.GREEN.darker());

        JLabel name = new JLabel("Enter name:  ");
        name.setFont(new Font("New Roman",Font.BOLD,22));
        accountPanel.add(Box.createVerticalStrut(300));
        accountPanel.add(name);

        JTextField nameField = new JTextField();
        nameField.setFont(new Font("New Roman",Font.BOLD,20));
        nameField.setPreferredSize(new Dimension(200,50));
        accountPanel.add(nameField);
        accountPanel.add(Box.createHorizontalStrut(200));

        JLabel accountNo = new JLabel("Enter account number:  ");
        accountNo.setFont(new Font("New Roman",Font.BOLD,22));
        accountPanel.add(accountNo);

        JTextField accountField = new JTextField();
        accountField.setFont(new Font("New Roman",Font.BOLD,20));
        accountField.setPreferredSize(new Dimension(160,50));
        accountPanel.add(accountField);
        transfer.add(accountPanel,BorderLayout.NORTH);

        JPanel amountPanel = new JPanel();
        amountPanel.setBackground(Color.GREEN.darker());
        JLabel amount = new JLabel("Enter amount: ");
        amount.setFont(new Font("New Roman",Font.BOLD,22));
        amountPanel.add(amount);

        JTextField amountField = new JTextField();
        amountField.setFont(new Font("New Roman",Font.BOLD,20));
        amountField.setPreferredSize(new Dimension(160,50));
        amountPanel.add(amountField);
        transfer.add(amountPanel,BorderLayout.CENTER);

        JPanel lowerPanel = new JPanel();
        lowerPanel.setBackground(Color.GREEN.darker());
        lowerPanel.setPreferredSize(new Dimension(0,250));

        JButton enter = new JButton("Enter");
        enter.setFont(new Font("New Roman",Font.BOLD,20));

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = nameField.getText();
                String firstName = "";
                String lastName = "";
               for(int i=0;i<userName.length();i++)
               {
                   firstName += userName.charAt(i);
                   if(" ".equals(userName.charAt(i)))
                   {
                       break;
                   }

               }
               for(int i=0; i<userName.length();i++)
               {
                   if(userName.charAt(i)==firstName.charAt(i))
                       continue;
                   else
                       lastName += userName.charAt(i);

               }

                boolean check = new ATM().checkUserInfo(firstName,lastName,accountField.getText());
                if(check!=true)
                {
                    JOptionPane optionPane = new JOptionPane("Wrong name or account number", JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Warning");
                    dialog.setVisible(true);
                    nameField.setText(null);
                    accountField.setText(null);
                }
                else if(new ATM().checkBalance(Double.parseDouble(amountField.getText())))
                {
                    JOptionPane optionPane = new JOptionPane("Money has successfully transferred",JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Transaction");
                    dialog.setVisible(true);
                    transfer.dispose();
                }
                else
                {
                    JOptionPane optionPane = new JOptionPane("Insufficient amount of balance", JOptionPane.WARNING_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Warning");
                    dialog.setVisible(true);
                    amount.setText(null);
                }
            }
        });

        lowerPanel.add(enter);



        transfer.add(lowerPanel,BorderLayout.SOUTH);
        transfer.setExtendedState(JFrame.MAXIMIZED_BOTH);
        transfer.setVisible(true);
        transfer.setDefaultCloseOperation(1);
    }
}
