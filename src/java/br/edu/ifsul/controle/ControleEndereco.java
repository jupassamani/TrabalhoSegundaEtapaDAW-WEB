/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EnderecoDAO;
import br.edu.ifsul.modelo.Endereco;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author jupassamani
 * @email juliapassamani@me.com
 */
@ManagedBean(name = "controleEndereco")
@ViewScoped
public class ControleEndereco implements Serializable{
    private Endereco objeto;
    private EnderecoDAO dao;

    public ControleEndereco() {
        objeto = new Endereco();
        dao = new EnderecoDAO();
    }
    public String listar() {
        return "/privado/endereco/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Endereco();
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
    
    public Endereco getObjeto() {
        return objeto;
    }

    public void setObjeto(Endereco objeto) {
        this.objeto = objeto;
    }

    public EnderecoDAO getDao() {
        return dao;
    }

    public void setDao(EnderecoDAO dao) {
        this.dao = dao;
    }
    
           

}
