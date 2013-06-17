/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;
import services.CRUDEPSWs_Service;
import services.Eps;

/**
 *
 * @author Alejandro
 */
@Named(value = "epsBean")
@RequestScoped
public class epsBean {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/CRUD_EPS_ws/CRUD_EPS_ws.wsdl")
    private CRUDEPSWs_Service service;
    private List<Eps> epss;
    private Eps selectedEps;

    public epsBean() {
        this.selectedEps = new Eps();
        this.epss = new ArrayList<Eps>();
    }

    public List<Eps> getEpss() {
        this.epss = readAllEPS();
        return epss;
    }

    public Eps getSelectedEps() {
        return selectedEps;
    }

    public void setSelectedEps(Eps selectedEps) {
        this.selectedEps = selectedEps;
    }

    public void btnCreateEps(ActionEvent actionEvent) {
        String msg;
        createEPS(this.selectedEps.getId(), this.selectedEps.getName(), "http://localhost:8080/MedicalRecordCRUD/MedicalRecordCRUDWS?WSDL", "http://localhost:8080/AppointmentCRUDWS/AppointmentCRUDWS?WSDL");
        /*if (createEPS(this.selectedEps.getId(), this.selectedEps.getName(), "http://localhost:8080/MedicalRecordCRUD/MedicalRecordCRUDWS?WSDL", "http://localhost:8080/AppointmentCRUDWS/AppointmentCRUDWS?WSDL")) {
            msg = "Se creo correctamente el registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        //createEPS(21, "FAMISANAR", "http://localhost:8080/MedicalRecordCRUD/MedicalRecordCRUDWS?WSDL", "http://localhost:8080/AppointmentCRUDWS/AppointmentCRUDWS?WSDL");
        */
    }

    private static void createEPS(int idEPS, java.lang.String nombreEPS, java.lang.String urlMedicalRecord, java.lang.String urlAppointment) {
        services.CRUDEPSWs_Service service = new services.CRUDEPSWs_Service();
        services.CRUDEPSWs port = service.getCRUDEPSWsPort();
        port.createEPS(idEPS, nombreEPS, urlMedicalRecord, urlAppointment);
    }

    public void btnUpdateEps(ActionEvent actionEvent) {
        //updateEPS(21, "FAMISANAR_EDITADO", "http://localhost:8080/MedicalRecordCRUD/MedicalRecordCRUDWS?WSDL", "http://localhost:8080/AppointmentCRUDWS/AppointmentCRUDWS?WSDL");
        updateEPS(this.selectedEps.getId(), this.selectedEps.getName(), "http://localhost:8080/MedicalRecordCRUD/MedicalRecordCRUDWS?WSDL", "http://localhost:8080/AppointmentCRUDWS/AppointmentCRUDWS?WSDL");
    }

    private static void updateEPS(int idEPS, java.lang.String nombreEPS, java.lang.String urlMedicalRecord, java.lang.String urlAppointment) {
        services.CRUDEPSWs_Service service = new services.CRUDEPSWs_Service();
        services.CRUDEPSWs port = service.getCRUDEPSWsPort();
        port.updateEPS(idEPS, nombreEPS, urlMedicalRecord, urlAppointment);
    }

    public void btnDeleteEps(ActionEvent actionEvent) {
        //deleteEPS(21);
        deleteEPS(this.selectedEps.getId());
    }

    private static void deleteEPS(int idEPS) {
        services.CRUDEPSWs_Service service = new services.CRUDEPSWs_Service();
        services.CRUDEPSWs port = service.getCRUDEPSWsPort();
        port.deleteEPS(idEPS);
    }

    private java.util.List<services.Eps> readAllEPS() {
        services.CRUDEPSWs port = this.service.getCRUDEPSWsPort();
        List<services.Eps> list = port.readAllEPS();
        
        for (services.Eps e : list) {
            System.out.println(e.getId());
            System.out.println(e.getName());
        }
        System.out.println(list.size());
        this.epss = list;
        return list;
    }

    private Eps readOneEPS(int idEPS) {
        services.CRUDEPSWs port = this.service.getCRUDEPSWsPort();
        return port.readOneEPS(idEPS);
    }
}
