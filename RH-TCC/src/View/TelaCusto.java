package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaCusto extends JPanel {

	private static final long serialVersionUID = 1L;

	public TelaCusto() {
        setLayout(new BorderLayout(10, 15));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(new Color(245, 246, 248));

        // Cabeçalho
        JLabel lblTitulo = new JLabel("MÓDULO IV: CUSTOS E PROJEÇÕES");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(30, 30, 30));
        add(lblTitulo, BorderLayout.NORTH);

        // Painel Central
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setOpaque(false);

        // 1. Linha superior: Gráfico de Colunas Empilhadas e Gráfico de Linha (Evolução)
        JPanel painelGraficos = new JPanel(new GridLayout(1, 2, 15, 0));
        painelGraficos.setOpaque(false);
        painelGraficos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 220));

        painelGraficos.add(criarCardCustosCategoria());
        painelGraficos.add(criarCardEvolucaoCustos());

        painelCentral.add(painelGraficos);
        painelCentral.add(Box.createVerticalStrut(15));

        // 2. Linha inferior: Cards de Indicadores de Custo (3 Cards)
        JPanel painelCards = new JPanel(new GridLayout(1, 3, 15, 0));
        painelCards.setOpaque(false);
        painelCards.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        painelCards.add(criarCardInfo("Custo por colaborador", "R$ 4.820"));
        painelCards.add(criarCardInfo("Custo treinamento", "R$ 36 mil"));
        painelCards.add(criarCardInfo("Projeção anual", "R$ 2,9 mi"));

        painelCentral.add(painelCards);

        add(painelCentral, BorderLayout.CENTER);
    }

    private JPanel criarCardCustosCategoria() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
            new EmptyBorder(12, 15, 12, 15)
        ));

        JLabel lblTitulo = new JLabel("Custos por categoria");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 12));
        lblTitulo.setForeground(new Color(50, 50, 50));
        card.add(lblTitulo, BorderLayout.NORTH);

        JPanel grafico = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int[] xBarras = {60, 150, 240, 330};
                int[] yRealizado = {70, 105, 125, 135};
                int[] alturaRealizado = {60, 25, 15, 10};
                int[] yPrevisto = {35, 80, 110, 125};
                int[] alturaPrevisto = {35, 25, 15, 10};
                String[] categorias = {"Folha", "Benefícios", "Treinamento", "Recrutamento"};

                // Linhas de grade
                g2.setColor(new Color(240, 240, 240));
                g2.drawLine(30, 20, 380, 20);
                g2.drawLine(30, 65, 380, 65);
                g2.drawLine(30, 110, 380, 110);

                // Rótulos eixo Y
                g2.setFont(new Font("Arial", Font.PLAIN, 10));
                g2.setColor(new Color(120, 120, 120));
                g2.drawString("300", 5, 24);
                g2.drawString("200", 5, 69);
                g2.drawString("100", 5, 114);

                // Barras empilhadas
                for (int i = 0; i < xBarras.length; i++) {
                    // Parte Realizado (Preto)
                    g2.setColor(Color.BLACK);
                    g2.fillRect(xBarras[i], yRealizado[i], 30, alturaRealizado[i]);

                    // Parte Previsto (Cinza claro)
                    g2.setColor(new Color(200, 200, 200));
                    g2.fillRect(xBarras[i], yPrevisto[i], 30, alturaPrevisto[i]);

                    // Nome da categoria eixo X
                    g2.setFont(new Font("Arial", Font.PLAIN, 10));
                    g2.setColor(new Color(100, 100, 100));
                    g2.drawString(categorias[i], xBarras[i] - 4, 150);
                }

                // Legenda inferior interna
                g2.setColor(Color.BLACK);
                g2.fillRect(90, 168, 10, 10);
                g2.setFont(new Font("Arial", Font.PLAIN, 10));
                g2.setColor(new Color(50, 50, 50));
                g2.drawString("Realizado", 105, 177);

                g2.setColor(new Color(200, 200, 200));
                g2.fillRect(190, 168, 10, 10);
                g2.setColor(new Color(50, 50, 50));
                g2.drawString("Previsto", 205, 177);
            }
        };
        grafico.setOpaque(false);
        card.add(grafico, BorderLayout.CENTER);

        return card;
    }

    private JPanel criarCardEvolucaoCustos() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
            new EmptyBorder(12, 15, 12, 15)
        ));

        JLabel lblTitulo = new JLabel("Evolução de custos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 12));
        lblTitulo.setForeground(new Color(50, 50, 50));
        card.add(lblTitulo, BorderLayout.NORTH);

        JPanel grafico = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int[] xPoints = {35, 95, 155, 215, 275, 335};
                int[] yPoints = {90, 80, 70, 68, 55, 45};
                String[] valores = {"180", "195", "210", "215", "235", "248"};
                String[] meses = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun"};

                // Linhas de grade
                g2.setColor(new Color(240, 240, 240));
                g2.drawLine(30, 20, 360, 20);
                g2.drawLine(30, 65, 360, 65);
                g2.drawLine(30, 110, 360, 110);

                // Eixo Y
                g2.setFont(new Font("Arial", Font.PLAIN, 10));
                g2.setColor(new Color(120, 120, 120));
                g2.drawString("300", 5, 24);
                g2.drawString("200", 5, 69);
                g2.drawString("100", 5, 114);
                g2.drawString("0", 12, 134);

                // Linha do gráfico
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(1.5f));
                for (int i = 0; i < xPoints.length - 1; i++) {
                    g2.drawLine(xPoints[i], yPoints[i], xPoints[i + 1], yPoints[i + 1]);
                }

                // Pontos e valores
                for (int i = 0; i < xPoints.length; i++) {
                    g2.setColor(Color.BLACK);
                    g2.fillOval(xPoints[i] - 3, yPoints[i] - 3, 7, 7);

                    g2.setFont(new Font("Arial", Font.BOLD, 10));
                    g2.setColor(new Color(90, 40, 150));
                    g2.drawString(valores[i], xPoints[i] - 10, yPoints[i] - 10);

                    g2.setFont(new Font("Arial", Font.PLAIN, 10));
                    g2.setColor(new Color(100, 100, 100));
                    g2.drawString(meses[i], xPoints[i] - 8, 145);
                }
            }
        };
        grafico.setOpaque(false);
        card.add(grafico, BorderLayout.CENTER);

        return card;
    }

    private JPanel criarCardInfo(String titulo, String valor) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
            new EmptyBorder(12, 12, 12, 12)
        ));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblTitulo.setForeground(new Color(80, 80, 80));

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Arial", Font.BOLD, 18));
        lblValor.setForeground(new Color(20, 20, 20));

        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(10));
        card.add(lblValor);

        return card;
    }
}
