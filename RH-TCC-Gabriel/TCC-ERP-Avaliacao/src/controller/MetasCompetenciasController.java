package controller;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import view.TelaMetasCompetencias;
import model.Meta;

public class MetasCompetenciasController {

    private TelaMetasCompetencias view;
    private int proximoId = 1;

    public MetasCompetenciasController(TelaMetasCompetencias view) {

        this.view = view;

        view.getNovo().addActionListener(e -> novo());
        view.getSalvar().addActionListener(e -> salvar());
        view.getExcluir().addActionListener(e -> excluir());
        view.getLimpar().addActionListener(e -> limpar());

        novo();
    }

    private void novo() {

        limpar();

        view.getId().setText(String.valueOf(proximoId));
    }

    private void salvar() {

        int id = Integer.parseInt(view.getId().getText());

        String funcionario = view.getFuncionario().getText();
        String cargo = view.getCargo().getText();
        String gestor = view.getGestor().getText();
        String area = view.getArea().getText();
        String metaTexto = view.getMeta().getText();
        String prazo = view.getPrazo().getText();

        String status = (String) view.getStatus().getSelectedItem();

        if (funcionario.isEmpty() || metaTexto.isEmpty()) {

            JOptionPane.showMessageDialog(
                view,
                "Preencha o funcionario e a meta."
            );

            return;
        }

        if (status.equals("Selecione o status")) {

            JOptionPane.showMessageDialog(
                view,
                "Selecione um status."
            );

            return;
        }

        // Cria o objeto Meta
        Meta meta = new Meta(
            id,
            funcionario,
            cargo,
            gestor,
            area,
            metaTexto,
            prazo,
            status
        );

        // Pega o modelo da tabela
        DefaultTableModel modelo =
            (DefaultTableModel) view.getTabela().getModel();

        // Adiciona os dados da Meta na tabela
        modelo.addRow(new Object[] {
            meta.getId(),
            meta.getFuncionario(),
            meta.getCargo(),
            meta.getGestor(),
            meta.getArea(),
            meta.getMeta(),
            meta.getPrazo(),
            meta.getStatus()
        });

        proximoId++;

        JOptionPane.showMessageDialog(
            view,
            "Meta cadastrada com sucesso!"
        );

        novo();
    }

    private void excluir() {

        int linha = view.getTabela().getSelectedRow();

        if (linha == -1) {

            JOptionPane.showMessageDialog(
                view,
                "Selecione uma meta na tabela."
            );

            return;
        }

        DefaultTableModel modelo =
            (DefaultTableModel) view.getTabela().getModel();

        modelo.removeRow(linha);

        JOptionPane.showMessageDialog(
            view,
            "Meta excluida com sucesso!"
        );

        novo();
    }

    private void limpar() {

        view.getFuncionario().setText("");
        view.getCargo().setText("");
        view.getGestor().setText("");
        view.getArea().setText("");
        view.getPrazo().setText("");
        view.getMeta().setText("");

        view.getStatus().setSelectedIndex(0);
    }
}