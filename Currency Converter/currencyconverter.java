import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverter extends JFrame implements ActionListener {
    JComboBox<String> fromCurrency, toCurrency;
    JTextField amountField, resultField;
    JButton convertButton;

    String[] currencies = {"USD", "EUR", "INR", "GBP"};

    // Hardcoded exchange rates (base: USD)
    double[][] exchangeRates = {
        {1.0, 0.93, 83.15, 0.79},  // From USD
        {1.07, 1.0, 89.10, 0.85},  // From EUR
        {0.012, 0.011, 1.0, 0.0095}, // From INR
        {1.26, 1.18, 105.12, 1.0}  // From GBP
    };

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel fromLabel = new JLabel("From:");
        fromCurrency = new JComboBox<>(currencies);
        
        JLabel toLabel = new JLabel("To:");
        toCurrency = new JComboBox<>(currencies);
        
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();
        
        JLabel resultLabel = new JLabel("Converted:");
        resultField = new JTextField();
        resultField.setEditable(false);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);

        add(fromLabel); add(fromCurrency);
        add(toLabel); add(toCurrency);
        add(amountLabel); add(amountField);
        add(resultLabel); add(resultField);
        add(new JLabel()); add(convertButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int fromIndex = fromCurrency.getSelectedIndex();
            int toIndex = toCurrency.getSelectedIndex();
            double amount = Double.parseDouble(amountField.getText());

            double rate = exchangeRates[fromIndex][toIndex];
            double result = amount * rate;

            resultField.setText(String.format("%.2f", result));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
        }
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}
