package app;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import view.TelaMenu;

public class Main {

	  public static void main(String[] args) {
	    // Toda interface Swing deve nascer na Event Dispatch Thread (EDT).
	    SwingUtilities.invokeLater(
	      new Runnable() {
	        public void run() {
	          try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	          } catch (Exception e) {
	            // O visual padrao continua funcional se o Look and Feel falhar.
	            e.printStackTrace();
	          }
	          new TelaMenu().setVisible(true);
	        }
	      }
	    );
	  }
	}
