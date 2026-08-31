
package view;

import javax.swing.*;
import java.awt.*;

public class ViewFuncionariosAtivos extends JPanel {

	private static final long serialVersionUID = 1L;

	public ViewFuncionariosAtivos() {

        setBackground(Color.BLACK);

        setLayout(
                new BorderLayout(20, 20)
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        25, 25, 25, 40
                )
        );

        // =========================
        // TÍTULO
        // =========================

        JLabel titulo =
                new JLabel(
                        "FUNCIONÁRIOS ATIVOS"
                );

        titulo.setForeground(
                Color.WHITE
        );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        JPanel topo =
                new JPanel(
                        new BorderLayout()
                );

        topo.setBackground(
                Color.BLACK
        );

        topo.add(
                titulo,
                BorderLayout.NORTH
        );

        JSeparator linha =
                new JSeparator();

        linha.setForeground(
                Color.WHITE
        );

        topo.add(
                linha,
                BorderLayout.SOUTH
        );

        add(
                topo,
                BorderLayout.NORTH
        );

        // =========================
        // CONTEÚDO
        // =========================

        JPanel centro =
                new JPanel(
                        new GridBagLayout()
                );

        centro.setBackground(
                Color.BLACK
        );

        // Número
        JLabel numero =
                new JLabel("1.240");

        numero.setForeground(
                Color.WHITE
        );

        numero.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        80
                )
        );

        // Headcount
        JLabel headcount =
                new JLabel(
                        "HEADCOUNT TOTAL"
                );

        headcount.setForeground(
                Color.LIGHT_GRAY
        );

        headcount.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        JPanel numeroPanel =
                new JPanel();

        numeroPanel.setBackground(
                Color.BLACK
        );

        numeroPanel.setLayout(
                new BoxLayout(
                        numeroPanel,
                        BoxLayout.Y_AXIS
                )
        );

        numeroPanel.add(numero);
        numeroPanel.add(headcount);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.weightx = 0.45;
        gbc.weighty = 1;

        gbc.anchor =
                GridBagConstraints.CENTER;

        centro.add(
                numeroPanel,
                gbc
        );

        // =========================
        // INFORMAÇÕES
        // =========================

        JPanel informacoes =
                new JPanel();

        informacoes.setBackground(
                Color.BLACK
        );

        informacoes.setLayout(
                new BoxLayout(
                        informacoes,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel efetivos =
                criarTexto(
                        "• Efetivos: 1.150 (92,7%)"
                );

        JLabel terceiros =
                criarTexto(
                        "• Terceirizados: 90 (7,3%)"
                );

        JLabel crescimento =
                criarTexto(
                        "• Crescimento Mensal: +2,1%"
                );

        JLabel descricao =
                criarTexto(
                        "<html>Estabilidade no quadro operacional com foco em expansão da<br>" +
                        "área técnica no próximo trimestre.</html>"
                );

        informacoes.add(
                efetivos
        );

        informacoes.add(
                Box.createVerticalStrut(15)
        );

        informacoes.add(
                terceiros
        );

        informacoes.add(
                Box.createVerticalStrut(15)
        );

        informacoes.add(
                crescimento
        );

        informacoes.add(
                Box.createVerticalStrut(30)
        );

        informacoes.add(
                descricao
        );

        gbc.gridx = 1;
        gbc.weightx = 0.55;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        centro.add(
                informacoes,
                gbc
        );

        add(
                centro,
                BorderLayout.CENTER
        );
    }

    private JLabel criarTexto(
            String texto
    ) {

        JLabel label =
                new JLabel(texto);

        label.setForeground(
                Color.WHITE
        );

        label.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        return label;
    }
}

