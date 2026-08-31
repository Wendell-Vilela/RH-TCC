package View;

import javax.swing.*;
import java.awt.*;

public class ViewTaxaTurnover extends JPanel {

    public ViewTaxaTurnover() {

        setBackground(Color.WHITE);
        setLayout(new BorderLayout(20, 20));

        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 40));

        // TÍTULO

        JLabel titulo = new JLabel("TAXA DE TURNOVER");
        titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(Color.WHITE);

        topo.add(titulo, BorderLayout.NORTH);

        JSeparator linha = new JSeparator();
        linha.setForeground(Color.BLACK);

        topo.add(linha, BorderLayout.SOUTH);

        add(topo, BorderLayout.NORTH);

        // CENTRO

        JPanel centro = new JPanel(new GridLayout(1, 2));
        centro.setBackground(Color.WHITE);

        centro.add(new GraficoPizza());

     // LEGENDA
        JPanel legenda = new JPanel();
        legenda.setBackground(Color.WHITE);
        legenda.setLayout(new BoxLayout(legenda, BoxLayout.Y_AXIS));

        legenda.add(Box.createVerticalStrut(120));

        // Corrigido: Removido o 'g.setColor(...)' - passamos apenas o objeto Color
        legenda.add(criarLegenda("■  Voluntário (1,2%)", new Color(212, 230, 242)));
        legenda.add(Box.createVerticalStrut(15));

        legenda.add(criarLegenda("■  Involuntário (2,8%)", Color.GRAY));
        legenda.add(Box.createVerticalStrut(15));

        legenda.add(criarLegenda("■  Estabilidade (96,0%)", Color.BLACK));

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
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            // Estabilidade
         // Base Principal (Fatia maior: 346 graus) - Azul Noturno
            g.setColor(Color.BLACK);
            g.fillArc(50, 50, 250, 250, 90, 346);

            // Involuntário (Fatia menor: 10 graus) - Laranja Alerta
            g.setColor(Color.GRAY);
            g.fillArc(50, 50, 250, 250, 436, 10);

            // Voluntário (Fatia média: 40 graus) - Verde Consciente
            g.setColor(new Color(212, 230, 242));
            g.fillArc(50, 50, 250, 250, 446, 40);

        }
    }
}