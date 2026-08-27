package view;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;



public class TendenciaAbsenteismo extends JPanel {

   
	private static final long serialVersionUID = 1L;
                                                                                                                                                          
    private List<Double> scores;  
    

    public TendenciaAbsenteismo(List<Double> scores) {
        this.scores = scores; 

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(Color.BLACK);
        montar();

                                                                                                                                                       
                                                                                                                                                                        
       
    }

    private void montar() {
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(Color.BLACK);
        formulario.setBorder(
            BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE),
        "Tendência de absenteismo",
        0,
        0,
        null,
        Color.WHITE
    )
      

        );
        

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 5, 5, 5);

        

        g.gridx = 0;
        g.gridy = 0;

        g.weightx = 1;
        g.weighty = 1;

        
        g.fill = GridBagConstraints.BOTH;
        GraphPanel grafico = new GraphPanel(scores);

        formulario.add(grafico, g);
       
        add(formulario, BorderLayout.CENTER);
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

class GraphPanel extends JPanel {
 
    private int padding = 25;
    private int labelPadding = 25;
    private Color lineColor = new Color(255, 255, 255, 255);
    private Color pointColor = new Color(255, 255, 255);
    private Color gridColor = new Color(255, 255, 255);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 4;
    private int numberYDivisions = 10;
    private List<Double> scores;
     private String[] meses = {
    "Jan", "Fev", "Mar", "Abr",
    "Mai", "Jun", "Jul", "Ago",
    "Set", "Out", "Nov", "Dez"
    };
    public GraphPanel(List<Double> scores) {
        this.scores = scores;
    

         setPreferredSize(
            new Dimension(600, 300)
        );
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - scores.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

      
        g2.setColor(Color.BLACK);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.WHITE);

        
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (scores.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.WHITE);
                String yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        for (int i = 0; i < scores.size(); i++) {
            if (scores.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((scores.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.WHITE);
                    String xLabel = meses[i];
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

     
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }



    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Double score : scores) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }

    private double getMaxScore() {
        double maxScore = Double.NEGATIVE_INFINITY;
        for (Double score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    public void setScores(List<Double> scores) {
        this.scores = scores;
        invalidate();
        this.repaint();
    }

    public List<Double> getScores() {
        return scores;
    }
    
    
}