package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaRotatividade extends JPanel {

	private static final long serialVersionUID = 1L;

	public TelaRotatividade() {
        setLayout(new BorderLayout(10, 15));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(new Color(245, 246, 248));

        // Cabeçalho
        JLabel lblTitulo = new JLabel("MÓDULO II: TURNOVER E ABSENTEÍSMO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(30, 30, 30));
        add(lblTitulo, BorderLayout.NORTH);

        // Painel Central
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setOpaque(false);

        // 1. Linha dos Gráficos (2 Cards com Gráficos Customizados)
        JPanel painelGraficos = new JPanel(new GridLayout(1, 2, 15, 0));
        painelGraficos.setOpaque(false);
        painelGraficos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 240));

        painelGraficos.add(criarCardGraficoLinha());
        painelGraficos.add(criarCardGraficoBarras());

        painelCentral.add(painelGraficos);
        painelCentral.add(Box.createVerticalStrut(15));

        // 2. Banner de Alerta
        JPanel painelAlerta = new JPanel(new BorderLayout(15, 0));
        painelAlerta.setBackground(Color.WHITE);
        painelAlerta.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
            new EmptyBorder(12, 15, 12, 15)
        ));
        painelAlerta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));

        JLabel lblAlertaTexto = new JLabel("Alerta: Operações acima da meta de absenteísmo (≤ 3,5%).");
        lblAlertaTexto.setFont(new Font("Arial", Font.BOLD, 12));
        lblAlertaTexto.setForeground(new Color(40, 40, 40));

        painelAlerta.add(lblAlertaTexto, BorderLayout.CENTER);
        painelCentral.add(painelAlerta);

        add(painelCentral, BorderLayout.CENTER);
    }

    private JPanel criarCardGraficoLinha() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
            new EmptyBorder(12, 15, 12, 15)
        ));

        JLabel lblTitulo = new JLabel("Turnover mensal");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 12));
        lblTitulo.setForeground(new Color(50, 50, 50));
        card.add(lblTitulo, BorderLayout.NORTH);

        // Painel para desenhar o gráfico de linha
        JPanel grafico = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int[] xPoints = {35, 95, 155, 215, 275, 335};
                int[] yPoints = {55, 70, 75, 105, 75, 80};
                String[] valores = {"10,2%", "9,1%", "8,7%", "7,3%", "8,6%", "8,4%"};
                String[] meses = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun"};

                // Linhas de grade horizontais
                g2.setColor(new Color(240, 240, 240));
                g2.drawLine(30, 20, 360, 20);
                g2.drawLine(30, 65, 360, 65);
                g2.drawLine(30, 110, 360, 110);

                // Rótulos do eixo Y
                g2.setFont(new Font("Arial", Font.PLAIN, 10));
                g2.setColor(new Color(120, 120, 120));
                g2.drawString("15%", 5, 24);
                g2.drawString("10%", 5, 69);
                g2.drawString("0%", 5, 114);

                // Linha do gráfico
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(1.5f));
                for (int i = 0; i < xPoints.length - 1; i++) {
                    g2.drawLine(xPoints[i], yPoints[i], xPoints[i + 1], yPoints[i + 1]);
                }

                // Pontos e textos
                for (int i = 0; i < xPoints.length; i++) {
                    g2.setColor(Color.BLACK);
                    g2.fillOval(xPoints[i] - 3, yPoints[i] - 3, 7, 7);

                    g2.setFont(new Font("Arial", Font.BOLD, 10));
                    g2.setColor(new Color(90, 40, 150));
                    g2.drawString(valores[i], xPoints[i] - 12, yPoints[i] - 10);

                    g2.setFont(new Font("Arial", Font.PLAIN, 10));
                    g2.setColor(new Color(100, 100, 100));
                    g2.drawString(meses[i], xPoints[i] - 8, 130);
                }
            }
        };
        grafico.setOpaque(false);
        card.add(grafico, BorderLayout.CENTER);

        return card;
    }

    private JPanel criarCardGraficoBarras() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
            new EmptyBorder(12, 15, 12, 15)
        ));

        JLabel lblTitulo = new JLabel("Absenteísmo por área");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 12));
        lblTitulo.setForeground(new Color(50, 50, 50));
        card.add(lblTitulo, BorderLayout.NORTH);

        // Painel para desenhar o gráfico de barras
        JPanel grafico = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int[] xBarras = {50, 140, 230, 320};
                int[] yBarras = {75, 25, 60, 90};
                int[] alturas = {35, 85, 50, 20};
                String[] valores = {"2,1%", "4,8%", "2,9%", "1,7%"};
                String[] categorias = {"RH", "Operações", "Comercial", "Financeiro"};

                // Linhas de grade
                g2.setColor(new Color(240, 240, 240));
                g2.drawLine(30, 20, 360, 20);
                g2.drawLine(30, 65, 360, 65);
                g2.drawLine(30, 110, 360, 110);

                // Rótulos do eixo Y
                g2.setFont(new Font("Arial", Font.PLAIN, 10));
                g2.setColor(new Color(120, 120, 120));
                g2.drawString("6%", 10, 24);
                g2.drawString("4%", 10, 69);
                g2.drawString("0%", 10, 114);

                // Desenho das barras
                for (int i = 0; i < xBarras.length; i++) {
                    g2.setColor(Color.BLACK);
                    g2.fillRect(xBarras[i], yBarras[i], 30, alturas[i]);

                    // Valor acima da barra de Operações em destaque (roxo)
                    if (i == 1) {
                        g2.setFont(new Font("Arial", Font.BOLD, 10));
                        g2.setColor(new Color(90, 40, 150));
                    } else {
                        g2.setFont(new Font("Arial", Font.PLAIN, 10));
                        g2.setColor(new Color(50, 50, 50));
                    }
                    g2.drawString(valores[i], xBarras[i] + 2, yBarras[i] - 8);

                    // Legenda eixo X
                    g2.setFont(new Font("Arial", Font.PLAIN, 10));
                    g2.setColor(new Color(100, 100, 100));
                    g2.drawString(categorias[i], xBarras[i] - 2, 130);
                }
            }
        };
        grafico.setOpaque(false);
        card.add(grafico, BorderLayout.CENTER);

        return card;
    }
}
