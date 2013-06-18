/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Usuario;
import facadews.Role;
import facadews.UserRole;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author William
 */
@Named(value = "usuarioBean")
@RequestScoped
public class usuarioBean {
    @EJB
    private Conector conector;
    
    private List<Usuario> usuarios;
    private Usuario selectedUsuario;

    public usuarioBean() {
        this.usuarios = new ArrayList<Usuario>();
        selectedUsuario = new Usuario();
    }

    public List<Usuario> getUsuarios() {
        usuarios = new ArrayList<Usuario>();
        
        List<facadews.UserRole> listaWS = conector.getListUR(Role.USER);
        for(facadews.UserRole ur:listaWS) {
            usuarios.add(new Usuario(ur.getId(), ur.getRole().toString(), ur.getIdEntity()));
        }
        listaWS = conector.getListUR(Role.DOCTOR);
        for(facadews.UserRole ur:listaWS) {
            usuarios.add(new Usuario(ur.getId(), ur.getRole().toString(), ur.getIdEntity()));
        }
        listaWS = conector.getListUR(Role.ADMINHOSPITAL);
        for(facadews.UserRole ur:listaWS) {
            usuarios.add(new Usuario(ur.getId(), ur.getRole().toString(), ur.getIdEntity()));
        }
        listaWS = conector.getListUR(Role.ADMINEPS);
        for(facadews.UserRole ur:listaWS) {
            usuarios.add(new Usuario(ur.getId(), ur.getRole().toString(), ur.getIdEntity()));
        }         
        listaWS = conector.getListUR(Role.SUPERADMIN);
        for(facadews.UserRole ur:listaWS) {
            usuarios.add(new Usuario(ur.getId(), ur.getRole().toString(), ur.getIdEntity()));
        }         
        return usuarios;
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario SelectedUsuario) {
        this.selectedUsuario = SelectedUsuario;
    }
    
    /*métodos intermedios (entre el ws y el resto de la aplicación)*/
    public void btnCreateUsuario(ActionEvent actionEvent) {
        String msg;
        FacesMessage message;
        if(existeUsuario(selectedUsuario.getId())) {
            msg = "Ya existe un usuario con el id especificado";
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, null);
        }
        else {
            conector.createUR(selectedUsuario.getId(), getRoleFromString(selectedUsuario.getRol()), selectedUsuario.getIdEntity());
            msg = "Se ha creado un usuario";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);            
        }
        FacesContext.getCurrentInstance().addMessage(null, message);           
    }

    public void btnUpdateUsuario(ActionEvent actionEvent) {       
        String msg;
        FacesMessage message;
        if(!existeUsuario(selectedUsuario.getId())) {
            msg = "No existen usuarios con el id especificado";
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, null);
        }
        else {
            UserRole ur = conector.getUR(selectedUsuario.getId());
            ur.setIdEntity(selectedUsuario.getIdEntity());
            ur.setRole(getRoleFromString(selectedUsuario.getRol()));
            conector.setUR(ur);
            msg = "Se ha actualizado el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);            
        }
        FacesContext.getCurrentInstance().addMessage(null, message);         
    }

    public void borrarUsuario() {        
        String msg;
        FacesMessage message;
        if(!existeUsuario(selectedUsuario.getId())) {
            msg = "No existen usuarios con el id especificado";
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, null);
        }
        else {
            conector.deleteUR(selectedUsuario.getId());
            msg = "Se ha borrado el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);            
        }
        FacesContext.getCurrentInstance().addMessage(null, message);         
    }
    
    private Role getRoleFromString(String string) {
        if(string.equals("SUPERADMIN")) return Role.SUPERADMIN;
        if(string.equals("ADMINEPS")) return Role.ADMINEPS;
        if(string.equals("ADMINHOSPITAL")) return Role.ADMINHOSPITAL;
        if(string.equals("DOCTOR")) return Role.DOCTOR;
        if(string.equals("USER")) return Role.USER;
        return Role.USER;
    }
    
    private boolean existeUsuario(Integer id) {
        UserRole ur = conector.getUR(id);
        if(ur==null)   return false;
        return true;
    }
}
