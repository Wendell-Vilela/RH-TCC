package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import view.Cores;

public class TelaRotatividade extends JPanel {

    private static final long serialVersionUID = 1L;

    public TelaRotatividade() {
        setLayout(new BorderLayout(10, 15));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(Cores.FUNDO);

        // Cabeçalho
        JLabel lblTitulo = new JLabel("MÓDULO II: TURNOVER E ABSENTEÍSMO");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitulo.setForeground(Cores.TEXTO_TITULO);
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
        painelAlerta.setBackground(Cores.FUNDO);
        
        TitledBorder bordaAlerta = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true),
            " Alerta do Sistema "
        );
        bordaAlerta.setTitleFont(new Font("SansSerif", Font.BOLD, 12));
        bordaAlerta.setTitleColor(Cores.TEXTO_TITULO);
        
        painelAlerta.setBorder(BorderFactory.createCompoundBorder(
            bordaAlerta,
            new EmptyBorder(8, 12, 8, 12)
        ));
        painelAlerta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));

        JLabel lblAlertaTexto = new JLabel("Operações acima da meta de absenteísmo (≤ 3,5%).");
        lblAlertaTexto.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblAlertaTexto.setForeground(Cores.TEXTO_TITULO);

        painelAlerta.add(lblAlertaTexto, BorderLayout.CENTER);
        painelCentral.add(painelAlerta);

        add(painelCentral, BorderLayout.CENTER);
    }

    private JPanel criarCardGraficoLinha() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Cores.FUNDO);
        
        TitledBorder bordaCard = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true),
            " Turnover Mensal "
        );
        bordaCard.setTitleFont(new Font("SansSerif", Font.BOLD, 13));
        bordaCard.setTitleColor(Cores.TEXTO_TITULO);

        card.setBorder(BorderFactory.createCompoundBorder(
            bordaCard,
            new EmptyBorder(10, 10, 10, 10)
        ));

        // Painel para desenhar o gráfico de linha
        JPanel grafico = new JPanel() {
            private static final long serialVersionUID = 1L;

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
                g2.setColor(Cores.BORDA);
                g2.drawLine(30, 20, 360, 20);
                g2.drawLine(30, 65, 360, 65);
                g2.drawLine(30, 110, 360, 110);

                // Rótulos do eixo Y
                g2.setFont(new Font("SansSerif", Font.PLAIN, 10));
                g2.setColor(Cores.TEXTO_TITULO);
                g2.drawString("15%", 5, 24);
                g2.drawString("10%", 5, 69);
                g2.drawString("0%", 5, 114);

                // Linha do gráfico
                g2.setColor(Cores.TEXTO_TITULO);
                g2.setStroke(new BasicStroke(1.5f));
                for (int i = 0; i < xPoints.length - 1; i++) {
                    g2.drawLine(xPoints[i], yPoints[i], xPoints[i + 1], yPoints[i + 1]);
                }

                // Pontos e textos
                for (int i = 0; i < xPoints.length; i++) {
                    g2.setColor(Cores.TEXTO_TITULO);
                    g2.fillOval(xPoints[i] - 3, yPoints[i] - 3, 7, 7);

                    g2.setFont(new Font("SansSerif", Font.BOLD, 10));
                    g2.setColor(Cores.AZUL_MENU);
                    g2.drawString(valores[i], xPoints[i] - 12, yPoints[i] - 10);

                    g2.setFont(new Font("SansSerif", Font.PLAIN, 10));
                    g2.setColor(Cores.TEXTO_TITULO);
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
        card.setBackground(Cores.FUNDO);
        
        TitledBorder bordaCard = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true),
            " Absenteísmo por Área "
        );
        bordaCard.setTitleFont(new Font("SansSerif", Font.BOLD, 13));
        bordaCard.setTitleColor(Cores.TEXTO_TITULO);

        card.setBorder(BorderFactory.createCompoundBorder(
            bordaCard,
            new EmptyBorder(10, 10, 10, 10)
        ));

        // Painel para desenhar o gráfico de barras
        JPanel grafico = new JPanel() {
            private static final long serialVersionUID = 1L;

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
                g2.setColor(Cores.BORDA);
                g2.drawLine(30, 20, 360, 20);
                g2.drawLine(30, 65, 360, 65);
                g2.drawLine(30, 110, 360, 110);

                // Rótulos do eixo Y
                g2.setFont(new Font("SansSerif", Font.PLAIN, 10));
                g2.setColor(Cores.TEXTO_TITULO);
                g2.drawString("6%", 10, 24);
                g2.drawString("4%", 10, 69);
                g2.drawString("0%", 10, 114);

                // Desenho das barras
                for (int i = 0; i < xBarras.length; i++) {
                    g2.setColor(Cores.TEXTO_TITULO);
                    g2.fillRect(xBarras[i], yBarras[i], 30, alturas[i]);

                    // Valor acima da barra de Operações em destaque (roxo padrão)
                    if (i == 1) {
                        g2.setFont(new Font("SansSerif", Font.BOLD, 10));
                        g2.setColor(Cores.AZUL_MENU);
                    } else {
                        g2.setFont(new Font("SansSerif", Font.PLAIN, 10));
                        g2.setColor(Cores.TEXTO_TITULO);
                    }
                    g2.drawString(valores[i], xBarras[i] + 2, yBarras[i] - 8);

                    // Legenda eixo X
                    g2.setFont(new Font("SansSerif", Font.PLAIN, 10));
                    g2.setColor(Cores.TEXTO_TITULO);
                    g2.drawString(categorias[i], xBarras[i] - 2, 130);
                }
            }
        };
        grafico.setOpaque(false);
        card.add(grafico, BorderLayout.CENTER);

        return card;
    }
}