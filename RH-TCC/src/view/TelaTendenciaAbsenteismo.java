package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.TendenciaAbsenteismo;

public class TelaTendenciaAbsenteismo extends JPanel {

    private static final long serialVersionUID = 1L;

    private List<TendenciaAbsenteismo> dados;
    private GraphPanel grafico;

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public TelaTendenciaAbsenteismo() {

        dados = new ArrayList<>();
        
        // DADOS FICTICIOS
        TendenciaAbsenteismo janeiro = new TendenciaAbsenteismo();
        janeiro.setMes(1);
        janeiro.setPercentual(2.1);
        dados.add(janeiro);

        TendenciaAbsenteismo fevereiro = new TendenciaAbsenteismo();
        fevereiro.setMes(2);
        fevereiro.setPercentual(2.8);
        dados.add(fevereiro);

        TendenciaAbsenteismo marco = new TendenciaAbsenteismo();
        marco.setMes(3);
        marco.setPercentual(2.4);
        dados.add(marco);

        TendenciaAbsenteismo abril = new TendenciaAbsenteismo();
        abril.setMes(4);
        abril.setPercentual(3.2);
        dados.add(abril);

        TendenciaAbsenteismo maio = new TendenciaAbsenteismo();
        maio.setMes(5);
        maio.setPercentual(2.7);
        dados.add(maio);

        TendenciaAbsenteismo junho = new TendenciaAbsenteismo();
        junho.setMes(6);
        junho.setPercentual(3.5);
        dados.add(junho);

        TendenciaAbsenteismo julho = new TendenciaAbsenteismo();
        julho.setMes(7);
        julho.setPercentual(3.1);
        dados.add(julho);

        TendenciaAbsenteismo agosto = new TendenciaAbsenteismo();
        agosto.setMes(8);
        agosto.setPercentual(3.8);
        dados.add(agosto);

        TendenciaAbsenteismo setembro = new TendenciaAbsenteismo();
        setembro.setMes(9);
        setembro.setPercentual(3.3);
        dados.add(setembro);

        TendenciaAbsenteismo outubro = new TendenciaAbsenteismo();
        outubro.setMes(10);
        outubro.setPercentual(4.1);
        dados.add(outubro);

        TendenciaAbsenteismo novembro = new TendenciaAbsenteismo();
        novembro.setMes(11);
        novembro.setPercentual(3.6);
        dados.add(novembro);

        TendenciaAbsenteismo dezembro = new TendenciaAbsenteismo();
        dezembro.setMes(12);
        dezembro.setPercentual(4.3);
        dados.add(dezembro);
        
        // DADOS FICTICIOS

        setLayout(new BorderLayout());

        setBorder(
            BorderFactory.createEmptyBorder(
                15,
                15,
                15,
                15
            )
        );

        setBackground(Color.WHITE);

        montar();
    }

    private void montar() {

        JPanel painelGrafico =
            new JPanel(new BorderLayout());

        painelGrafico.setBackground(
            Color.WHITE
        );

        painelGrafico.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(
                    Color.BLACK
                ),
                "Tendência de Absenteísmo"
            )
        );

        grafico =
            new GraphPanel(dados);

        painelGrafico.add(
            grafico,
            BorderLayout.CENTER
        );

        JPanel painelTabela =
            new JPanel(new BorderLayout());

        painelTabela.setBackground(
            Color.WHITE
        );

        painelTabela.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(
                    Color.BLACK
                ),
                "Absenteísmo por Funcionário"
            )
        );

        String[] colunas = {
            "ID",
            "Nome",
            "Faltas",
            "Taxa de Absenteísmo"
        };

        modeloTabela =
            new DefaultTableModel(
                colunas,
                0
            ) {

                private static final long serialVersionUID = 1L;

                @Override
                public boolean isCellEditable(
                    int row,
                    int column
                ) {
                    return false;
                }
            };

        tabela =
            new JTable(modeloTabela);

        tabela.setRowHeight(35);
        tabela.setBackground(Color.WHITE);
        tabela.setForeground(Color.BLACK);
        tabela.setGridColor(Color.BLACK);
        tabela.setShowGrid(true);
        tabela.setFillsViewportHeight(true);

        tabela.getTableHeader()
              .setReorderingAllowed(false);

        tabela.getTableHeader()
              .setBackground(Color.WHITE);

        tabela.getTableHeader()
              .setForeground(Color.BLACK);

        tabela.getTableHeader()
              .setFont(
                  tabela.getTableHeader()
                        .getFont()
                        .deriveFont(
                            Font.BOLD
                        )
              );

        DefaultTableCellRenderer centralizador =
            new DefaultTableCellRenderer();

        centralizador.setHorizontalAlignment(
            JLabel.CENTER
        );

        tabela.getColumnModel()
              .getColumn(0)
              .setCellRenderer(
                  centralizador
              );

        tabela.getColumnModel()
              .getColumn(2)
              .setCellRenderer(
                  centralizador
              );

        tabela.getColumnModel()
              .getColumn(3)
              .setCellRenderer(
                  centralizador
              );

        tabela.getColumnModel()
              .getColumn(0)
              .setPreferredWidth(50);

        tabela.getColumnModel()
              .getColumn(1)
              .setPreferredWidth(150);

        tabela.getColumnModel()
              .getColumn(2)
              .setPreferredWidth(70);

        tabela.getColumnModel()
              .getColumn(3)
              .setPreferredWidth(150);

        JScrollPane scrollTabela =
            new JScrollPane(tabela);

        painelTabela.add(
            scrollTabela,
            BorderLayout.CENTER
        );

        JSplitPane divisao =
            new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                painelGrafico,
                painelTabela
            );

        divisao.setResizeWeight(0.5);
        divisao.setDividerSize(5);
        divisao.setContinuousLayout(true);

        add(
            divisao,
            BorderLayout.CENTER
        );
    }

    public void atualizarGrafico(
        List<TendenciaAbsenteismo> novosDados
    ) {

        if (novosDados == null) {
            dados = new ArrayList<>();
        } else {
            dados = novosDados;
        }

        grafico.setDados(dados);
    }

    public void atualizarTabela(
        List<Object[]> funcionarios
    ) {

        modeloTabela.setRowCount(0);

        if (funcionarios == null) {
            return;
        }

        for (
            Object[] funcionario
            : funcionarios
        ) {

            if (
                funcionario == null ||
                funcionario.length < 4
            ) {
                continue;
            }

            Object taxa =
                funcionario[3];

            if (taxa instanceof Number) {

                double valor =
                    ((Number) taxa).doubleValue();

                taxa =
                    String.format(
                        Locale.US,
                        "%.2f%%",
                        valor
                    );
            }

            modeloTabela.addRow(
                new Object[] {
                    funcionario[0],
                    funcionario[1],
                    funcionario[2],
                    taxa
                }
            );
        }

        tabela.revalidate();
        tabela.repaint();
    }

    public void limparTabela() {
        modeloTabela.setRowCount(0);
    }

    public JTable getTabela() {
        return tabela;
    }

    public GraphPanel getGrafico() {
        return grafico;
    }

    public void setGrafico(
        GraphPanel grafico
    ) {
        this.grafico = grafico;
    }
}

class GraphPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private int padding = 25;
    private int labelPadding = 25;

    private Color lineColor =
        Color.BLACK;

    private Color pointColor =
        Color.BLACK;

    private Color gridColor =
        new Color(50, 50, 50);

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

    public GraphPanel(
        List<TendenciaAbsenteismo> dados
    ) {

        this.dados = dados;

        setPreferredSize(
            new Dimension(600, 300)
        );

        setBackground(Color.WHITE);
    }

    public void setDados(
        List<TendenciaAbsenteismo> dados
    ) {

        if (dados == null) {
            this.dados =
                new ArrayList<>();
        } else {
            this.dados = dados;
        }

        repaint();
    }

    @Override
    protected void paintComponent(
        Graphics g
    ) {

        super.paintComponent(g);

        if (
            dados == null ||
            dados.isEmpty()
        ) {

            g.setColor(Color.GRAY);

            g.drawString(
                "Nenhum dado disponível",
                getWidth() / 2 - 70,
                getHeight() / 2
            );

            return;
        }

        Graphics2D g2 =
            (Graphics2D) g;

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        double xScale;

        if (dados.size() > 1) {

            xScale =
                (
                    (double) getWidth()
                    - (2 * padding)
                    - labelPadding
                )
                / (dados.size() - 1);

        } else {

            xScale = 0;
        }

        double maxScore =
            getMaxPercentual();

        double minScore =
            getMinPercentual();

        if (maxScore == minScore) {

            maxScore += 1.0;
            minScore -= 1.0;
        }

        double yScale =
            (
                (double) getHeight()
                - 2 * padding
                - labelPadding
            )
            / (maxScore - minScore);

        List<Point> graphPoints =
            new ArrayList<>();

        for (
            int i = 0;
            i < dados.size();
            i++
        ) {

            double percentual =
                dados.get(i)
                     .getPercentual();

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

            graphPoints.add(
                new Point(x, y)
            );
        }

        g2.setColor(Color.WHITE);

        g2.fillRect(
            padding + labelPadding,
            padding,
            getWidth()
                - (2 * padding)
                - labelPadding,
            getHeight()
                - 2 * padding
                - labelPadding
        );

        for (
            int i = 0;
            i <= numberYDivisions;
            i++
        ) {

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

            g2.setColor(gridColor);

            g2.drawLine(
                padding + labelPadding,
                y0,
                getWidth() - padding,
                y0
            );

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
                padding
                    + labelPadding
                    - labelWidth
                    - 5,
                y0
                    + (
                        metrics.getHeight()
                        / 2
                    )
                    - 3
            );
        }

        for (
            int i = 0;
            i < dados.size();
            i++
        ) {

            if (dados.size() <= 1) {
                continue;
            }

            int x0 =
                i
                * (
                    getWidth()
                    - padding * 2
                    - labelPadding
                )
                / (
                    dados.size() - 1
                )
                + padding
                + labelPadding;

            g2.setColor(gridColor);

            g2.drawLine(
                x0,
                padding,
                x0,
                getHeight()
                    - padding
                    - labelPadding
            );

            g2.setColor(Color.BLACK);

            int mes =
                dados.get(i)
                     .getMes();

            String xLabel;

            if (
                mes >= 1 &&
                mes <= 12
            ) {

                xLabel =
                    meses[mes - 1];

            } else if (
                i < meses.length
            ) {

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
                getHeight()
                    - padding
                    - labelPadding
                    + metrics.getHeight()
                    + 3
            );
        }

        g2.setColor(Color.BLACK);

        g2.drawLine(
            padding + labelPadding,
            getHeight()
                - padding
                - labelPadding,
            padding + labelPadding,
            padding
        );

        g2.drawLine(
            padding + labelPadding,
            getHeight()
                - padding
                - labelPadding,
            getWidth() - padding,
            getHeight()
                - padding
                - labelPadding
        );

        Stroke oldStroke =
            g2.getStroke();

        g2.setColor(lineColor);

        g2.setStroke(GRAPH_STROKE);

        for (
            int i = 0;
            i < graphPoints.size() - 1;
            i++
        ) {

            Point p1 =
                graphPoints.get(i);

            Point p2 =
                graphPoints.get(i + 1);

            g2.drawLine(
                p1.x,
                p1.y,
                p2.x,
                p2.y
            );
        }

        g2.setStroke(oldStroke);

        g2.setColor(pointColor);

        for (
            Point point
            : graphPoints
        ) {

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

        for (
            TendenciaAbsenteismo dado
            : dados
        ) {

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

        for (
            TendenciaAbsenteismo dado
            : dados
        ) {

            max =
                Math.max(
                    max,
                    dado.getPercentual()
                );
        }

        return max ==
            Double.NEGATIVE_INFINITY
            ? 10
            : max;
    }

    public String[] getMeses() {
        return meses;
    }

    public void setMeses(
        String[] meses
    ) {
        this.meses = meses;
    }
}
