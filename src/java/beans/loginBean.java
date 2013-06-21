/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

//import dao.UsuarioDao;
//import dao.UsuarioDaoImpl;
import facadews.Role;
import facadews.UserRole;
import facadews.UserRoleWS_Service;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
//import model.Usuario;
import org.primefaces.context.RequestContext;
import util.MyUtil;

/**
 *
 * @author William
 */
@ManagedBean(name="loginBean")
@SessionScoped
public class loginBean implements Serializable{
    @EJB
    private Session session;
    
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/UserRoleWS/UserRoleWS.wsdl")
    private UserRoleWS_Service service;
    
    private Integer username;
    private String password;
    
    public Session getSession() {
        return session;
    }
    
    public Integer getUsername() {
        return username;
    }

    public void setUsername(Integer username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
 
    public String login() {  
        System.out.print("Entró a login!!");
        RequestContext context = RequestContext.getCurrentInstance();
        String ruta = "";
        FacesMessage msg;
        boolean loggedIn;

        if(this.findUser()) {
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", password);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Bienvenido!", null);  

            ruta = MyUtil.basepathlogin()+"views/inicio.xhtml";
            //return "views/inicio?faces-redirect=true";
            
        } else {  
            loggedIn = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Usuario y/o clave es incorrecto");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("loggedIn", loggedIn); 
        context.addCallbackParam("ruta", ruta); 
        
        return "";
    }
    
    public void logout(){
        String ruta = MyUtil.basepathlogin()+"login.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        session = null;
        
        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(false);
        sesion.invalidate();
        
        context.addCallbackParam("loggetOut", true);
        context.addCallbackParam("ruta", ruta);
    }
    
    private boolean findUser() {
        if(String.valueOf(username).contains("1")){
            UserRole ur = new UserRole();
            ur.setId(1);
            ur.setIdEntity(1);
            ur.setRole(Role.SUPERADMIN);
            
            session.setId(ur.getId());
            session.setIdEntity(ur.getIdEntity());
            //session.setRole(ur.getRole().toString());
            session.setRole(String.valueOf(username));
            
            return true;
        }           
        
//        UserRole ur = getUR(Integer.valueOf(username)); 
//        if(ur!=null) {
//            session.setId(ur.getId());
//            session.setIdEntity(ur.getIdEntity());
//            session.setRole(ur.getRole().toString());
//            return true;
//        }
        return false;
    }

    private UserRole getUR(java.lang.Integer arg0) {
        facadews.UserRoleWS port = service.getUserRoleWSPort();
        return port.getUR(arg0);
    }
    
}
