/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trablab3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author fhnri
 */
public class JanelaMesas extends JFrame{
    
    private JanelaPedido janelaPedidos = new JanelaPedido();
    
    protected List<Mesa> mesas;
    public final JList<Mesa> lstMesas= new JList<>(new DefaultListModel<>());
    
    public final JList<Pedido> lstPedido= new JList<>(new DefaultListModel<>());
    
    private JButton fecharPedido = new JButton("Fechar pedido");
    private JButton addItem = new JButton("Gerenciar pedido");
    private JButton novoPedido = new JButton("Novo pedido");
    private JButton removerPedido = new JButton("Remover pedido");

    public JanelaMesas(List<Mesa> mesasSample) throws HeadlessException {
        
        super("Mesas");
        
        JanelaMesas janela=this;
        
        this.mesas = mesasSample;
        
        lstMesas.setModel(new MesasListModel(this.mesas));
        
        lstMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane paneMesas = new JScrollPane(lstMesas);
        Dimension d = lstMesas.getPreferredSize();
        d.width = 150;
        paneMesas.setPreferredSize(d);
        
        add(paneMesas,BorderLayout.WEST);
         
         add(new JScrollPane(lstPedido),BorderLayout.CENTER);
        
        JPanel botoes = new JPanel(new GridLayout(1,4));
        
        fecharPedido.setEnabled(false);
        addItem.setEnabled(false);
        removerPedido.setEnabled(false);
        
        botoes.add(fecharPedido);
        botoes.add(addItem);
        botoes.add(novoPedido);
        botoes.add(removerPedido);
        
        add(botoes,BorderLayout.SOUTH);
        lstMesas.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Mesa selecionada = lstMesas.getSelectedValue();
                if(selecionada != null){
                    lstPedido.setModel( new PedidosListModel(selecionada.getPedidos() ) );
                }else{
                    lstPedido.setModel( new DefaultListModel<>());
                }
            }
            
        });
        lstPedido.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Pedido pedido=lstPedido.getSelectedValue();
                
                if(pedido!=null){
                    fecharPedido.setEnabled(true);
                    if(pedido.isAberto()){
                        addItem.setEnabled(true);
                        removerPedido.setEnabled(true);
                        fecharPedido.setText("Fechar pedido");
                    }else{
                        addItem.setEnabled(false);
                        removerPedido.setEnabled(false);
                        fecharPedido.setText("Visualizar pedido");
                    }
                }else{
                    fecharPedido.setEnabled(false);
                    addItem.setEnabled(false);
                    removerPedido.setEnabled(false);
                }
            }
        });
        
        
        novoPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mesa selecionada = lstMesas.getSelectedValue();
                if(selecionada != null){
                    selecionada.addPedido(new Pedido());
                    lstPedido.updateUI();
                    lstMesas.updateUI();
                }
            }
        });
        
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Pedido pedido=lstPedido.getSelectedValue();
                
                if(pedido!=null){
                    
                    janelaPedidos.viewPedido(janela);
                    
                }
                
            }
        });
        
        fecharPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pedido pedido=lstPedido.getSelectedValue();
                
                if(pedido!=null){
                    
                    janelaPedidos.closePedido(janela);
                    
                }
            }
        });
        
        removerPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pedido pedido=lstPedido.getSelectedValue();
                
                if(pedido!=null){
                    
                    lstPedido.clearSelection();
                    Mesa selecionada = lstMesas.getSelectedValue();
                    selecionada.getPedidos().remove(pedido);
                    lstPedido.updateUI();
                    lstMesas.updateUI();
                    
                }
            }
        });
        
    }

    void viewNF() {
        if(lstPedido.getSelectedValue()!=null){
            JanelaNotaFiscal NF=new JanelaNotaFiscal(this);
        }
    }
    
    
    
}
