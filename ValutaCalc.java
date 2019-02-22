// Andrea Vesterhus
// Valutakurser sist oppdatert 26.04.2017 21:30

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JOptionPane.*;

public class ValutaCalc extends JFrame implements ActionListener
{
    private JButton btnGBP, btnEUR,btnUSD, btnSEK, btnRmNOK, btnRmOther, btnRmBoth, btnExit; 
    private JTextField nokValuta, otherValuta;
    private JLabel valutaText;
    
    public ValutaCalc()
    {
        super("ValutaKalkulator");
        
        JPanel pnlDisplay = new JPanel(new GridLayout(1, 4));
        JPanel pnlCurrency = new JPanel(new GridLayout(2, 2));
        JPanel pnlButtons = new JPanel(new GridLayout(1, 4));
        valutaText = new JLabel("Annen Valuta");
        
        nokValuta = new JTextField(10);
        otherValuta = new JTextField(10);
        pnlDisplay.add(new JLabel("NOK"));
        pnlDisplay.add(nokValuta);
        pnlDisplay.add(valutaText);
        pnlDisplay.add(otherValuta);
        add(pnlDisplay, BorderLayout.NORTH);
        
        btnGBP = new JButton("GBP");
        btnEUR = new JButton("EUR");
        btnUSD = new JButton("USD");
        btnSEK = new JButton("SEK");
        
        btnGBP.addActionListener(this);
        btnEUR.addActionListener(this);
        btnUSD.addActionListener(this);
        btnSEK.addActionListener(this);
        
        pnlCurrency.add(btnGBP);
        pnlCurrency.add(btnEUR);
        pnlCurrency.add(btnUSD);
        pnlCurrency.add(btnSEK);
        add(pnlCurrency, BorderLayout.CENTER);
        
        btnRmNOK = new JButton("Fjern NOK");
        btnRmOther = new JButton("Fjern annen valuta");
        btnRmBoth = new JButton("Fjern begge");
        btnExit = new JButton("Avslutt");
        
        btnRmNOK.addActionListener(this);
        btnRmOther.addActionListener(this);
        btnRmBoth.addActionListener(this);
        btnExit.addActionListener(this);
        
        pnlButtons.add(btnRmNOK);
        pnlButtons.add(btnRmOther);
        pnlButtons.add(btnRmBoth);
        pnlButtons.add(btnExit);
        add(pnlButtons, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 200);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event) {
        JButton clicked = (JButton) event.getSource();
        
        //BUTTONS PANEL (pnlButtons) METHODS
        if (clicked == btnExit)
            System.exit(0);
        if (clicked == btnRmNOK) 
            nokValuta.setText("");
        if (clicked == btnRmOther) {
            otherValuta.setText("");
            valutaText.setText("Annen Valuta");
        }
        if (clicked == btnRmBoth) {
            nokValuta.setText("");
            otherValuta.setText("");
            valutaText.setText("Annen Valuta");
        }
        
        String nok = nokValuta.getText();
        String otherV = otherValuta.getText();
        double result = 0;
        
        //BRITISKE PUND
        if (clicked == btnGBP) {
            if (nok.length() == 0 && otherV.length() == 0) {
                showMessageDialog(this, "Du må oppgi minst en valuta!");
            } else if (nok.length() > 0 && otherV.length() == 0) {
                double nokInput = Double.parseDouble(nok);
                result = nokInput / 11.0110;
                otherValuta.setText("" + result);
                valutaText.setText("GBP");
            }
            else if (otherV.length() > 0 && nok.length() == 0) {
                double otherVInput = Double.parseDouble(otherV);
                result = otherVInput * 11.0110;
                nokValuta.setText("" + result);
                valutaText.setText("GBP");
            }
            else {
                showMessageDialog(this, "Du kan bare oppgi en valuta!");
            }
        }
        
        //EURO
        if (clicked == btnEUR) {
            if (nok.length() == 0 && otherV.length() == 0) {
                showMessageDialog(this, "Du må oppgi minst en valuta!");
            } else if (nok.length() > 0 && otherV.length() == 0) {
                double nokInput = Double.parseDouble(nok);
                result = nokInput / 9.3485;
                otherValuta.setText("" + result);
                valutaText.setText("EUR");
            }
            else if (otherV.length() > 0 && nok.length() == 0) {
                double otherVInput = Double.parseDouble(otherV);
                result = otherVInput * 9.3485;
                nokValuta.setText("" + result);
                valutaText.setText("EUR");
            }
            else {
                showMessageDialog(this, "Du kan bare oppgi en valuta!");
            }
        }
        
        //US DOLLAR
        if (clicked == btnUSD) {
            if (nok.length() == 0 && otherV.length() == 0) {
                showMessageDialog(this, "Du må oppgi minst en valuta!");
            } else if (nok.length() > 0 && otherV.length() == 0) {
                double nokInput = Double.parseDouble(nok);
                result = nokInput / 8.5821;
                otherValuta.setText("" + result);
                valutaText.setText("USD");
            }
            else if (otherV.length() > 0 && nok.length() == 0) {
                double otherVInput = Double.parseDouble(otherV);
                result = otherVInput * 8.5821;
                nokValuta.setText("" + result);
                valutaText.setText("USD");
            }
            else {
                showMessageDialog(this, "Du kan bare oppgi en valuta!");
            }
        }
        
        //SVENSKE KRONER
        if (clicked == btnSEK) {
            if (nok.length() == 0 && otherV.length() == 0) {
                showMessageDialog(this, "Du må oppgi minst en valuta!");
            } else if (nok.length() > 0 && otherV.length() == 0) {
                double nokInput = Double.parseDouble(nok);
                result = nokInput * 0.9778;
                otherValuta.setText("" + result);
                valutaText.setText("SEK");
            }
            else if (otherV.length() > 0 && nok.length() == 0) {
                double otherVInput = Double.parseDouble(otherV);
                result = otherVInput / 0.9778;
                nokValuta.setText("" + result);
                valutaText.setText("SEK");
            }
            else {
                showMessageDialog(this, "Du kan bare oppgi en valuta!");
            }
        }
    }
}
