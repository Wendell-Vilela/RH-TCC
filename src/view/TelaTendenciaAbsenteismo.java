package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import model.TendenciaAbsenteismo;

public class TelaTendenciaAbsenteismo extends JPanel {

    private static final long serialVersionUID = 1L;

    private List<TendenciaAbsenteismo> dados;
    private GraphPanel grafico;

    public TelaTendenciaAbsenteismo() {

        this.dados = new ArrayList<>();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(Color.WHITE);

        montar();
    }

    private void montar() {

        JPanel formulario = new JPanel(new GridBagLayout());

        formulario.setBackground(Color.WHITE);

        formulario.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Tendência de Absenteísmo",
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

        grafico = new GraphPanel(dados);

        formulario.add(grafico, g);

        add(formulario, BorderLayout.CENTER);
    }

    public void atualizarGrafico(List<TendenciaAbsenteismo> novosDados) {

        if (novosDados == null) {
            this.dados = new ArrayList<>();
        } else {
            this.dados = novosDados;
        }

        grafico.setDados(this.dados);
    }

    public GraphPanel getGrafico() {
        return grafico;
    }

    public void setGrafico(GraphPanel grafico) {
        this.grafico = grafico;
    }
}


class GraphPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private int padding = 25;
    private int labelPadding = 25;

    private Color lineColor = Color.BLACK;
    private Color pointColor = Color.BLACK;
    private Color gridColor = new Color(50, 50, 50);

    private static final Stroke GRAPH_STROKE =
        new BasicStroke(2f);

    private int pointWidth = 6;
    private int numberYDivisions = 5;

    private List<TendenciaAbsenteismo> dados;

    private String[] meses = {
        "Jan",
        "Fev",
        "Mar",
        "Abr",
        "Mai",
        "Jun",
        "Jul",
        "Ago",
        "Set",
        "Out",
        "Nov",
        "Dez"
    };

    public GraphPanel(List<TendenciaAbsenteismo> dados) {

        this.dados = dados;

        setPreferredSize(new Dimension(600, 300));
        setBackground(Color.WHITE);
    }

    public void setDados(List<TendenciaAbsenteismo> dados) {

        if (dados == null) {
            this.dados = new ArrayList<>();
        } else {
            this.dados = dados;
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (dados == null || dados.isEmpty()) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        double xScale;

        if (dados.size() > 1) {

            xScale =
                ((double) getWidth()
                - (2 * padding)
                - labelPadding)
                / (dados.size() - 1);

        } else {

            xScale = 0;
        }

        double maxScore = getMaxPercentual();
        double minScore = getMinPercentual();

        if (maxScore == minScore) {

            maxScore += 1.0;
            minScore -= 1.0;
        }

        double yScale =
            ((double) getHeight()
            - 2 * padding
            - labelPadding)
            / (maxScore - minScore);

        List<Point> graphPoints = new ArrayList<>();

        for (int i = 0; i < dados.size(); i++) {

            double percentual =
                dados.get(i).getPercentual();

            int x =
                (int) (
                    i * xScale
                    + padding
                    + labelPadding
                );

            int y =
                (int) (
                    (maxScore - percentual)
                    * yScale
                    + padding
                );

            graphPoints.add(new Point(x, y));
        }

        // Área branca do gráfico

        g2.setColor(Color.WHITE);

        g2.fillRect(
            padding + labelPadding,
            padding,
            getWidth() - (2 * padding) - labelPadding,
            getHeight() - 2 * padding - labelPadding
        );

        // Linhas horizontais e valores do eixo Y

        for (int i = 0; i <= numberYDivisions; i++) {

            int x0 =
                padding + labelPadding;

            int x1 =
                pointWidth + padding + labelPadding;

            int y0 =
                getHeight()
                - (
                    (
                        i
                        * (
                            getHeight()
                            - padding * 2
                            - labelPadding
                        )
                    )
                    / numberYDivisions
                    + padding
                    + labelPadding
                );

            int y1 = y0;

            // Grade

            g2.setColor(gridColor);

            g2.drawLine(
                padding + labelPadding + 1 + pointWidth,
                y0,
                getWidth() - padding,
                y1
            );

            // Valor do eixo Y

            g2.setColor(Color.BLACK);

            double value =
                minScore
                + (
                    (maxScore - minScore)
                    * i
                    / numberYDivisions
                );

            String yLabel =
                String.format(
                    Locale.US,
                    "%.1f%%",
                    value
                );

            FontMetrics metrics =
                g2.getFontMetrics();

            int labelWidth =
                metrics.stringWidth(yLabel);

            g2.drawString(
                yLabel,
                x0 - labelWidth - 5,
                y0 + (metrics.getHeight() / 2) - 3
            );

            g2.drawLine(
                x0,
                y0,
                x1,
                y1
            );
        }

        // Meses e linhas verticais

        for (int i = 0; i < dados.size(); i++) {

            if (dados.size() > 1) {

                int x0 =
                    i
                    * (
                        getWidth()
                        - padding * 2
                        - labelPadding
                    )
                    / (dados.size() - 1)
                    + padding
                    + labelPadding;

                int x1 = x0;

                int y0 =
                    getHeight()
                    - padding
                    - labelPadding;

                int y1 =
                    y0 - pointWidth;

                // Linha vertical da grade

                g2.setColor(gridColor);

                g2.drawLine(
                    x0,
                    getHeight()
                    - padding
                    - labelPadding
                    - 1
                    - pointWidth,
                    x1,
                    padding
                );

                // Nome do mês

                g2.setColor(Color.BLACK);

                String xLabel;

                int mes =
                    dados.get(i).getMes();

                if (mes >= 1 && mes <= 12) {

                    xLabel =
                        meses[mes - 1];

                } else if (i < meses.length) {

                    xLabel =
                        meses[i];

                } else {

                    xLabel =
                        String.valueOf(mes);
                }

                FontMetrics metrics =
                    g2.getFontMetrics();

                int labelWidth =
                    metrics.stringWidth(xLabel);

                g2.drawString(
                    xLabel,
                    x0 - labelWidth / 2,
                    y0 + metrics.getHeight() + 3
                );

                g2.drawLine(
                    x0,
                    y0,
                    x1,
                    y1
                );
            }
        }

        // Eixos

        g2.setColor(Color.BLACK);

        g2.drawLine(
            padding + labelPadding,
            getHeight() - padding - labelPadding,
            padding + labelPadding,
            padding
        );

        g2.drawLine(
            padding + labelPadding,
            getHeight() - padding - labelPadding,
            getWidth() - padding,
            getHeight() - padding - labelPadding
        );

        // Linha do gráfico

        Stroke oldStroke =
            g2.getStroke();

        g2.setColor(lineColor);

        g2.setStroke(GRAPH_STROKE);

        for (int i = 0; i < graphPoints.size() - 1; i++) {

            int x1 =
                graphPoints.get(i).x;

            int y1 =
                graphPoints.get(i).y;

            int x2 =
                graphPoints.get(i + 1).x;

            int y2 =
                graphPoints.get(i + 1).y;

            g2.drawLine(
                x1,
                y1,
                x2,
                y2
            );
        }

        // Pontos

        g2.setStroke(oldStroke);

        g2.setColor(pointColor);

        for (Point point : graphPoints) {

            int x =
                point.x - pointWidth / 2;

            int y =
                point.y - pointWidth / 2;

            g2.fillOval(
                x,
                y,
                pointWidth,
                pointWidth
            );
        }
    }

    private double getMinPercentual() {

        double min =
            Double.MAX_VALUE;

        for (TendenciaAbsenteismo dado : dados) {

            min =
                Math.min(
                    min,
                    dado.getPercentual()
                );
        }

        return min == Double.MAX_VALUE
            ? 0
            : min;
    }

    private double getMaxPercentual() {

        double max =
            Double.NEGATIVE_INFINITY;

        for (TendenciaAbsenteismo dado : dados) {

            max =
                Math.max(
                    max,
                    dado.getPercentual()
                );
        }

        return max == Double.NEGATIVE_INFINITY
            ? 10
            : max;
    }

    public String[] getMeses() {
        return meses;
    }

    public void setMeses(String[] meses) {
        this.meses = meses;
    }
}
