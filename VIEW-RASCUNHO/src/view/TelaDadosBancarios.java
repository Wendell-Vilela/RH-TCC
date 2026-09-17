package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import view.Cores;
import view.funcaoFacilitar;

public class TelaDadosBancarios extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextField banco = new JTextField(35);
    private final JTextField codigoBanco = new JTextField(10);
    private final JTextField agencia = new JTextField(15);
    private final JTextField conta = new JTextField(20);
    private final JTextField pix = new JTextField(30);

    private final JComboBox<String> tipoConta = new JComboBox<>(new String[]{
        "Selecione",
        "Conta Corrente",
        "Conta Poupança",
        "Conta Salário"
    });

    public TelaDadosBancarios() {
        setLayout(new GridLayout(1, 1));
        setBackground(Cores.FUNDO);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Criando os JPanels
        JPanel principal = new JPanel();
        JPanel formulario = new JPanel();
        JPanel botoes = new JPanel();

        // Adicionando os layouts
        principal.setLayout(new GridLayout(2, 1, 100, 10));
        botoes.setLayout(new FlowLayout(FlowLayout.LEFT));
        formulario.setLayout(new GridLayout(6, 1, 10, 10));

        // Borda estilizada com título
        TitledBorder bordaTitulo = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Cores.BORDA, 1, true),
            " Dados Bancários "
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
        funcaoFacilitar.adicionarLinha(formulario, g, 0, "Banco:", banco);
        funcaoFacilitar.adicionarLinha(formulario, g, 1, "Código do Banco:", codigoBanco);
        funcaoFacilitar.adicionarLinha(formulario, g, 2, "Agência:", agencia);
        funcaoFacilitar.adicionarLinha(formulario, g, 3, "Conta:", conta);
        funcaoFacilitar.adicionarLinha(formulario, g, 4, "Tipo de Conta:", tipoConta);
        funcaoFacilitar.adicionarLinha(formulario, g, 5, "PIX:", pix);

        // Criando os botões estilizados
        JButton btnNovo = funcaoFacilitar.criarBotao("Novo", Cores.BOTAO_NOVO);
        JButton btnSalvar = funcaoFacilitar.criarBotao("Salvar", Cores.BOTAO_SALVAR);
        JButton btnExcluir = funcaoFacilitar.criarBotao("Excluir", Cores.BOTAO_EXCLUIR);
        JButton btnLimpar = funcaoFacilitar.criarBotao("Limpar", Cores.BOTAO_LIMPAR);

        botoes.add(btnNovo);
        botoes.add(btnSalvar);
        botoes.add(btnExcluir);
        botoes.add(btnLimpar);

        // Configuração dos fundos
        principal.setBackground(Cores.FUNDO);
        botoes.setBackground(Cores.FUNDO);
        formulario.setBackground(Cores.FUNDO);

        // Montagem final do painel
        principal.add(formulario);
        principal.add(botoes, BorderLayout.SOUTH);
        add(principal);
    }
}