package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import view.Cores;
import view.funcaoFacilitar;

public class TelaDependentes extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextField nome = new JTextField(30);

    private final JComboBox<String> parentesco = new JComboBox<>(new String[]{
        "Selecione",
        "Cônjuge",
        "Filho",
        "Filha",
        "Pai",
        "Mãe",
        "Outro"
    });

    private final JTextField nascimento = new JTextField(15);
    private final JTextField assistencia = new JTextField(30);

    public TelaDependentes() {
        setLayout(new BorderLayout());
        setBackground(Cores.FUNDO);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        montar();
    }

    private void montar() {
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(Cores.FUNDO);

        // Borda estilizada com título
        TitledBorder bordaTitulo = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true),
            " Cadastro de Dependente "
        );
        bordaTitulo.setTitleFont(new Font("SansSerif", Font.BOLD, 14));
        bordaTitulo.setTitleColor(Cores.TEXTO_TITULO);
        formulario.setBorder(BorderFactory.createCompoundBorder(
            bordaTitulo,
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8, 8, 8, 8);

        // Adicionando as linhas com funcaoFacilitar
        funcaoFacilitar.adicionarLinha(formulario, g, 0, "Beneficiário:", nome);
        funcaoFacilitar.adicionarLinha(formulario, g, 1, "Parentesco:", parentesco);
        funcaoFacilitar.adicionarLinha(formulario, g, 2, "Nascimento:", nascimento);
        funcaoFacilitar.adicionarLinha(formulario, g, 3, "Assistência:", assistencia);

        // Tabela de dependentes
        String[] colunas = {
            "Beneficiário",
            "Parentesco",
            "Nascimento",
            "Assistência"
        };

        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(modelo);
        funcaoFacilitar.estilizarTabela(tabela);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.getViewport().setBackground(Cores.FUNDO);
        scroll.setBorder(BorderFactory.createLineBorder(Cores.BORDA));

        // Botões estilizados
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        botoes.setBackground(Cores.FUNDO);

        JButton btnNovo = funcaoFacilitar.criarBotao("Novo", Cores.BOTAO_NOVO);
        JButton btnSalvar = funcaoFacilitar.criarBotao("Salvar", Cores.BOTAO_SALVAR);
        JButton btnExcluir = funcaoFacilitar.criarBotao("Excluir", Cores.BOTAO_EXCLUIR);
        JButton btnLimpar = funcaoFacilitar.criarBotao("Limpar", Cores.BOTAO_LIMPAR);

        botoes.add(btnNovo);
        botoes.add(btnSalvar);
        botoes.add(btnExcluir);
        botoes.add(btnLimpar);

        // Painel central unindo formulário, tabela e botões
        JPanel conteudo = new JPanel(new BorderLayout(0, 15));
        conteudo.setBackground(Cores.FUNDO);

        conteudo.add(formulario, BorderLayout.NORTH);
        conteudo.add(scroll, BorderLayout.CENTER);
        conteudo.add(botoes, BorderLayout.SOUTH);

        add(conteudo, BorderLayout.CENTER);
    }
}