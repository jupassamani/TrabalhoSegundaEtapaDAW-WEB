/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PlataformaDAO;
import br.edu.ifsul.modelo.Plataforma;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author jupassamani
 * @email juliapassamani@me.com
 */

@ManagedBean(name = "controlePlataforma")
@ViewScoped
public class ControlePlataforma implements Serializable{
    private PlataformaDAO dao;
    private Plataforma objeto;

    public ControlePlataforma() {
        dao = new PlataformaDAO();
        objeto = new Plataforma();
    }
    
    public String listar() {
        return "/privado/plataforma/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Plataforma();
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

    public PlataformaDAO getDao() {
        return dao;
    }

    public void setDao(PlataformaDAO dao) {
        this.dao = dao;
    }

    public Plataforma getObjeto() {
        return objeto;
    }

    public void setObjeto(Plataforma objeto) {
        this.objeto = objeto;
    }
}
