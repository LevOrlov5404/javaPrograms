import javax.swing.*;
import java.awt.*;

public class Calculator {
	// Объявление всех компонентов калькулятора.
	JPanel windowContent;
	JTextField displayField;
	int i;
	JButton[] numButtons = new JButton[10];
	JButton buttonPoint;
	JButton buttonEqual;
	JPanel p1;
	JButton buttonPlus;
	JButton buttonMinus;
	JButton buttonDevide;
	JButton buttonMulty;
	JPanel p2;

	// В конструкторе создаются все компоненты
	// и добавляются на фрейм с помощью комбинации
	// Borderlayout и Gridlayout
	Calculator() {
		windowContent = new JPanel();
		// Задаём схему для этой панели
		BorderLayout bl = new BorderLayout();
		windowContent.setLayout(bl);
		// Создаём и отображаем поле
		// Добавляем его в Северную область окна
		displayField = new JFormattedTextField();
		displayField.setHorizontalAlignment(SwingConstants.RIGHT);
		windowContent.add("North",displayField);
		// Создаём панель с GridLayout
		// которая содержит 12 кнопок - 10 кнопок с числами
		// и кнопки с точкой и знаком равно
		p1 = new JPanel();
		GridLayout gl = new GridLayout(4, 3);
		p1.setLayout(gl);
		CalculatorEngine calcEngine = new CalculatorEngine(this);
		i = 0;
		while (i < 10)
		{
			numButtons[i] = new JButton("" + ((i + 1) % 10));
			numButtons[i].addActionListener(calcEngine);
			p1.add(numButtons[i]);
			++i;
		}
		buttonPoint = new JButton(".");
		buttonPoint.addActionListener(calcEngine);
		buttonEqual = new JButton("=");
		buttonEqual.addActionListener(calcEngine);
		// Добавляем кнопки на панель p1
		p1.add(buttonPoint);
		p1.add(buttonEqual);
		// Помещаем панель p1 в центральную область окна
		windowContent.add("Center", p1);
		buttonPlus = new JButton("+");
		buttonPlus.addActionListener(calcEngine);
		buttonMinus = new JButton("-");
		buttonMinus.addActionListener(calcEngine);
		buttonDevide = new JButton("/");
		buttonDevide.addActionListener(calcEngine);
		buttonMulty = new JButton("*");
		buttonMulty.addActionListener(calcEngine);
		p2 = new JPanel();
		p2.setLayout(new GridLayout(4, 1));
		p2.add(buttonPlus);
		p2.add(buttonMinus);
		p2.add(buttonDevide);
		p2.add(buttonMulty);
		windowContent.add("East", p2);
		//Создаём фрейм и задаём его основную панель
		JFrame frame = new JFrame("Calculator");
		frame.setContentPane(windowContent);
		// делаем размер окна достаточным
		// для того, чтобы вместить все компоненты
		frame.pack();
		// Наконец, отображаем окно
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		Calculator calc = new Calculator();
	}
}
