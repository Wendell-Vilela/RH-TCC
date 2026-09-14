package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Model.GestaoFerias; 
 
public class TelaGestaoFerias extends JPanel { 
 
    /** 
	 *  
	 */ 
	private static final long serialVersionUID = 1L; 
 
    private JTable tabela;
    private DefaultTableModel modelo;
	 
 
 
    
 
    public TelaGestaoFerias() { 
        setLayout(new BorderLayout()); 
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); 
        setBackground(Color.WHITE); 
 
         String[]colunas ={ 
        "Departamento", 
        "Até 12 meses", 
        "De 12-18 meses", 
        "Risco (+18 meses)" 
 
        }; 
 
     
 
 
    modelo = new DefaultTableModel(colunas, 0) { 
 
       @Override 
        public boolean isCellEditable(int row, int column) { 
            return false; 
        } 
        }; 
     
        tabela = new JTable(modelo); 
        tabela.setBackground(Color.WHITE);
        tabela.setForeground(Color.BLACK); 
        
        tabela.setRowHeight(40);

        tabela.setGridColor(Color.BLACK); 
        tabela.getTableHeader().setBackground(Color.WHITE); 
        tabela.getTableHeader().setForeground(Color.BLACK); 

        tabela.getTableHeader().setPreferredSize(
        new Dimension(0, 50)
        );

        tabela.getTableHeader().setOpaque(true); 
        tabela.getTableHeader().setDefaultRenderer( 
    new javax.swing.table.DefaultTableCellRenderer() { 
 
        @Override 
        public Component getTableCellRendererComponent( 
                JTable table, 
                Object value, 
                boolean isSelected, 
                boolean hasFocus, 
                int row, 
                int column 
        ) { 
 
            JLabel label = new JLabel(value.toString()); 
 
            label.setBackground(Color.WHITE); 
            label.setForeground(Color.BLACK); 
            label.setOpaque(true); 
            
            label.setFont(label.getFont().deriveFont(Font.BOLD));
            label.setBorder( 
                BorderFactory.createLineBorder(Color.BLACK) 
            ); 
 
            label.setHorizontalAlignment(JLabel.CENTER); 
 
            return label; 
        } 
    } 
); 
 
        tabela.setSelectionBackground(Color.DARK_GRAY); 
        tabela.setSelectionForeground(Color.BLACK); 
        DefaultTableCellRenderer centralizar = new DefaultTableCellRenderer(); 
 
        centralizar.setHorizontalAlignment(JLabel.CENTER); 
 
        for (int i = 0; i < tabela.getColumnCount(); i++) { 
            tabela.getColumnModel() 
                .getColumn(i) 
                .setCellRenderer(centralizar); 
        } 
        tabela.getTableHeader().setReorderingAllowed(false); 
 
        JScrollPane scrollpane = new JScrollPane(tabela); 
 
        scrollpane.setBackground(Color.WHITE); 
        scrollpane.getViewport().setBackground(Color.WHITE); 
 
        montar(scrollpane); 
    } 
    

    
    private void montar(JScrollPane scrollpane) { 
        JPanel formulario = new JPanel(new GridBagLayout()); 
        formulario.setBackground(Color.WHITE); 
        formulario.setBorder( 
        BorderFactory.createTitledBorder( 
        BorderFactory.createLineBorder(Color.BLACK), 
        "Gestão de férias", 
        0, 
        0, 
        null, 
        Color.BLACK 
        ) 
        ); 
 
        GridBagConstraints g = new GridBagConstraints(); 
        g.insets = new Insets(5, 5, 5, 5); 
 
        g.gridx = 0; 
        g.gridy = 0; 
        g.weightx = 1; 
        g.weighty = 1; 
        g.fill = GridBagConstraints.BOTH; 
 
 
        
 
         
 
        JPanel conteudo = new JPanel(new BorderLayout(0,15)); 
        conteudo.setBackground(Color.WHITE); 
        conteudo.add(scrollpane, BorderLayout.CENTER); 
         
 
         formulario.add(conteudo, g); 
 
         add(formulario, BorderLayout.CENTER);; 
    } 
    

    public void AtualizarTabela(List<GestaoFerias> lista) {

        modelo.setRowCount(0);

        for (GestaoFerias gestaoFerias : lista) {
            modelo.addRow(new Object[] {
                gestaoFerias.getDepartamento(),
                gestaoFerias.getAteDozeMeses(),
                gestaoFerias.getDozeADezoitoMeses(),
                gestaoFerias.getMaisDezoitoMeses()

            });
        }
        
    }

    public JTable getTabela() {
        return tabela;
    }

    public void setTabela(JTable tabela) {
        this.tabela = tabela;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }
} 

