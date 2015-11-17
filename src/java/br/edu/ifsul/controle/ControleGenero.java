/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsul.controle;

import br.edu.ifsul.dao.GeneroDAO;
import br.edu.ifsul.modelo.Genero;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author jupassamani
 * @email juliapassamani@me.com
 */
@ManagedBean(name = "controleGenero")
@ViewScoped
public class ControleGenero implements Serializable{
    private GeneroDAO dao;
    private Genero objeto;

    public ControleGenero() {
        dao = new GeneroDAO();
        objeto = new Genero();
    }
    
    public String listar() {
        return "/privado/genero/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Genero();
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

    public GeneroDAO getDao() {
        return dao;
    }

    public void setDao(GeneroDAO dao) {
        this.dao = dao;
    }

    public Genero getObjeto() {
        return objeto;
    }

    public void setObjeto(Genero objeto) {
        this.objeto = objeto;
    }
}
