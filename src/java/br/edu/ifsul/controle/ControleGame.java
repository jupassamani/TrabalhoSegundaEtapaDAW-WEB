/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsul.controle;

import br.edu.ifsul.dao.GameDAO;
import br.edu.ifsul.dao.GeneroDAO;
import br.edu.ifsul.modelo.Game;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author jupassamani
 * @email juliapassamani@me.com
 */
@ManagedBean(name = "controleGame")
@ViewScoped
public class ControleGame implements Serializable{
    private GameDAO dao;
    private Game objeto;
    private GeneroDAO daoGenero;

    public ControleGame(GameDAO dao, GeneroDAO daoGenero) {
        dao = new GameDAO();
        daoGenero = new GeneroDAO();
    }
    
    public String listar() {
        return "/privado/game/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Game();
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

    public GameDAO getDao() {
        return dao;
    }

    public void setDao(GameDAO dao) {
        this.dao = dao;
    }

    public Game getObjeto() {
        return objeto;
    }

    public void setObjeto(Game objeto) {
        this.objeto = objeto;
    }

    public GeneroDAO getDaoGenero() {
        return daoGenero;
    }

    public void setDaoGenero(GeneroDAO daoGenero) {
        this.daoGenero = daoGenero;
    }
}
