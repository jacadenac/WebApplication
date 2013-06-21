/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import facadews.GetUR;
import facadews.Role;
import facadews.UserRole;

/**
 *
 * @author Alejandro
 */
@Named(value="inicioBean")
@SessionScoped
public class inicioBean implements Serializable{
    Role rol;
    public void crearSesion(){
        Integer id1=1;
        String exitoSolicitud;
        //recibirId mandado por WebManager
        //exitoSolicitud=GetUR(id1);
        
        //si no tiene rol... crea uno usuario con rol user
        
        //UserRole user= new UserRole(id1, rol, id1);
        
    }
}


/* 
    SUPERADMIN("SuperAdmin"),
    ADMINEPS("AdminEPS"),
    ADMINHOSPITAL("AdminHospital"),
    DOCTOR("Doctor"),
    USER("User"); 
    */