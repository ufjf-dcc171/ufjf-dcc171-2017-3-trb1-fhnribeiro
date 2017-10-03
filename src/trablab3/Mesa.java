/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trablab3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fhnri
 */
public class Mesa {
    
    protected List<Pedido> pedidos;
    
    public static int mesas_id=1;
    
    protected int id;
    
    protected String descricao;

    public Mesa(String descricao) {
        this.descricao = descricao;
        pedidos = new ArrayList<>();
    }
    
    

    public Mesa(List<Pedido> pedidos, String descricao) {
        
        this.pedidos = pedidos;
        
        this.descricao = descricao;
        
        this.id=mesas_id;
        
        Mesa.mesas_id+=1;
        
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void addPedido(Pedido p){
        pedidos.add(p);
    }
    
    @Override
    public String toString(){
        return this.descricao+"("+this.pedidos.size()+" pedidos)";
    }
    
}
