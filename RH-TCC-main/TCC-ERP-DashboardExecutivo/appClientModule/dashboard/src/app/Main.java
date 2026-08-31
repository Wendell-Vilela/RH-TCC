import view.TelaDashboard;
import javax.swing.*;


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
	          new TelaDashboard().setVisible(true);
	        }
	      }
	    );
	  }
	}
