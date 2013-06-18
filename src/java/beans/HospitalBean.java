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
import javax.xml.ws.WebServiceRef;
import services.CRUDHospital;

/**
 *
 * @author William
 */
@ManagedBean
@RequestScoped
public class HospitalBean {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/CRUDHospital/CRUDHospitalWS.wsdl")
    private CRUDHospital service;
    
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
        //implementar método que se comunica con el otro compomente
        createHospital(selectedHospital.getId(), selectedHospital.getNombre(), selectedHospital.getUrl());
        String msg = "Se creo correctamente el registro.";
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);        
    }   

    public void btnUpdateHospital(ActionEvent actionEvent) {
        //implementar método que se comunica con el otro compomente
    }
    
    public String borrarHospital(){
        String msg = "Se ha borrado: "+selectedHospital.getNombre();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);  
        deleteHospital(selectedHospital.getId());
        return "";
    }
    
    public List<Hospital> leerHospitales() {
        
        List<services.Hospital> listaWS = readAllHospital();
        List<Hospital> rta = new ArrayList<Hospital>();
        
        for(services.Hospital sh:listaWS) {
            Hospital h = new Hospital(sh.getID(), sh.getName(), sh.getURL());
            rta.add(h);
        }
        return rta;       
    }    
    
    //métodos del web service
    private void createHospital(int arg0, java.lang.String arg1, java.lang.String arg2) {
        services.CRUDHospitalWS port = service.getCRUDHospitalWSPort();
        port.createHospital(arg0, arg1, arg2);
    }

    private java.util.List<services.Hospital> readAllHospital() {
        services.CRUDHospitalWS port = service.getCRUDHospitalWSPort();
        return port.readAllHospital();
    }

    private void deleteHospital(int arg0) {
        services.CRUDHospitalWS port = service.getCRUDHospitalWSPort();
        port.deleteHospital(arg0);
    }
    
}
