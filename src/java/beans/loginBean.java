/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

//import dao.UsuarioDao;
//import dao.UsuarioDaoImpl;
import facadews.CreateUR;
import facadews.ProcessResult;
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
@ManagedBean(name = "loginBean")
@SessionScoped
public class loginBean implements Serializable {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/25.23.97.240_8080/UserRoleWS/UserRoleWS.wsdl")
    private UserRoleWS_Service service;

    @EJB
    private Session session;
    
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
        
        if (this.findUser()) {
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", username);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Bienvenido!", null);
            if (session.getRole().equals("SUPERADMIN")) {
                ruta = MyUtil.basepathlogin() + "views/admin/inicio.xhtml";
            }if(session.getRole().equals("ADMINHOSPITAL")){
                ruta = MyUtil.basepathlogin() + "views/hospital/inicio.xhtml";
            }if(session.getRole().equals("ADMINEPS")){
                ruta = MyUtil.basepathlogin() + "views/eps/inicio.xhtml";
            }if(session.getRole().equals("DOCTOR")){
                ruta = MyUtil.basepathlogin() + "views/doctor/inicio.xhtml";
            }if(session.getRole().equals("USER")){
                ruta = MyUtil.basepathlogin() + "views/usuario/inicio.xhtml";
            }
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

    public void logout() {
        String ruta = MyUtil.basepathlogin() + "login.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        session = null;

        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(false);
        sesion.invalidate();

        context.addCallbackParam("loggetOut", true);
        context.addCallbackParam("ruta", ruta);
    }

    private boolean findUser() {
        UserRole u = getUR(Integer.valueOf(username)); 
        System.out.println("Rol de Usuario " + u.getRole().toString());
        if (u != null) {
            UserRole ur = new UserRole();
            ur.setId(username);
            ur.setIdEntity(u.getIdEntity());
            ur.setRole(u.getRole());

            session.setId(ur.getId());
            session.setIdEntity(ur.getIdEntity());
          //  session.setRole(String.valueOf(ur.getRole()));
            session.setRole(ur.getRole().toString()); //<- MANUAL
            //session.setRole("SuperAdmin");
            //session.setRole("AdminHospital");
            //session.setRole("AdminEPS");
            //session.setRole("Doctor");

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ROOOOOLLLL+ur.getRole()", null);
            System.out.print("ROOOOLLLL!!!" + ur.getRole());

            //No me sirve el metodo createUR  <- Con busqueda en la tabla de AdminRoles
            ur = getUR(Integer.valueOf(username)); 
            if(ur==null){
                Integer entity=0;
                //ur=createUR(username,Role.USER,entity);
            }
            session.setId(ur.getId());
            session.setIdEntity(ur.getIdEntity());
            session.setRole(ur.getRole().toString());
            return true;
        }
        return false;
    }

    private UserRole getUR(java.lang.Integer arg0) {
        facadews.UserRoleWS port = service.getUserRoleWSPort();
        return port.getUR(arg0);
    }

    private ProcessResult createUR(java.lang.Integer arg0, facadews.Role arg1, java.lang.Integer arg2) {
        facadews.UserRoleWS port = service.getUserRoleWSPort();
        return port.createUR(arg0, arg1, arg2);
    }

    private java.util.List<facadews.UserRole> getFullListUR() {
        facadews.UserRoleWS port = service.getUserRoleWSPort();
        return port.getFullListUR();
    }
    
    
}
