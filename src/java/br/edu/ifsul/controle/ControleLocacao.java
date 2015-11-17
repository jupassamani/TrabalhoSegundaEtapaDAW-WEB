/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsul.controle;

import br.edu.ifsul.dao.LocacaoDAO;
import br.edu.ifsul.modelo.Locacao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author jupassamani
 * @email juliapassamani@me.com
 */

@ManagedBean(name = "controleLocacao")
@ViewScoped
public class ControleLocacao implements Serializable{
    private LocacaoDAO dao;
    private Locacao objeto;

    public ControleLocacao() {
        dao = new LocacaoDAO();
        objeto = new Locacao();
    }
    
    public String listar() {
        return "/privado/locacao/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Locacao();
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

    public LocacaoDAO getDao() {
        return dao;
    }

    public void setDao(LocacaoDAO dao) {
        this.dao = dao;
    }

    public Locacao getObjeto() {
        return objeto;
    }

    public void setObjeto(Locacao objeto) {
        this.objeto = objeto;
    }
}
