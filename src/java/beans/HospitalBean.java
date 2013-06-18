/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Hospital;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
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
    
    private List<Hospital> hospitales;
    private Hospital selectedHospital;
    
    public HospitalBean() {
        hospitales = new ArrayList<Hospital>();
        selectedHospital = new Hospital();
    }

    public List<Hospital> getHospitales() {
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
    
    public void btnCreateHospital(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
        String msg = "Se creo correctamente el registro.";
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);        
    }    
}
