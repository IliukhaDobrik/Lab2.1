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

        boxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Formula №1", 1); //добавили кнопку
        boxFormulaType.add(Box.createHorizontalStrut(20)); //добавляем пустую область
        addRadioButton("Formula №2", 2);
        boxFormulaType.add(Box.createHorizontalGlue());
        boxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW,2)); //граница

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
        contentBox.add(boxFormulaType);
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

    private Box boxFormulaType = Box.createHorizontalBox();

    private int formulaID = 1;

    private Border border = BorderFactory.createLineBorder(Color.GREEN,3);
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private JLabel label = new JLabel();

    private void addRadioButton(String buttonName, final int formulaID){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyFrame.this.formulaID = formulaID;
            }
        });
        radioButtons.add(button);
        boxFormulaType.add(button);
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
