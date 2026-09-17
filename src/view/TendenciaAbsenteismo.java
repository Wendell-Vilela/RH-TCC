package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import view.Cores;

public class TendenciaAbsenteismo extends JPanel {

    private static final long serialVersionUID = 1L;
    private final List<Double> scores;
    private final GraphPanel grafico;

    public TendenciaAbsenteismo() {
        this.scores = new ArrayList<>();
        this.scores.add(1.5);
        this.scores.add(2.0);
        this.scores.add(1.8);
        this.scores.add(2.5);
        this.scores.add(2.1);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(Cores.FUNDO);

        this.grafico = new GraphPanel(scores);
        montar();
    }

    private void montar() {
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(Cores.FUNDO);

        TitledBorder borda = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true),
            " Tendência de Absenteísmo "
        );
        borda.setTitleFont(new Font("SansSerif", Font.BOLD, 14));
        borda.setTitleColor(Cores.TEXTO_TITULO);

        formulario.setBorder(BorderFactory.createCompoundBorder(
            borda,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);
        g.gridx = 0;
        g.gridy = 0;
        g.weightx = 1;
        g.weighty = 1;
        g.fill = GridBagConstraints.BOTH;

        formulario.add(grafico, g);

        add(formulario, BorderLayout.CENTER);
    }

    public void setScores(List<Double> novosScores) {
        this.scores.clear();
        if (novosScores != null) {
            this.scores.addAll(novosScores);
        }
        this.grafico.repaint();
    }
}

class GraphPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final int padding = 25;
    private final int labelPadding = 35;
    private static final Stroke GRAPH_STROKE = new BasicStroke(2.5f);
    private final int pointWidth = 8;
    private final int numberYDivisions = 5;
    private final List<Double> scores;
    private final String[] meses = {
        "Jan", "Fev", "Mar", "Abr", "Mai", "Jun",
        "Jul", "Ago", "Set", "Out", "Nov", "Dez"
    };

    public GraphPanel(List<Double> scores) {
        this.scores = scores;
        setPreferredSize(new Dimension(600, 300));
        setBackground(Cores.FUNDO);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (scores == null || scores.isEmpty()) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = (scores.size() > 1)
            ? ((double) getWidth() - (2 * padding) - labelPadding) / (scores.size() - 1) : 0;

        double maxScore = getMaxScore();
        double minScore = getMinScore();

        if (maxScore == minScore) {
            maxScore += 1.0;
            minScore -= 1.0;
        }

        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (maxScore - minScore);

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((maxScore - scores.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

        // Desenhar linhas de grade e rótulos Y
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);

            g2.setColor(Cores.BORDA);
            g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y0);

            g2.setColor(Cores.TEXTO_TITULO);
            g2.setFont(new Font("SansSerif", Font.PLAIN, 11));
            double value = minScore + ((maxScore - minScore) * i / numberYDivisions);
            String yLabel = String.format(Locale.US, "%.1f%%", value);
            FontMetrics metrics = g2.getFontMetrics();
            int labelWidth = metrics.stringWidth(yLabel);
            g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);

            g2.drawLine(x0, y0, x1, y0);
        }

        // Desenhar linhas verticais e rótulos X (Meses)
        for (int i = 0; i < scores.size(); i++) {
            if (scores.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
                int y0 = getHeight() - padding - labelPadding;

                if (i < meses.length) {
                    g2.setColor(Cores.BORDA);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x0, padding);

                    g2.setColor(Cores.TEXTO_TITULO);
                    g2.setFont(new Font("SansSerif", Font.PLAIN, 11));
                    String xLabel = meses[i];
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x0, y0 - pointWidth);
            }
        }

        // Eixos principais
        g2.setColor(Cores.TEXTO_TITULO);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

        // Desenho da linha do gráfico
        Stroke oldStroke = g2.getStroke();
        g2.setColor(Cores.AZUL_MENU);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        // Desenho dos pontos no gráfico
        g2.setStroke(oldStroke);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;

            g2.setColor(Cores.AZUL_MENU);
            g2.fillOval(x, y, pointWidth, pointWidth);

            // Exibir valor acima do ponto
            g2.setFont(new Font("SansSerif", Font.BOLD, 10));
            String val = String.format(Locale.US, "%.1f%%", scores.get(i));
            FontMetrics metrics = g2.getFontMetrics();
            g2.drawString(val, graphPoints.get(i).x - (metrics.stringWidth(val) / 2), y - 5);
        }
    }

    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Double score : scores) {
            minScore = Math.min(minScore, score);
        }
        return minScore == Double.MAX_VALUE ? 0 : minScore;
    }

    private double getMaxScore() {
        double maxScore = Double.NEGATIVE_INFINITY;
        for (Double score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore == Double.NEGATIVE_INFINITY ? 10 : maxScore;
    }
}