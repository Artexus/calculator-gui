import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonGUI extends JFrame implements ActionListener {
	JButton button[] = new JButton[10];
	JButton arithmeticOperator[] = new JButton[10];
	JLabel labelResult = new JLabel();
	JLabel labelNumber = new JLabel();
	boolean validOperator = true;
	private int saveOperator;
	private double number1;
	private double number2;
	private int clearInt;
	private int turn;
	boolean number1Valid = false;
	boolean number2Valid = false;
	Calculate calculate = new Calculate();
	int integerCount = 0;
	private double results;
	public ButtonGUI(){
		for(int i = 0 ; i < 10 ; i ++) {
			button[i] = new JButton();
			button[i].addActionListener(this);
		}
		this.turn = 0;
		//buttons GUI
		//Numbers
		button[0].setBounds(125, 400, 50, 50);
		button[0].setText("0");
		
		button[1].setBounds(73,348,50,50);
		button[1].setText("1");
		button[2].setBounds(125,348,50,50);
		button[2].setText("2");
		button[3].setBounds(177,348,50,50);
		button[3].setText("3");
		button[6].setBounds(177,296,50,50);
		button[6].setText("6");
		button[5].setBounds(125,296,50,50);
		button[5].setText("5");
		button[4].setBounds(73,296,50,50);
		button[4].setText("4");
		button[7].setBounds(73, 244, 50, 50);
		button[7].setText("7");
		button[8].setBounds(125, 244, 50, 50);
		button[8].setText("8");
		button[9].setBounds(177,244,50,50);
		button[9].setText("9");
		
		//Aritmethic Operator
		for(int i = 0 ; i < 10 ;i ++)
		{
			arithmeticOperator[i] = new JButton();
			arithmeticOperator[i].addActionListener(this);
		}
		arithmeticOperator[0].setBounds(21, 348, 50, 50);
		arithmeticOperator[0].setText("+");
		arithmeticOperator[1].setBounds(21, 296, 50, 50);
		arithmeticOperator[1].setText("-");
		arithmeticOperator[2].setBounds(21, 244, 50, 50);
		arithmeticOperator[2].setText("X");
		arithmeticOperator[3].setBounds(21, 400, 50, 50);
		arithmeticOperator[3].setText("÷");
		arithmeticOperator[4].setBounds(177,400,50,50);
		arithmeticOperator[4].setText("=");
		arithmeticOperator[5].setBounds(73,400,50,50);
		arithmeticOperator[5].setText("<-");
		
		for(int j = 0 ;j < 6;j ++) {
			add(arithmeticOperator[j]);
		}
		
		//set Title Calculator
		setTitle("Calculator");
		setSize(300,500);
		for(int j = 0 ; j < 10 ; j ++) {
			add(button[j]);
		}
		labelResult.setBounds(30, 125, 500,50 );
		labelNumber.setBounds(30,73,500,50);
		setBackground(Color.black);
		add(labelResult);
		add(labelNumber);
		setLayout(null);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//apabila dipencet
		if(e.getSource() == arithmeticOperator[5]) {
			validOperator = false;
			if(turn % 2 == 0)
				number1Valid = true;
			if(number1 != 0) {
				clearInt = (int) number1;
				clearInt /= 10;
				number1 = clearInt;
			}
			labelResult.setText("Input = " +number1);
			repaint();
		}
		for(int i = 0 ; i < 10 ; i ++){
			if(e.getSource() == button[i]) {
				validOperator = false;
				if(turn % 2 == 0) {
					number1Valid = true;
					if(number1 == 0) {
						number1 += i;
					}
					else
					{
						number1 *= 10;
						number1 += i;
					}
					labelResult.setText("Input = " +number1);
					repaint();
				}
				else {
					number2Valid = true;
					if(number2 == 0) {
						number2 += i;
					}
					else
					{
						number2 *= 10;
						number2 += i;
					}
					labelResult.setText("Input = " +number2);
					turn  = 1; // endless input ke number 2
					repaint();
				}
			}
			if(e.getSource() == arithmeticOperator[i]) {
				
				if(!validOperator)
				{
					turn = 1;
					validOperator = true;
					//0 itu +
					//1 itu -
					//2 itu X
					//3 itu /
					if(saveOperator == 0 && number1Valid && number2Valid) {
						number1 = calculate.plus(number1, number2);
						number2 = 0;
						number2Valid = false;
						labelNumber.setText(""+number1);
						
					}
					else if(saveOperator == 1 &&number1Valid && number2Valid) {
						number1 = calculate.min(number1, number2);
						number2 = 0;
						number2Valid = false;
						labelNumber.setText(""+number1);
					}
					else if(saveOperator == 2 &&number1Valid && number2Valid) {
						number1 = calculate.multiply(number1, number2);
						number2 = 0;
						number2Valid = false;
						labelNumber.setText(""+number1);
					}
					else if(saveOperator == 3 &&number1Valid && number2Valid) {
						number1 = calculate.divide(number1, number2);
						number2 = 0;
						number2Valid = false;
						labelNumber.setText(""+number1);
					}
					if(i == 0 && !number2Valid) {
						saveOperator = 0;
						labelNumber.setText(number1 + "+");
					}
					else if(i == 1 && !number2Valid) {
						saveOperator = 1;
						labelNumber.setText(number1 + "-");
					}
					else if(i == 2 && !number2Valid) {
						saveOperator = 2;
						labelNumber.setText(number1 + "x");
					}
					else if(i == 3 && !number2Valid){
						saveOperator = 3;
						labelNumber.setText(number1 + "÷");
					}
					if(i == 4) {
						labelNumber.setText("");
						labelResult.setText("Result = " +number1);
					}
					repaint();
				}
			}					
		}
	}
	@Override
	public void setBackground(Color bgColor) {
		super.setBackground(bgColor);
		repaint();
	}
}
