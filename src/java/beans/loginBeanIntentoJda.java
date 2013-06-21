/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import facadews.Role;
import facadews.UserRole;
import facadews.UserRoleWS_Service;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import util.MyUtil;

/**
 *
 * @author familia
 */
@Named(value="loginBeanIntentoJda")
@SessionScoped
public class loginBeanIntentoJda implements Serializable{

    private UserRole usuario;
    String pass;
    
    public loginBeanIntentoJda() {
        if(this.usuario == null){
            this.usuario = new UserRole();
        }
    }

    public UserRole getUsuario() {
        return usuario;
    }

    public void setUsuario(UserRole usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
  
    public void login(ActionEvent actionEvent) {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg;  
        boolean loggedIn;  
        String ruta = "";
          
        this.usuario.setId(1);
        this.usuario.setIdEntity(2);
        //this.usuario.setRol(Role.USER);
        
        if(this.usuario != null) {  
            loggedIn = true;  
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario.getId());
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", String.valueOf(this.usuario.getId()));  
            ruta = MyUtil.baseurl()+"views/inicio.xhtml";
        } else {  
            loggedIn = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Usuario y/o clave es incorrecto");  
            if(this.usuario == null){
                this.usuario = new UserRole();
            }
        }  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("loggedIn", loggedIn); 
        context.addCallbackParam("ruta", ruta); 
    }
    
    public void logout(){
        String ruta = MyUtil.basepathlogin()+"login.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(false);
        sesion.invalidate();
        
        context.addCallbackParam("loggetOut", true);
        context.addCallbackParam("ruta", ruta);
    }
}
