/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Usuario;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Alejandro
 */
@Named(value = "usuarioBean")
@RequestScoped
public class usuarioBean {

    private List<Usuario> usuarios;
    private Usuario usuario;

    public usuarioBean() {
        this.usuarios = new ArrayList<Usuario>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void btnCreateUsuario(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }

    public void btnUpdateUsuario(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }

    public void btnDeleteUsuario(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }
}
