package view;

public package view; 
 
import java.awt.*; 
import javax.swing.*; 
import javax.swing.table.DefaultTableCellRenderer; 
import javax.swing.table.DefaultTableModel; 
 
public class GestaoFerias extends JPanel { 
 
    /** 
	 *  
	 */ 
	private static final long serialVersionUID = 1L; 
 
    
	 
 
 
    
 
    public GestaoFerias() { 
        setLayout(new BorderLayout()); 
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); 
        setBackground(Color.BLACK); 
 
         String[]colunas ={ 
        "Departamento", 
        "Até 12 meses", 
        "De 12-18 meses", 
        "Risco (+18 meses)" 
 
        }; 
 
         Object[][] dados = { 
            {"Operações", 240, 42, 12}, 
            {"Comercial", 110, 15, 3}, 
            {"Financeiro", 45, 2, 0}, 
            {"Tecnologia", 88, 8, 1} 
        }; 
 
 
        DefaultTableModel modelo = new DefaultTableModel(dados, colunas) { 
 
       @Override 
        public boolean isCellEditable(int row, int column) { 
            return false; 
        } 
        }; 
     
        JTable tabela = new JTable(modelo); 
        tabela.setBackground(Color.BLACK); 
        tabela.setForeground(Color.WHITE); 
        
        tabela.setRowHeight(40);

        tabela.setGridColor(Color.WHITE); 
        tabela.getTableHeader().setBackground(Color.BLACK); 
        tabela.getTableHeader().setForeground(Color.WHITE); 

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
 
            label.setBackground(Color.BLACK); 
            label.setForeground(Color.WHITE); 
            label.setOpaque(true); 
            
            label.setFont(label.getFont().deriveFont(Font.BOLD));
            label.setBorder( 
                BorderFactory.createLineBorder(Color.WHITE) 
            ); 
 
            label.setHorizontalAlignment(JLabel.CENTER); 
 
            return label; 
        } 
    } 
); 
 
        tabela.setSelectionBackground(Color.DARK_GRAY); 
        tabela.setSelectionForeground(Color.WHITE); 
        DefaultTableCellRenderer centralizar = new DefaultTableCellRenderer(); 
 
        centralizar.setHorizontalAlignment(JLabel.CENTER); 
 
        for (int i = 0; i < tabela.getColumnCount(); i++) { 
            tabela.getColumnModel() 
                .getColumn(i) 
                .setCellRenderer(centralizar); 
        } 
        tabela.getTableHeader().setReorderingAllowed(false); 
 
        JScrollPane scrollpane = new JScrollPane(tabela); 
 
        scrollpane.setBackground(Color.BLACK); 
        scrollpane.getViewport().setBackground(Color.BLACK); 
 
        montar(scrollpane); 
    } 
 
    private void montar(JScrollPane scrollpane) { 
        JPanel formulario = new JPanel(new GridBagLayout()); 
        formulario.setBackground(Color.BLACK); 
        formulario.setBorder( 
        BorderFactory.createTitledBorder( 
        BorderFactory.createLineBorder(Color.WHITE), 
        "Gestão de férias", 
        0, 
        0, 
        null, 
        Color.WHITE 
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
        conteudo.setBackground(Color.BLACK); 
        conteudo.add(scrollpane, BorderLayout.CENTER); 
         
 
         formulario.add(conteudo, g); 
 
         add(formulario, BorderLayout.CENTER);; 
    } 
 
    private void componente( 
        JPanel painel, 
        GridBagConstraints g, 
        int linha, 
        String texto, 
        Component campo 
    ) { 
        g.gridx = 0; 
        g.gridy = linha; 
        g.weightx = 0; 
        g.fill = GridBagConstraints.NONE; 
        painel.add(new JLabel(texto), g); 
 
        g.gridx = 1; 
        g.weightx = 1; 
        g.fill = GridBagConstraints.HORIZONTAL; 
        painel.add(campo, g); 
    } 
} 

