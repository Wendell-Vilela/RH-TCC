package View;

import javax.swing.*;
import java.awt.*;

public class ViewTaxaTurnover extends JPanel {

    public ViewTaxaTurnover() {

        setBackground(Color.BLACK);
        setLayout(new BorderLayout(20, 20));

        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 40));

        // TÍTULO

        JLabel titulo = new JLabel("TAXA DE TURNOVER");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(Color.BLACK);

        topo.add(titulo, BorderLayout.NORTH);

        JSeparator linha = new JSeparator();
        linha.setForeground(Color.WHITE);

        topo.add(linha, BorderLayout.SOUTH);

        add(topo, BorderLayout.NORTH);

        // CENTRO

        JPanel centro = new JPanel(new GridLayout(1, 2));
        centro.setBackground(Color.BLACK);

        centro.add(new GraficoPizza());

        // LEGENDA

        JPanel legenda = new JPanel();
        legenda.setBackground(Color.BLACK);
        legenda.setLayout(new BoxLayout(legenda, BoxLayout.Y_AXIS));

        legenda.add(Box.createVerticalStrut(120));

        legenda.add(criarLegenda("■  Voluntário (1,2%)", Color.WHITE));
        legenda.add(Box.createVerticalStrut(15));

        legenda.add(criarLegenda("■  Involuntário (2,8%)", Color.GRAY));
        legenda.add(Box.createVerticalStrut(15));

        legenda.add(criarLegenda(
                "■  Estabilidade (96,0%)",
                new Color(70, 70, 70)
        ));

        centro.add(legenda);

        add(centro, BorderLayout.CENTER);

        // RODAPÉ

        JLabel taxa = new JLabel(
                "Taxa global de 4,0%. Monitoramento ativo do setor de Vendas devido ao pico de saída voluntária."
        );

        taxa.setForeground(Color.GRAY);
        taxa.setFont(new Font("Arial", Font.PLAIN, 11));
        taxa.setHorizontalAlignment(SwingConstants.CENTER);

        add(taxa, BorderLayout.SOUTH);
    }

    // LEGENDA

    private JLabel criarLegenda(String texto, Color cor) {

        JLabel label = new JLabel(texto);

        label.setForeground(cor);
        label.setFont(new Font("Arial", Font.PLAIN, 13));

        return label;
    }

    // GRÁFICO DE PIZZA

    private static class GraficoPizza extends JPanel {

        public GraficoPizza() {
            setBackground(Color.BLACK);
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            // Estabilidade
            g.setColor(new Color(35, 35, 35));
            g.fillArc(50, 50, 250, 250, 90, 346);

            // Involuntário
            g.setColor(new Color(100, 100, 100));
            g.fillArc(50, 50, 250, 250, 436, 10);

            // Voluntário
            g.setColor(Color.WHITE);
            g.fillArc(50, 50, 250, 250, 446, 40);
        }
    }
}