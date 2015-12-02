/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ClienteTitularDAO;
import br.edu.ifsul.modelo.ClienteTitular;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author jupassamani
 * @email juliapassamani@me.com
 */
@ManagedBean(name = "controleClienteTitular")
@ViewScoped
public class ControleClienteTitular implements Serializable{
    private ClienteTitular objeto;
    private ClienteTitularDAO dao;

    public ControleClienteTitular() {
        dao = new ClienteTitularDAO();
        objeto = new ClienteTitular();
    }
    
    public String listar() {
        return "/privado/clientetitular/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new ClienteTitular();
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persistir(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
        } catch (Exception e) {
            Util.mensagemErro(e.getMessage());
        }
    }

    public void editar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception e) {
            Util.mensagemErro(e.getMessage());
        }
    }
    
    public void remover(Integer id){
        try {
            dao.remove(id);
        } catch(Exception e){
            Util.mensagemErro(e.getMessage());
        }
    }

    public ClienteTitular getObjeto() {
        return objeto;
    }

    public void setObjeto(ClienteTitular objeto) {
        this.objeto = objeto;
    }

    public ClienteTitularDAO getDao() {
        return dao;
    }

    public void setDao(ClienteTitularDAO dao) {
        this.dao = dao;
    }
}
