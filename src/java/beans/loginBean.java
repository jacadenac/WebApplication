/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

//import dao.UsuarioDao;
//import dao.UsuarioDaoImpl;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
//import model.Usuario;
import org.primefaces.context.RequestContext;
import util.MyUtil;

/**
 *
 * @author Alejandro
 */
@Named(value="loginBean")
@SessionScoped
public class loginBean implements Serializable{
/*
    private Usuario usuario;
    private UsuarioDao usuarioDao;
    
    public loginBean() {
        this.usuarioDao = new UsuarioDaoImpl();
        if(this.usuario == null){
            this.usuario = new Usuario();
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
  */
    public void login(ActionEvent actionEvent) {  
  /*      RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg;  
        boolean loggedIn; */ 
        RequestContext context = RequestContext.getCurrentInstance();
        String ruta = "";
        FacesMessage msg;
        boolean loggedIn=true;
          /*
        this.usuario = this.usuarioDao.login(this.usuario);
        if(this.usuario != null) {  
            loggedIn = true;  
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario.getUsuario());
            * msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.usuario.getUsuario());  
            */
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", "prueba");
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", null);  

            ruta = MyUtil.basepathlogin()+"views/inicio.xhtml";/*
        } else {  
            loggedIn = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Usuario y/o clave es incorrecto");  
            if(this.usuario == null){
                this.usuario = new Usuario();
            }
        }  
          */
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