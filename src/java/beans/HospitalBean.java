/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Hospital;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author William
 */
@ManagedBean
@RequestScoped
public class HospitalBean {
    @EJB
    private Conector conector;
    
    private List<Hospital> hospitales;
    private Hospital selectedHospital;
    
    public HospitalBean() {
        hospitales = new ArrayList<Hospital>();
        selectedHospital = new Hospital();
    }

    public List<Hospital> getHospitales() {
        hospitales=leerHospitales();
        return hospitales;
    }

    public void setHospitales(List<Hospital> hospitales) {
        this.hospitales = hospitales;
    }

    public Hospital getSelectedHospital() {
        return selectedHospital;
    }

    public void setSelectedHospital(Hospital selectedHospital) {
        this.selectedHospital = selectedHospital;
    }
    
    
    //métodos intermediarios entre los métodos del ws y la webapp
    public void btnCreateHospital(ActionEvent actionEvent) {
        String msg;
        FacesMessage message;
        if(existeHospital(selectedHospital.getId())) {
            msg = "El id especificado ya se encuentra registrado";
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, null);
        }
        else {
            conector.createHospital(selectedHospital.getId(), selectedHospital.getNombre(), selectedHospital.getUrl());
            msg = "Se ha creado un nuevo hospital";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);       
    }   

    public void btnUpdateHospital(ActionEvent actionEvent) {
        String msg = "Se ha actualizado el registro";
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message); 
        conector.updateHospital(selectedHospital.getId(), selectedHospital.getNombre(), selectedHospital.getUrl());
        System.out.println("updateHospital: nombre="+selectedHospital.getNombre()+" id="+selectedHospital.getId()
                +" url:"+selectedHospital.getUrl());
    }
    
    public void borrarHospital(){        
        String msg;
        FacesMessage message;
        if(!existeHospital(selectedHospital.getId())) {
            msg = "No hay hospitales con el id especificado";
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, null);
        }
        else {
            conector.deleteHospital(selectedHospital.getId());
            msg = "Se ha borrado el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);   
        }
        FacesContext.getCurrentInstance().addMessage(null, message);           
    }
    
    public List<Hospital> leerHospitales() {
        
        List<services.Hospital> listaWS = conector.readAllHospital();
        List<Hospital> rta = new ArrayList<Hospital>();
        
        for(services.Hospital sh:listaWS) {
            Hospital h = new Hospital(sh.getID(), sh.getName(), sh.getURL());
            rta.add(h);
        }
        return rta;       
    } 
    
    public boolean existeHospital(Integer id) {
        services.Hospital sh = conector.readHospitalbyId(id);
        if(sh==null) return false;
        return true;
    }
  
}
