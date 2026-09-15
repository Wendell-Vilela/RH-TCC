package View;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class CustoMensal extends JPanel {

    
	private static final long serialVersionUID = 1L;
    private JLabel investimentoTotallbl;
    private JLabel investimentoTotaltxt;

    private JLabel salarioTotallbl;
    private JLabel salarioTotaltxt;

    private JLabel encargoTotallbl;
    private JLabel encargoTotaltxt;

    private JLabel beneficioTotallbl;
    private JLabel beneficioTotaltxt;

    public CustoMensal() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(Color.WHITE);
        montar();
    }

    private void montar() {
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(Color.WHITE);
        
        Border borda = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.BLACK),
        BorderFactory.createEmptyBorder(20, 20, 20, 20)
    );

        formulario.setBorder(borda);

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 20, 10, 20);

        investimentoTotallbl = new JLabel("Investimento total:");
        investimentoTotaltxt = new JLabel("0.0");

        investimentoTotallbl.setFont(investimentoTotallbl.getFont().deriveFont(20f));
        investimentoTotaltxt.setFont(investimentoTotallbl.getFont().deriveFont(20f));

        investimentoTotallbl.setForeground(Color.BLACK);
        investimentoTotaltxt.setForeground(Color.BLACK);

        salarioTotallbl = new JLabel("Salários:");
        salarioTotaltxt = new JLabel("0.0");

        salarioTotallbl.setFont(salarioTotallbl.getFont().deriveFont(12f));
        salarioTotaltxt.setFont(salarioTotaltxt.getFont().deriveFont(12f));

        salarioTotallbl.setForeground(Color.BLACK);
        salarioTotaltxt.setForeground(Color.BLACK);

        encargoTotallbl = new JLabel("Encargos:");
        encargoTotaltxt = new JLabel("0.0");

        encargoTotallbl.setFont(encargoTotallbl.getFont().deriveFont(12f));
        encargoTotaltxt.setFont(encargoTotallbl.getFont().deriveFont(12f));

        encargoTotallbl.setForeground(Color.BLACK);
        encargoTotaltxt.setForeground(Color.BLACK);

        beneficioTotallbl = new JLabel("Benefícios:");
        beneficioTotaltxt = new JLabel("0.0");

        beneficioTotallbl.setFont(beneficioTotallbl.getFont().deriveFont(12f));
        beneficioTotaltxt.setFont(beneficioTotaltxt.getFont().deriveFont(12f));

        beneficioTotallbl.setForeground(Color.BLACK);
        beneficioTotaltxt.setForeground(Color.BLACK);

        g.anchor = GridBagConstraints.WEST;
        //
        g.gridx = 0;
        g.gridy = 0;
        formulario.add(investimentoTotallbl, g);
   
        g.gridx = 1;
        formulario.add(investimentoTotaltxt, g);
        //
        g.gridx = 0;
        g.gridy = 1;
        formulario.add(salarioTotallbl, g);

        g.gridx = 1;
        formulario.add(salarioTotaltxt, g);
        //
        g.gridx = 0;
        g.gridy = 2;
        formulario.add(encargoTotallbl, g);

        g.gridx = 1;
        formulario.add(encargoTotaltxt, g);

        //
        g.gridx = 0;
        g.gridy = 3;
        formulario.add(beneficioTotallbl, g);

        g.gridx = 1;
        formulario.add(beneficioTotaltxt, g);
        JPanel conteudo = new JPanel(new BorderLayout());
        conteudo.add(formulario, BorderLayout.NORTH);
        

        add(conteudo, BorderLayout.NORTH);
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