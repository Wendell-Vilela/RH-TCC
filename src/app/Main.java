package app;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import view.TelaMenu;

public class Main {

	  public static void main(String[] args) {
	    SwingUtilities.invokeLater(
	      new Runnable() {
	        public void run() {
	          try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	          } catch (Exception e) {
	            e.printStackTrace();
	          }
	          new TelaMenu().setVisible(true);
	        }
	      }
	    );
	  }
	}
