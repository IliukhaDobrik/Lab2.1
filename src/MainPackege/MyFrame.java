package MainPackege;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame{
    private final int WIDTH = 700;
    private final int HEIGHT = 500;

    private JTextField textFieldX = new JTextField();
    private JTextField textFieldY = new JTextField();
    private JTextField textFieldZ = new JTextField();
    private JTextField textFieldResult = new JTextField();
    private JTextField textFieldMem = new JTextField();

    private ButtonGroup radioButtons = new ButtonGroup();

    private Box boxFormula1 = Box.createVerticalBox();
    private Box boxFormula2 = Box.createVerticalBox();

    private int formulaID = 1;

    private int memID = 1;
    private Double mem1 = 1d;
    private Double mem2 = 2d;
    private Double mem3 = 3d;

    MyFrame(){
        setTitle("Калькулятор");
        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        setLocation((toolkit.getScreenSize().width - WIDTH)/2,(toolkit.getScreenSize().height - HEIGHT)/2);

        addBoxFormula(boxFormula1,1);
        addBoxFormula(boxFormula2,2);

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

        Box boxMemory = Box.createVerticalBox();
        Box boxRadio = Box.createHorizontalBox();
        boxRadio.add(Box.createHorizontalGlue());
        boxRadio.add(Box.createHorizontalStrut(10));
        addRadioButton("Переменная 1", boxRadio, 1);
        boxRadio.add(Box.createHorizontalStrut(80));
        addRadioButton("Переменная 2", boxRadio, 2);
        boxRadio.add(Box.createHorizontalStrut(80));
        addRadioButton("Переменная 3", boxRadio, 3);
        boxRadio.add(Box.createHorizontalStrut(10));
        boxRadio.add(Box.createHorizontalGlue());
        Box boxField = Box.createHorizontalBox();
        boxField.add(Box.createHorizontalGlue());
        textFieldMem = new JTextField("0",10);
        textFieldMem.setMaximumSize(textFieldY.getPreferredSize());
        boxField.add(textFieldMem);
        boxField.add(Box.createHorizontalGlue());
        boxMemory.add(boxRadio);
        boxMemory.add(boxField);
        boxMemory.setBorder(BorderFactory.createLineBorder(Color.cyan,2));

        JButton memCalc = new JButton("M+");
        memCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double x = Double.parseDouble(textFieldX.getText());
                    double y = Double.parseDouble(textFieldY.getText());
                    double z = Double.parseDouble(textFieldZ.getText());
                    if (formulaID==1){
                        if (memID == 1){
                            mem1+=formula1(x,y,z);
                            textFieldMem.setText(mem1.toString());
                        }
                        if (memID == 2){
                            mem2+=formula1(x,y,z);
                            textFieldMem.setText(mem2.toString());
                        }
                        else {
                            mem3+=formula1(x,y,z);
                            textFieldMem.setText(mem3.toString());
                        }
                    }
                    else {
                        if (memID == 1){
                            mem1+=formula2(x,y,z);
                            textFieldMem.setText(mem1.toString());
                        }
                        if (memID == 2){
                            mem2+=formula2(x,y,z);
                            textFieldMem.setText(mem2.toString());
                        }
                        else {
                            mem3+=formula2(x,y,z);
                            textFieldMem.setText(mem3.toString());
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MyFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

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
        boxButtons.add(memCalc);
        boxButtons.add(Box.createHorizontalStrut(30));
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
        contentBox.add(boxMemory);
        contentBox.add(boxResult);
        contentBox.add(boxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);

        setVisible(true);
    }

    private void addBoxFormula(Box boxFormula, int formulaID){
        boxFormula.add(Box.createHorizontalGlue());
        Box boxButton1 = Box.createHorizontalBox();
        addRadioButton("Формула №" + formulaID, formulaID, boxButton1);
        boxFormula.add(Box.createHorizontalGlue());
        Box boxForm = Box.createHorizontalBox();
        ImageIcon imageIcon;
        if (formulaID == 1){
            imageIcon = new ImageIcon("D:\\Java\\Lab2\\src\\Формула1.png");
        }
        else {
            imageIcon = new ImageIcon("D:\\Java\\Lab2\\src\\Формула2.png");
        }
        JLabel form1 = new JLabel();
        form1.setIcon(imageIcon);
        boxForm.add(form1);
        boxFormula.add(Box.createHorizontalGlue());
        boxFormula.add(boxButton1);
        boxFormula.add(boxForm);
        boxFormula.add(Box.createHorizontalGlue());
        boxFormula.setBorder(BorderFactory.createLineBorder(Color.YELLOW,2));
    }

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

    private void addRadioButton(String buttonName,Box box,final int memID){
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (memID == 1){
                    MyFrame.this.memID = memID;
                    textFieldMem.setText(mem1.toString());
                }
                if (memID == 2){
                    MyFrame.this.memID = memID;
                    textFieldMem.setText(mem2.toString());
                }
                if (memID == 3){
                    MyFrame.this.memID = memID;
                    textFieldMem.setText(mem3.toString());
                }
            }
        });
        radioButtons.add(button);
        box.add(button);
    }

    public double formula1(double x, double y, double z){
        double q = Math.sin(Math.log(y) + Math.sin(Math.PI * y*y));
        double w = Math.pow(x*x+ Math.sin(z) + Math.pow(Math.E, Math.cos(z)),0.25);
        return q*w;
    }

    public double formula2(double x, double y, double z){
        double q = Math.cos(Math.pow(x, Math.E)) + Math.log((1+y)*(1+y)) + Math.sqrt(1/x) + Math.cos(y*y);
        return Math.pow(q, Math.sin(z));
    }
}
