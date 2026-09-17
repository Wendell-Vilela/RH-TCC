package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class ViewFuncionariosAtivos extends JPanel {

    private static final long serialVersionUID = 1L;

    public ViewFuncionariosAtivos() {

        setBackground(Cores.FUNDO);
        setLayout(new BorderLayout(20, 20));

        // Borda padronizada com título
        TitledBorder borda = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Cores.BORDA),
                "FUNCIONÁRIOS ATIVOS",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 14),
                Cores.TEXTO_TITULO
        );

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15),
                borda
        ));

        // CONTEÚDO PRINCIPAL

        JPanel centro = new JPanel(new GridBagLayout());
        centro.setBackground(Cores.FUNDO);

        // DESTAQUE DE HEADCOUNT
        JLabel numero = new JLabel("1.240");
        numero.setForeground(Cores.TEXTO_TITULO);
        numero.setFont(new Font("SansSerif", Font.BOLD, 80));

        JLabel headcount = new JLabel("HEADCOUNT TOTAL");
        headcount.setForeground(Cores.CINZA);
        headcount.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JPanel numeroPanel = new JPanel();
        numeroPanel.setBackground(Cores.FUNDO);
        numeroPanel.setLayout(new BoxLayout(numeroPanel, BoxLayout.Y_AXIS));

        numeroPanel.add(numero);
        numeroPanel.add(headcount);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.40;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        centro.add(numeroPanel, gbc);

        //DETALHAMENTO DAS INFORMAÇÕES
        JPanel informacoes = new JPanel();
        informacoes.setBackground(Cores.FUNDO);
        informacoes.setLayout(new BoxLayout(informacoes, BoxLayout.Y_AXIS));

        JLabel efetivos = criarTexto("• Efetivos: 1.150 (92,7%)");
        JLabel terceiros = criarTexto("• Terceirizados: 90 (7,3%)");
        JLabel crescimento = criarTexto("• Crescimento Mensal: +2,1%");
        JLabel turnover = criarTexto("• Taxa de Turnover: 1,2% (Baixa)");
        JLabel tempoMedio = criarTexto("• Tempo Médio de Casa: 3,4 anos");
        JLabel maiorDepto = criarTexto("• Maior Área: Operações (520 colaboradores)");

        JLabel descricao = criarTexto(
                "<html><br><i>Estabilidade no quadro operacional com foco em expansão da<br>" +
                "área técnica no próximo trimestre.</i></html>"
        );
        descricao.setForeground(Cores.CINZA);

        informacoes.add(efetivos);
        informacoes.add(Box.createVerticalStrut(8));
        informacoes.add(terceiros);
        informacoes.add(Box.createVerticalStrut(8));
        informacoes.add(crescimento);
        informacoes.add(Box.createVerticalStrut(8));
        informacoes.add(turnover);
        informacoes.add(Box.createVerticalStrut(8));
        informacoes.add(tempoMedio);
        informacoes.add(Box.createVerticalStrut(8));
        informacoes.add(maiorDepto);
        informacoes.add(Box.createVerticalStrut(15));
        informacoes.add(descricao);

        gbc.gridx = 1;
        gbc.weightx = 0.60;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        centro.add(informacoes, gbc);

        add(centro, BorderLayout.CENTER);

        // RODAPÉ INFORMATIVO

        JPanel rodape = new JPanel(new GridLayout(1, 2, 10, 0));
        rodape.setBackground(Cores.FUNDO);
        rodape.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblAtualizacao = new JLabel("Última atualização do sistema: Hoje às 08:00");
        lblAtualizacao.setForeground(Cores.TEXTO_TITULO);
        lblAtualizacao.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblAtualizacao.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel lblMeta = new JLabel("Meta Q3: 1.300 Funcionários");
        lblMeta.setForeground(Cores.CINZA);
        lblMeta.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblMeta.setHorizontalAlignment(SwingConstants.RIGHT);

        rodape.add(lblAtualizacao);
        rodape.add(lblMeta);

        add(rodape, BorderLayout.SOUTH);
    }

    private JLabel criarTexto(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(Cores.TEXTO_TITULO);
        label.setFont(new Font("SansSerif", Font.PLAIN, 13));
        return label;
    }
}