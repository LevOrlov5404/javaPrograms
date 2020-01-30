import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorEngine implements ActionListener {
	Calculator parent;
	char selectedAction = ' ';
	double currentResult = 0;
	boolean case_error = false;

	CalculatorEngine(Calculator parent) {
		this.parent = parent;
	}
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();
		String dispFieldText=parent.displayField.getText();
		double displayValue = 0;
		if (!case_error && !"".equals(dispFieldText))
			try {
				displayValue = Double.parseDouble(dispFieldText);
			} catch (NumberFormatException e1) {
				JOptionPane.showConfirmDialog(null,
						"Пожалуйста, введите число", "Неправильный ввод",
						JOptionPane.PLAIN_MESSAGE);
				parent.displayField.setText("");
				return;
			}
		Object src = e.getSource();
		if (src == parent.buttonPlus) {
			selectedAction = '+';
			currentResult=displayValue;
			parent.displayField.setText("");
		}
		else if (src == parent.buttonMinus) {
			selectedAction = '-';
			currentResult=displayValue;
			parent.displayField.setText("");
		}
		else if (src == parent.buttonDevide) {
			selectedAction = '/';
			currentResult=displayValue;
			parent.displayField.setText("");
		}
		else if (src == parent.buttonMulty) {
			selectedAction = '*';
			currentResult=displayValue;
			parent.displayField.setText("");
		}
		else if (src == parent.buttonEqual) {
			if (selectedAction == '+') {
				currentResult = currentResult + displayValue;
				parent.displayField.setText("" + currentResult);
			}
			else if (selectedAction == '-') {
				currentResult = currentResult - displayValue;
				parent.displayField.setText("" + currentResult);
			}
			else if (selectedAction == '/') {
				if (displayValue == 0) {
					parent.displayField.setText("На 0 делить нельзя!");
					case_error = true;
				}
				else {
					currentResult = currentResult / displayValue;
					parent.displayField.setText("" + currentResult);
				}
			}
			else if (selectedAction == '*') {
				currentResult = currentResult * displayValue;
				parent.displayField.setText("" + currentResult);
			}
		}
		else if (dispFieldText.indexOf('.') != -1 && src == parent.buttonPoint) {
			parent.displayField.setText("Ошибка! Больше одной точки.");
			case_error = true;
			currentResult = 0;
		}
		else {
			if (case_error)
				parent.displayField.setText(clickedButton.getText());
			else {
				parent.displayField.setText(dispFieldText + clickedButton.getText());
			}
			case_error = false;
		}
	}
}
