import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame{
	
	//tabell til å holde alle siffer-knapper
	private JButton[] siffre;
	//tabell til å holde alle aritmetiske knapper
	private JButton[] regneoperasjoner;
	
	private JButton btnC, btnErLik;
	
	private JTextField txtDisplay;
	
	public Calculator(){
		super("Kalkulator");
		
		//opprette siffer-knapper og legge dem inn i sifferpanel, + C og =
		JPanel pnlSifferpanel = new JPanel(new GridLayout(4,3,5,5));
		String[] sifferTekst = {"0","1","2","3","4","5","6","7","8","9"};
		siffre = new JButton[sifferTekst.length];
		
		RegneLytter lytter = new RegneLytter(); 
		
		for(int i = 0; i < sifferTekst.length; i++){
			siffre[i] = new JButton( sifferTekst[i] );
			siffre[i].addActionListener(lytter);	//koble lytter og knapp
			pnlSifferpanel.add(siffre[i]);
			
		}
		btnC = new JButton("C");
		btnC.addActionListener(lytter);
		
		btnErLik = new JButton("=");
		btnErLik.addActionListener(lytter);
		
		pnlSifferpanel.add(btnC);
		pnlSifferpanel.add(btnErLik);
		
		//opprette knapper for regneoperasjoner og legge dem inn i eget panel
		JPanel pnlRegneoperasjoner = new JPanel( new GridLayout(4,1,5,5));
		String[] operasjoner = {"+","-","*","/"};
		//la regneoperasjonstabellen være like stor som antall operasjonestekster
		regneoperasjoner = new JButton[operasjoner.length];
		
		for(int i = 0; i < operasjoner.length; i++){
			regneoperasjoner[i] = new JButton(operasjoner[i]);
			regneoperasjoner[i].addActionListener(lytter); //koble lytter og knapp
			pnlRegneoperasjoner.add(regneoperasjoner[i]);
		}
		
		txtDisplay = new JTextField("0");
		//ikke la bruker kunne skrive direkte i displayet
		txtDisplay.setEnabled(false); 
		
		//legge begge panelene inn på vinduet, sammen med displayet
		add(txtDisplay, BorderLayout.NORTH);
		add(pnlSifferpanel, BorderLayout.CENTER);
		add(pnlRegneoperasjoner, BorderLayout.EAST);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(280, 200);
		setVisible(true);
	}
	private class RegneLytter implements ActionListener{
		//hjelpevariabel for å lett kunne nullstille displayet etter at 
		//utregning er foretatt og svar er vist
		private boolean svarNettoppUtregnet = false;
		
		public void actionPerformed(ActionEvent e) {
			//hvis ikke "=" eller "C" trykkes, skal siste inntasting legges 
			//bakerst i displayet
			String regnestykke = txtDisplay.getText();
			int antallTegn = regnestykke.length();
			
			char sisteTegn = regnestykke.charAt(antallTegn-1);
			//if(sisteTegn == 'C'){
			if( e.getSource() == btnC){
				txtDisplay.setText("0"); //sletter innhold i tekstfeltet
				
			}else if(e.getSource() == btnErLik){
				
				//koden i if..else blokkene under er relativt identiske og kunne gjerne
				//blitt refaktorert (ombygd) til å legges inn metoden 
				//private int(eller double) regntUt(String regnestykke, char operasjon)
				
				int svar=0;
				if(regnestykke.indexOf('+') > 0){
					int pos = regnestykke.indexOf('+');
					int tall1 = Integer.parseInt(regnestykke.substring(0, pos));
					int tall2 = Integer.parseInt(regnestykke.substring(pos+1));
					svar = tall1 + tall2;
				}else if(regnestykke.indexOf('-') > 0){
					int pos = regnestykke.indexOf('-');
					int tall1 = Integer.parseInt(regnestykke.substring(0, pos));
					int tall2 = Integer.parseInt(regnestykke.substring(pos+1));
					svar = tall1 - tall2;
				}else if(regnestykke.indexOf('*') > 0){
					int pos = regnestykke.indexOf('*');
					int tall1 = Integer.parseInt(regnestykke.substring(0, pos));
					int tall2 = Integer.parseInt(regnestykke.substring(pos+1));
					svar = tall1 * tall2;
				}else{//divisjon
					int pos = regnestykke.indexOf('/');
					int tall1 = Integer.parseInt(regnestykke.substring(0, pos));
					int tall2 = Integer.parseInt(regnestykke.substring(pos+1));
					svar = tall1 / tall2;
				}
				txtDisplay.setText(""+ svar);
				
			}else{//et tall eller en regneoperasjonstegn: legg til bakerst
				String gammelTekst = txtDisplay.getText();
				//finne ut om det står 0 først i displayet og evt overskrive dette
				if(gammelTekst.startsWith("0")){ //kunne også brukt gammelTekst.charAt(0) == '0'
					//e.getActionCommand() returnerer teksten som står på gui-elementet
					//som forårsaket hendelsen.
					txtDisplay.setText(e.getActionCommand());
				}else{
					txtDisplay.setText(gammelTekst + e.getActionCommand());//bakerst					
				}
			}
			
		}
		
	}
}
