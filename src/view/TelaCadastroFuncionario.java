
package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseWheelListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controller.CadastroFuncionarioController;
import model.CadastroFuncionario;
import model.Dependentes;
import model.Historico;

public class TelaCadastroFuncionario extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int LARGURA_ROTULO = 190;

    private final JTextField id = new JTextField(25);
    private final JTextField nome = new JTextField(30);
    private final JTextField matricula = new JTextField(25);
    private final JTextField cargo = new JTextField(30);
    private final JTextField departamento = new JTextField(30);
    private final JTextField email = new JTextField(30);
    private final JTextField telefone = new JTextField(25);

    private final JComboBox<String> status = new JComboBox<>(new String[]{
        "Selecione o status", "Ativo", "Afastado", "Ferias", "Desligado"
    });

    private final JFormattedTextField nascimento = criarCampoData();

    private final JComboBox<String> estadoCivil = new JComboBox<>(new String[]{
        "Selecione", "Solteiro", "Casado", "Divorciado", "Viuvo"
    });

    private final JTextField naturalidade = new JTextField(30);

    private final JComboBox<String> nacionalidade = new JComboBox<>(new String[]{
        "Selecione", "Brasil", "Argentina", "França", "Uruguai"
    });

    private final JTextField endereco = new JTextField(35);
    private final JTextField cidade = new JTextField(30);

    private final JTextField banco = new JTextField(30);
    private final JTextField codigoBanco = new JTextField(20);
    private final JTextField agencia = new JTextField(20);
    private final JTextField conta = new JTextField(25);

    private final JComboBox<String> tipoConta = new JComboBox<>(new String[]{
        "Selecione", "Conta Corrente", "Conta Poupanca", "Conta Salario"
    });

    private final JTextField pix = new JTextField(30);

    private final JTextField cpf = new JTextField(25);
    private final JTextField rg = new JTextField(25);
    private final JTextField passaporte = new JTextField(25);
    private final JFormattedTextField validadePassaporte = criarCampoData();
    private final JTextField pis = new JTextField(25);
    private final JTextField ctps = new JTextField(25);

    private final JTextField dependenteNome = new JTextField(30);

    private final JComboBox<String> dependenteParentesco =
        new JComboBox<>(new String[]{
            "Selecione", "Conjuge", "Filho", "Filha", "Pai", "Mae", "Outro"
        });

    private final JFormattedTextField dependenteNascimento = criarCampoData();
    private final JTextField dependenteAssistencia = new JTextField(30);

    private final DefaultTableModel dependentesModelo = new DefaultTableModel(
        new String[]{"Nome", "Parentesco", "Nascimento", "Assistência"}, 0
    ) {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JTable tabelaDependentes = new JTable(dependentesModelo);

    private final JTextField historicoAno = new JTextField(10);
    private final JTextField historicoEvento = new JTextField(30);
    private final JTextField historicoCargo = new JTextField(30);
    private final JTextArea historicoObservacao = new JTextArea(4, 35);

    private final DefaultTableModel historicoModelo = new DefaultTableModel(
        new String[]{"Ano", "Evento", "Cargo", "Observação"}, 0
    ) {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JTable tabelaHistorico = new JTable(historicoModelo);

    private final JButton salvar = new JButton("Salvar");
    private final JButton excluir = new JButton("Excluir");
    private final JButton limpar = new JButton("Limpar");

    private final JButton adicionarDependente =
        new JButton("Adicionar dependente");

    private final JButton removerDependente =
        new JButton("Remover selecionado");

    private final JButton adicionarHistorico =
        new JButton("Adicionar registro");

    private final JButton removerHistorico =
        new JButton("Remover selecionado");

    private final JLabel identificacao =
        new JLabel("Preencha os dados para iniciar um cadastro.");

    private final CadastroFuncionarioController controller;

    public TelaCadastroFuncionario() {
        setLayout(new BorderLayout(0, 0));
        montarTela();
        controller = new CadastroFuncionarioController(this);
    }

    private JFormattedTextField criarCampoData() {
        try {
            MaskFormatter mascara = new MaskFormatter("##/##/####");
            mascara.setPlaceholderCharacter(' ');

            JFormattedTextField campo = new JFormattedTextField(mascara);
            campo.setColumns(10);

            return campo;

        } catch (ParseException e) {
            throw new IllegalStateException(
                "Erro ao criar campo de data.", e
            );
        }
    }

    private void montarTela() {
        id.setEditable(false);

        historicoObservacao.setLineWrap(true);
        historicoObservacao.setWrapStyleWord(true);

        JTabbedPane abas = new JTabbedPane();

        JPanel telaDados = criarTelaDados();
        JPanel telaComplementos = criarTelaComplementos();

        JScrollPane scrollDados = criarScroll(telaDados);
        JScrollPane scrollComplementos = criarScroll(telaComplementos);

        abas.addTab("Dados do Funcionário", scrollDados);
        abas.addTab("Dependentes e Histórico", scrollComplementos);

        add(criarCabecalho(), BorderLayout.NORTH);
        add(abas, BorderLayout.CENTER);
        add(criarPainelAcoes(), BorderLayout.SOUTH);

        habilitarRolagem(telaDados, scrollDados);
        habilitarRolagem(scrollDados, scrollDados);

        habilitarRolagem(telaComplementos, scrollComplementos);
        habilitarRolagem(scrollComplementos, scrollComplementos);
    }

    private JScrollPane criarScroll(JPanel painel) {
        JScrollPane scroll = new JScrollPane(painel);

        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setWheelScrollingEnabled(true);

        scroll.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        scroll.getVerticalScrollBar().setUnitIncrement(18);

        return scroll;
    }

    private JPanel criarCabecalho() {
        JPanel painel = new JPanel();

        painel.setLayout(
            new javax.swing.BoxLayout(
                painel,
                javax.swing.BoxLayout.Y_AXIS
            )
        );

        painel.setBorder(
            BorderFactory.createEmptyBorder(16, 24, 12, 24)
        );

        JLabel titulo = new JLabel("Gestão de Funcionários");

        titulo.setFont(
            titulo.getFont().deriveFont(Font.BOLD, 21f)
        );

        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        identificacao.setFont(
            identificacao.getFont().deriveFont(Font.PLAIN, 12f)
        );

        identificacao.setBorder(
            BorderFactory.createEmptyBorder(4, 0, 0, 0)
        );

        identificacao.setAlignmentX(Component.LEFT_ALIGNMENT);

        painel.add(titulo);
        painel.add(identificacao);

        return painel;
    }

    private JPanel criarPainelAcoes() {
        JPanel painel = new JPanel(new BorderLayout());

        painel.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(
                    1, 0, 0, 0,
                    UIManager.getColor("Panel.background").darker()
                ),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)
            )
        );

        Dimension tamanho = new Dimension(85, 28);

        salvar.setPreferredSize(tamanho);
        excluir.setPreferredSize(tamanho);
        limpar.setPreferredSize(tamanho);

        JPanel botoes = new JPanel(
            new FlowLayout(FlowLayout.RIGHT, 6, 0)
        );

        botoes.add(salvar);
        botoes.add(excluir);
        botoes.add(limpar);

        painel.add(botoes, BorderLayout.EAST);

        return painel;
    }

    private JPanel criarTelaDados() {
        JPanel painel = criarPainelConteudo();

        adicionarSecao(painel, "1. Cadastro de Funcionário",
            criarCampo("Código", id),
            criarCampo("Nome completo", nome),
            criarCampo("Matrícula", matricula),
            criarCampo("Cargo", cargo),
            criarCampo("Departamento", departamento),
            criarCampo("E-mail", email),
            criarCampo("Telefone", telefone),
            criarCampo("Status", status)
        );

        adicionarSecao(painel, "2. Dados Pessoais",
            criarCampo("Nascimento", nascimento),
            criarCampo("Estado civil", estadoCivil),
            criarCampo("Naturalidade", naturalidade),
            criarCampo("Nacionalidade", nacionalidade),
            criarCampo("Endereço", endereco),
            criarCampo("Cidade", cidade)
        );

        adicionarSecao(painel, "3. Dados Bancários",
            criarCampo("Banco", banco),
            criarCampo("Código do banco", codigoBanco),
            criarCampo("Agência", agencia),
            criarCampo("Conta", conta),
            criarCampo("Tipo de conta", tipoConta),
            criarCampo("PIX", pix)
        );

        adicionarSecao(painel, "4. Documentos",
            criarCampo("CPF", cpf),
            criarCampo("RG", rg),
            criarCampo("Passaporte", passaporte),
            criarCampo("Validade do passaporte", validadePassaporte),
            criarCampo("PIS", pis),
            criarCampo("CTPS", ctps)
        );

        adicionarEspacoFlexivel(painel);

        return painel;
    }

    private JPanel criarTelaComplementos() {
        JPanel painel = criarPainelConteudo();

        adicionarSecao(painel, "5. Dependentes",
            criarCampo("Nome", dependenteNome),
            criarCampo("Parentesco", dependenteParentesco),
            criarCampo("Nascimento", dependenteNascimento),
            criarCampo("Assistência", dependenteAssistencia)
        );

        adicionarTabela(
            painel,
            tabelaDependentes,
            adicionarDependente,
            removerDependente
        );

        adicionarSecao(painel, "6. Histórico",
            criarCampo("Ano", historicoAno),
            criarCampo("Evento", historicoEvento),
            criarCampo("Cargo", historicoCargo),
            criarCampo(
                "Observação",
                new JScrollPane(historicoObservacao)
            )
        );

        adicionarTabela(
            painel,
            tabelaHistorico,
            adicionarHistorico,
            removerHistorico
        );

        adicionarEspacoFlexivel(painel);

        return painel;
    }

    private JPanel criarPainelConteudo() {
        JPanel painel = new JPanel(new GridBagLayout());

        painel.setBorder(
            BorderFactory.createEmptyBorder(20, 30, 22, 30)
        );

        return painel;
    }

    private JPanel criarCampo(String texto, Component campo) {
        JPanel linha = new JPanel(new BorderLayout(12, 0));

        linha.setBorder(
            BorderFactory.createEmptyBorder(1, 0, 3, 0)
        );

        JLabel rotulo = new JLabel(texto);

        rotulo.setPreferredSize(
            new Dimension(LARGURA_ROTULO, 28)
        );

        rotulo.setMinimumSize(
            new Dimension(LARGURA_ROTULO, 28)
        );

        rotulo.setVerticalAlignment(SwingConstants.CENTER);

        linha.add(rotulo, BorderLayout.WEST);
        linha.add(campo, BorderLayout.CENTER);

        linha.setAlignmentX(Component.LEFT_ALIGNMENT);

        return linha;
    }

    private void adicionarSecao(
        JPanel painel,
        String titulo,
        Component... campos
    ) {
        JLabel rotuloTitulo = new JLabel(titulo);

        rotuloTitulo.setFont(
            rotuloTitulo.getFont().deriveFont(Font.BOLD, 15f)
        );

        adicionarComponente(
            painel, rotuloTitulo, 0,
            new Insets(7, 0, 7, 0), 0, 0
        );

        adicionarComponente(
            painel, new JSeparator(), 0,
            new Insets(0, 0, 8, 0), 0, 0
        );

        for (Component campo : campos) {
            adicionarComponente(
                painel, campo, 0,
                new Insets(0, 0, 2, 0), 0, 0
            );
        }

        JPanel espaco = new JPanel();
        espaco.setOpaque(false);

        adicionarComponente(
            painel, espaco, 0,
            new Insets(0, 0, 14, 0), 0, 0
        );
    }

    private void adicionarComponente(
        JPanel painel,
        Component componente,
        int coluna,
        Insets insets,
        double pesoY,
        int preenchimento
    ) {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = coluna;
        gbc.gridy = painel.getComponentCount();
        gbc.weightx = 1;
        gbc.weighty = pesoY;

        gbc.fill = preenchimento == 0
            ? GridBagConstraints.HORIZONTAL
            : GridBagConstraints.BOTH;

        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = insets;
        gbc.gridwidth = 1;

        painel.add(componente, gbc);
    }

    private void adicionarEspacoFlexivel(JPanel painel) {
        JPanel espaco = new JPanel();
        espaco.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = painel.getComponentCount();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        painel.add(espaco, gbc);
    }

    private void adicionarTabela(
        JPanel painel,
        JTable tabela,
        JButton adicionar,
        JButton remover
    ) {
        JPanel botoes = new JPanel(
            new FlowLayout(FlowLayout.LEFT, 8, 2)
        );

        botoes.add(adicionar);
        botoes.add(remover);

        adicionarComponente(
            painel, botoes, 0,
            new Insets(0, 0, 7, 0), 0, 0
        );

        tabela.setFillsViewportHeight(true);
        tabela.setRowHeight(24);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setPreferredSize(new Dimension(500, 135));

        adicionarComponente(
            painel, scroll, 0,
            new Insets(0, 0, 17, 0), 0, 1
        );
    }

    private void habilitarRolagem(
        Component componente,
        JScrollPane scrollPrincipal
    ) {
        MouseWheelListener listener = e -> {
            JScrollBar barra = scrollPrincipal.getVerticalScrollBar();

            if (!barra.isVisible()) {
                return;
            }

            int movimento =
                e.getWheelRotation() * barra.getUnitIncrement() * 2;

            int maximo =
                barra.getMaximum() - barra.getVisibleAmount();

            int novoValor = Math.max(
                barra.getMinimum(),
                Math.min(barra.getValue() + movimento, maximo)
            );

            barra.setValue(novoValor);
            e.consume();
        };

        componente.addMouseWheelListener(listener);

        if (componente instanceof Container) {
            for (Component filho : ((Container) componente).getComponents()) {
                habilitarRolagem(filho, scrollPrincipal);
            }
        }
    }

    public void adicionarDependenteNaTabela() {
        String nomeDependente = texto(dependenteNome);

        if (nomeDependente.isEmpty()) {
            throw new IllegalArgumentException(
                "Informe o nome do dependente."
            );
        }

        dependentesModelo.addRow(new Object[]{
            nomeDependente,
            dependenteParentesco.getSelectedItem(),
            textoData(dependenteNascimento),
            texto(dependenteAssistencia)
        });

        limparDependente();
    }

    public void removerDependenteSelecionado() {
        int linha = tabelaDependentes.getSelectedRow();

        if (linha >= 0) {
            dependentesModelo.removeRow(
                tabelaDependentes.convertRowIndexToModel(linha)
            );
        }
    }

    public void adicionarHistoricoNaTabela() {
        if (texto(historicoAno).isEmpty()
                && texto(historicoEvento).isEmpty()
                && texto(historicoCargo).isEmpty()
                && texto(historicoObservacao).isEmpty()) {
            throw new IllegalArgumentException(
                "Informe pelo menos um dado do histórico."
            );
        }

        historicoModelo.addRow(new Object[]{
            texto(historicoAno),
            texto(historicoEvento),
            texto(historicoCargo),
            texto(historicoObservacao)
        });

        limparHistorico();
    }

    public void removerHistoricoSelecionado() {
        int linha = tabelaHistorico.getSelectedRow();

        if (linha >= 0) {
            historicoModelo.removeRow(
                tabelaHistorico.convertRowIndexToModel(linha)
            );
        }
    }

    private void limparDependente() {
        dependenteNome.setText("");
        dependenteParentesco.setSelectedIndex(0);
        dependenteNascimento.setValue(null);
        dependenteAssistencia.setText("");
    }

    private void limparHistorico() {
        historicoAno.setText("");
        historicoEvento.setText("");
        historicoCargo.setText("");
        historicoObservacao.setText("");
    }

    public void limparFormulario() {
        id.setText("");
        nome.setText("");
        matricula.setText("");
        cargo.setText("");
        departamento.setText("");
        email.setText("");
        telefone.setText("");
        status.setSelectedIndex(0);

        nascimento.setValue(null);
        estadoCivil.setSelectedIndex(0);
        naturalidade.setText("");
        nacionalidade.setSelectedIndex(0);
        endereco.setText("");
        cidade.setText("");

        banco.setText("");
        codigoBanco.setText("");
        agencia.setText("");
        conta.setText("");
        tipoConta.setSelectedIndex(0);
        pix.setText("");

        cpf.setText("");
        rg.setText("");
        passaporte.setText("");
        validadePassaporte.setValue(null);
        pis.setText("");
        ctps.setText("");

        dependentesModelo.setRowCount(0);
        historicoModelo.setRowCount(0);

        limparDependente();
        limparHistorico();

        identificacao.setText(
            "Preencha os dados para iniciar um cadastro."
        );
    }

    public List<String[]> obterDependentes() {
        List<String[]> dados = new ArrayList<>();

        for (int i = 0; i < dependentesModelo.getRowCount(); i++) {
            dados.add(new String[]{
                valor(dependentesModelo, i, 0),
                valor(dependentesModelo, i, 1),
                valor(dependentesModelo, i, 2),
                valor(dependentesModelo, i, 3)
            });
        }

        return dados;
    }

    public List<String[]> obterHistorico() {
        List<String[]> dados = new ArrayList<>();

        for (int i = 0; i < historicoModelo.getRowCount(); i++) {
            dados.add(new String[]{
                valor(historicoModelo, i, 0),
                valor(historicoModelo, i, 1),
                valor(historicoModelo, i, 2),
                valor(historicoModelo, i, 3)
            });
        }

        return dados;
    }

    private String valor(
        DefaultTableModel modelo,
        int linha,
        int coluna
    ) {
        Object valor = modelo.getValueAt(linha, coluna);
        return valor == null ? "" : valor.toString();
    }

    private String texto(JTextField campo) {
        return campo.getText().trim();
    }

    private String texto(JTextArea campo) {
        return campo.getText().trim();
    }

    private String textoData(JTextField campo) {
        String valor = campo.getText();
        String numeros = valor.replaceAll("\\D", "");

        return numeros.length() == 8 ? valor.trim() : "";
    }

    public int getId() {
        return id.getText().trim().isEmpty()
            ? 0
            : Integer.parseInt(id.getText().trim());
    }

    public void setId(int valor) {
        id.setText(String.valueOf(valor));
    }

    public String getNome() {
        return texto(nome);
    }

    public String getMatricula() {
        return texto(matricula);
    }

    public String getCargo() {
        return texto(cargo);
    }

    public String getDepartamento() {
        return texto(departamento);
    }

    public String getEmail() {
        return texto(email);
    }

    public String getTelefone() {
        return texto(telefone);
    }

    public String getStatus() {
        return String.valueOf(status.getSelectedItem());
    }

    public String getNascimento() {
        return textoData(nascimento);
    }

    public String getEstadoCivil() {
        return String.valueOf(estadoCivil.getSelectedItem());
    }

    public String getNaturalidade() {
        return texto(naturalidade);
    }

    public String getNacionalidade() {
        return String.valueOf(nacionalidade.getSelectedItem());
    }

    public String getEndereco() {
        return texto(endereco);
    }

    public String getCidade() {
        return texto(cidade);
    }

    public String getBanco() {
        return texto(banco);
    }

    public String getCodigoBanco() {
        return texto(codigoBanco);
    }

    public String getAgencia() {
        return texto(agencia);
    }

    public String getConta() {
        return texto(conta);
    }

    public String getTipoConta() {
        return String.valueOf(tipoConta.getSelectedItem());
    }

    public String getPix() {
        return texto(pix);
    }

    public String getCpf() {
        return texto(cpf);
    }

    public String getRg() {
        return texto(rg);
    }

    public String getPassaporte() {
        return texto(passaporte);
    }

    public String getValidadePassaporte() {
        return textoData(validadePassaporte);
    }

    public String getPis() {
        return texto(pis);
    }

    public String getCtps() {
        return texto(ctps);
    }

    public JButton getSalvar() {
        return salvar;
    }

    public JButton getExcluir() {
        return excluir;
    }

    public JButton getLimpar() {
        return limpar;
    }

    public void carregarFuncionario(CadastroFuncionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException(
                "Funcionário não informado."
            );
        }

        limparFormulario();

        setId(funcionario.getId());
        nome.setText(valorSeguro(funcionario.getNome()));
        matricula.setText(valorSeguro(funcionario.getMatricula()));
        cargo.setText(valorSeguro(funcionario.getCargo()));
        departamento.setText(valorSeguro(funcionario.getDepartamento()));
        email.setText(valorSeguro(funcionario.getEmail()));
        telefone.setText(valorSeguro(funcionario.getTelefone()));
        selecionarCombo(status, funcionario.getStatus());

        if (funcionario.getDadosPessoais() != null) {
            model.DadosPessoais dados = funcionario.getDadosPessoais();

            setCampoData(nascimento, dados.getNascimento());
            selecionarCombo(estadoCivil, dados.getEstadoCivil());
            naturalidade.setText(valorSeguro(dados.getNaturalidade()));
            selecionarCombo(nacionalidade, dados.getNacionalidade());
            endereco.setText(valorSeguro(dados.getEndereco()));
            cidade.setText(valorSeguro(dados.getCidade()));
        }

        if (funcionario.getDadosBancarios() != null) {
            model.DadosBancarios dados = funcionario.getDadosBancarios();

            banco.setText(valorSeguro(dados.getBanco()));
            codigoBanco.setText(valorSeguro(dados.getCodigoBanco()));
            agencia.setText(valorSeguro(dados.getAgencia()));
            conta.setText(valorSeguro(dados.getConta()));
            selecionarCombo(tipoConta, dados.getTipoConta());
            pix.setText(valorSeguro(dados.getPix()));
        }

        if (funcionario.getDocumentos() != null) {
            model.Documentos dados = funcionario.getDocumentos();

            cpf.setText(valorSeguro(dados.getCpf()));
            rg.setText(valorSeguro(dados.getRg()));
            passaporte.setText(valorSeguro(dados.getPassaporte()));
            setCampoData(validadePassaporte, dados.getValidadePassaporte());
            pis.setText(valorSeguro(dados.getPis()));
            ctps.setText(valorSeguro(dados.getCtps()));
        }

        for (Dependentes dependente : funcionario.getDependentes()) {
            dependentesModelo.addRow(new Object[]{
                valorSeguro(dependente.getNome()),
                valorSeguro(dependente.getParentesco()),
                valorSeguro(dependente.getNascimento()),
                valorSeguro(dependente.getAssistencia())
            });
        }

        for (Historico registro : funcionario.getHistorico()) {
            historicoModelo.addRow(new Object[]{
                valorSeguro(registro.getAno()),
                valorSeguro(registro.getEvento()),
                valorSeguro(registro.getCargo()),
                valorSeguro(registro.getObservacao())
            });
        }

        setIdentificacao(
            "Funcionário: " + valorSeguro(funcionario.getNome())
            + " — código " + funcionario.getId()
        );
    }

    private void selecionarCombo(JComboBox<String> combo, String valor) {
        if (valor != null && !valor.trim().isEmpty()) {
            combo.setSelectedItem(valor);
        }
    }

    private void setCampoData(JFormattedTextField campo, String valor) {
        campo.setText(valorSeguro(valor));
    }

    private String valorSeguro(String valor) {
        return valor == null ? "" : valor;
    }

    public JButton getAdicionarDependente() {
        return adicionarDependente;
    }

    public JButton getRemoverDependente() {
        return removerDependente;
    }

    public JButton getAdicionarHistorico() {
        return adicionarHistorico;
    }

    public JButton getRemoverHistorico() {
        return removerHistorico;
    }

    public void setIdentificacao(String texto) {
        identificacao.setText(texto == null ? "" : texto);
    }

    public CadastroFuncionarioController getController() {
        return controller;
    }
}