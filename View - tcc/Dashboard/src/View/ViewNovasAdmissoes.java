
package View;

import javax.swing.*;
import java.awt.*;

public class ViewNovasAdmissoes extends JPanel {

    public ViewNovasAdmissoes() {

        setBackground(Color.BLACK);
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 40));

        // TÍTULO

        JLabel titulo = new JLabel("NOVAS ADMISSÕES");

        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(Color.BLACK);

        topo.add(titulo, BorderLayout.NORTH);

        JSeparator linha = new JSeparator();
        linha.setForeground(Color.WHITE);

        topo.add(linha, BorderLayout.SOUTH);

        add(topo, BorderLayout.NORTH);

        // GRÁFICO

        add(new GraficoBarras(), BorderLayout.CENTER);

        // RODAPÉ

        JLabel total = new JLabel(
                "Total acumulado no período: 98 novos colaboradores."
        );

        total.setForeground(Color.GRAY);
        total.setFont(new Font("Arial", Font.PLAIN, 11));

        total.setHorizontalAlignment(SwingConstants.CENTER);

        add(total, BorderLayout.SOUTH);
    }


    // GRÁFICO DE BARRAS

    private static class GraficoBarras extends JPanel {

        public GraficoBarras() {

            setBackground(Color.BLACK);

            UIManager.put("ProgressBar.selectionForeground", Color.BLACK);
            UIManager.put("ProgressBar.selectionBackground", Color.WHITE);

            setLayout(new GridLayout(4, 1, 10, 10));

            JProgressBar barra1 = new JProgressBar(0, 42);
            barra1.setValue(42);
            barra1.setString("Operações - 42 Contratações");
            barra1.setStringPainted(true);
            barra1.setForeground(Color.WHITE);
            barra1.setBackground(new Color(35, 35, 35));

            JProgressBar barra2 = new JProgressBar(0, 42);
            barra2.setValue(30);
            barra2.setString("Vendas - 30 Contratações");
            barra2.setStringPainted(true);
            barra2.setForeground(Color.WHITE);
            barra2.setBackground(new Color(35, 35, 35));

            JProgressBar barra3 = new JProgressBar(0, 42);
            barra3.setValue(18);
            barra3.setString("Tecnologia - 18 Contratações");
            barra3.setStringPainted(true);
            barra3.setForeground(Color.WHITE);
            barra3.setBackground(new Color(35, 35, 35));

            JProgressBar barra4 = new JProgressBar(0, 42);
            barra4.setValue(8);
            barra4.setString("Administrativo - 8 Contratações");
            barra4.setStringPainted(true);
            barra4.setForeground(Color.WHITE);
            barra4.setBackground(new Color(35, 35, 35));

            add(barra1);
            add(barra2);
            add(barra3);
            add(barra4);
        }
    }
        
    }
    


