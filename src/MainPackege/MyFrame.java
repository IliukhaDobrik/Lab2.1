package MainPackege;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame{
    MyFrame(){
        setTitle("Calculator"); //устанавливает имя
        setSize(WIDTH,HEIGHT); //устанваливает размер
        setResizable(false); //нельзя менять размер
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //что делает крестик
        setLocation((toolkit.getScreenSize().width - WIDTH)/2,(toolkit.getScreenSize().height - HEIGHT)/2); //ставим наш фрэйм в центр

        boxFormula1.add(Box.createHorizontalGlue());
        Box boxButton1 = Box.createHorizontalBox();
        addRadioButton("Formula №1", 1, boxButton1);
        String form1 = "sin(ln(y) + sin(pi*y^2)) * (x^2 + sin(z) + e^cos(z)^1/4";
        boxFormula1.add(Box.createHorizontalGlue());
        Box boxForm = Box.createHorizontalBox();
        boxForm.add(new JLabel(form1));
        boxFormula1.add(Box.createHorizontalGlue());
        boxFormula1.add(boxButton1);
        boxFormula1.add(boxForm);
        boxFormula1.add(Box.createHorizontalGlue());
        boxFormula1.setBorder(BorderFactory.createLineBorder(Color.YELLOW,2)); //граница

        //boxFormula2.add(Box.createHorizontalGlue());
        //Box boxButton2 = Box.createHorizontalBox();
        //addRadioButton("Formula №2", 2, boxButton2);
        //String form2 = "(cos(e^x) + ln(1+y)^2 + sqrt(e^cos(x) + sin^2(pi*z)) + sqrt(1/x) + cos(y^2))^sin(z)";
        ///boxFormula2.add(Box.createHorizontalGlue());
        //Box boxForm2 = Box.createHorizontalBox();
        //boxForm2.add(new JLabel(form2));
        //boxFormula2.add(boxButton2);
        //boxFormula2.add(boxForm);
        //boxFormula2.add(Box.createHorizontalGlue());
        //boxFormula2.setBorder(BorderFactory.createLineBorder(Color.YELLOW,2)); //граница

        JLabel labelX = new JLabel("X: ");
        textFieldX = new JTextField("0",10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());

        JLabel labelY = new JLabel("Y: ");
        textFieldY = new JTextField("0",10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());

        JLabel labelZ = new JLabel("Z: ");
        textFieldZ = new JTextField("0",10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box boxVariables = Box.createHorizontalBox();
        boxVariables.setBorder(BorderFactory.createLineBorder(Color.RED,2));
        boxVariables.add(Box.createHorizontalGlue());
        boxVariables.add(labelX);
        boxVariables.add(Box.createHorizontalStrut(10));
        boxVariables.add(textFieldX);
        boxVariables.add(Box.createHorizontalStrut(100));
        boxVariables.add(labelY);
        boxVariables.add(Box.createHorizontalStrut(10));
        boxVariables.add(textFieldY);
        boxVariables.add(Box.createHorizontalStrut(100));
        boxVariables.add(labelZ);
        boxVariables.add(Box.createHorizontalStrut(10));
        boxVariables.add(textFieldZ);
        boxVariables.add(Box.createHorizontalGlue());

        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());

        Box boxResult = Box.createHorizontalBox();
        boxResult.add(Box.createHorizontalGlue());
        boxResult.add(labelForResult);
        boxResult.add(Box.createHorizontalStrut(10));
        boxResult.add(textFieldResult);
        boxResult.add(Box.createHorizontalGlue());
        boxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));

        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    double x = Double.parseDouble(textFieldX.getText());
                    double y = Double.parseDouble(textFieldY.getText());
                    double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaID==1)
                        result = formula1(x,y,z);
                    else
                        result = formula2(x,y,z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MyFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        Box boxButtons = Box.createHorizontalBox();
        boxButtons.add(Box.createHorizontalGlue());
        boxButtons.add(buttonCalc);
        boxButtons.add(Box.createHorizontalStrut(30));
        boxButtons.add(buttonReset);
        boxButtons.add(Box.createHorizontalGlue());
        boxButtons.setBorder(
                BorderFactory.createLineBorder(Color.GREEN,2));

        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(boxFormula1);
        contentBox.add(boxFormula2);
        contentBox.add(boxVariables);
        contentBox.add(boxResult);
        contentBox.add(boxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);

        setVisible(true);
    }

    private int WIDTH = 500;
    private int HEIGHT = 500;

    private JTextField textFieldX = new JTextField();
    private JTextField textFieldY = new JTextField();
    private JTextField textFieldZ = new JTextField();
    private JTextField textFieldResult = new JTextField();

    private ButtonGroup radioButtons = new ButtonGroup();

    private Box boxFormula1 = Box.createVerticalBox();
    private Box boxFormula2 = Box.createHorizontalBox();

    private int formulaID = 1;

    private Border border = BorderFactory.createLineBorder(Color.GREEN,3);
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private JLabel label = new JLabel();

    private void addRadioButton(String buttonName, final int formulaID, Box box){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyFrame.this.formulaID = formulaID;
            }
        });
        radioButtons.add(button);
        box.add(button);
    }

    public double formula1(double x, double y, double z){
        double q = Math.sin(Math.log(y) + Math.sin(Math.PI * y*y));
        double w = Math.pow(x*x+ Math.sin(z) + Math.pow(Math.E, Math.cos(z)),1/4);
        return q*w;
    }

    public double formula2(double x, double y, double z){
        double q = Math.cos(Math.pow(x, Math.E)) + Math.log((1+y)*(1+y)) + Math.sqrt(1/x) + Math.cos(y*y);
        return Math.pow(q, Math.sin(z));
    }
}
