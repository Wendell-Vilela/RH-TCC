package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class ViewNovasAdmissoes extends JPanel {

    private static final long serialVersionUID = 1L;

    public ViewNovasAdmissoes() {
        setBackground(Cores.FUNDO);
        setLayout(new BorderLayout(20, 20));

        // Borda padronizada com título
        TitledBorder borda = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Cores.BORDA),
                "NOVAS ADMISSÕES",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14),
                Cores.TEXTO_TITULO
        );

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15),
                borda
        ));

        // GRÁFICO DE BARRAS
        add(new GraficoBarras(), BorderLayout.CENTER);

        // RODAPÉ
        JPanel rodape = new JPanel(new BorderLayout());
        rodape.setBackground(Cores.FUNDO);
        rodape.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel total = new JLabel("Total acumulado no período: 98 novos colaboradores.");
        total.setForeground(Cores.TEXTO_TITULO);
        total.setFont(new Font("SansSerif", Font.PLAIN, 12));
        total.setHorizontalAlignment(SwingConstants.CENTER);

        rodape.add(total, BorderLayout.CENTER);

        add(rodape, BorderLayout.SOUTH);
    }

    // GRÁFICO DE BARRAS CUSTOMIZADO
    private static class GraficoBarras extends JPanel {

        private static final long serialVersionUID = 1L;

        public GraficoBarras() {
            setBackground(Cores.FUNDO);

            // Ajuste de contraste para a legibilidade do texto nas barras
            UIManager.put("ProgressBar.selectionForeground", Cores.FUNDO);
            UIManager.put("ProgressBar.selectionBackground", Cores.TEXTO_TITULO);

            setLayout(new GridLayout(4, 1, 15, 15));

            add(criarBarra("Operações - 42 Contratações", 42, 42));
            add(criarBarra("Vendas - 30 Contratações", 30, 42));
            add(criarBarra("Tecnologia - 18 Contratações", 18, 42));
            add(criarBarra("Administrativo - 8 Contratações", 8, 42));
        }

        private JProgressBar criarBarra(String texto, int valor, int maximo) {
            JProgressBar barra = new JProgressBar(0, maximo);
            barra.setValue(valor);
            barra.setString(texto);
            barra.setStringPainted(true);
            barra.setFont(new Font("SansSerif", Font.BOLD, 12));

            // Aplicando o esquema de cores padronizado
            barra.setForeground(Cores.AZUL_MENU);
            barra.setBackground(Cores.BORDA);
            barra.setBorder(BorderFactory.createLineBorder(Cores.BORDA, 1, true));

            return barra;
        }
    }
}