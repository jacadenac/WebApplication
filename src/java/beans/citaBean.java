/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Cita;
import fachadews.Appointment;
import fachadews.MedicalRecord;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;
import services.AppointmentCRUDUrlWS_Service;
import services.CRUDEPSWs_Service;
import services.Eps;

/**
 *
 * @author Alejandro
 */
@Named(value = "citaBean")
@RequestScoped
public class citaBean {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/CRUD_EPS_ws/CRUD_EPS_ws.wsdl")
    private CRUDEPSWs_Service service_1;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/AppointmentCRUDUrlWS/AppointmentCRUDUrlWS.wsdl")
    private AppointmentCRUDUrlWS_Service service;
    private List<Appointment> citas;
    private Appointment selectedCita;
    private MedicalRecord historiaAsociada;
    private Eps epsAsociada;

    public citaBean() {
        this.citas = new ArrayList<Appointment>();
    }

    public List<Appointment> getCitas() {
        //
        return citas;
    }

    public Appointment getSelectedCita() {
        return selectedCita;
    }

    public void setSelectedCita(Appointment selectedCita) {
        this.selectedCita = selectedCita;
    }

    public void btnCreateCita(ActionEvent actionEvent) {
        addAppoinment(historiaAsociada.getIdHistoria(), selectedCita, epsAsociada.getId());
    }

    private void addAppoinment(int idRecord, fachadews.Appointment appointment, int idEPS) {
        services.AppointmentCRUDUrlWS port = service.getAppointmentCRUDUrlWSPort();
        port.addAppoinment(idRecord, appointment, idEPS);
    }

    public void btnUpdateCita(ActionEvent actionEvent) {
        getAppointment(historiaAsociada.getIdHistoria(), selectedCita.getIdAppointment(), epsAsociada.getId());
    }
    
    public void btnGetCita(ActionEvent actionEvent) {
        getAppointment(historiaAsociada.getIdHistoria(), selectedCita.getIdAppointment(), epsAsociada.getId());
    }

    private void getAppointment(int idRecord, int idAppointment, int idEPS) {
        services.AppointmentCRUDUrlWS port = service.getAppointmentCRUDUrlWSPort();
        port.getAppointment(idRecord, idAppointment, idEPS);
    }

    public void btnDeleteCita(ActionEvent actionEvent) {
        deleteAppoinment(historiaAsociada.getIdHistoria(), selectedCita.getIdAppointment(), epsAsociada.getId());
    }

    private void deleteAppoinment(int idRecord, int idAppointment, int idEPS) {
        services.AppointmentCRUDUrlWS port = service.getAppointmentCRUDUrlWSPort();
        port.deleteAppoinment(idRecord, idAppointment, idEPS);
    }

    private java.util.List<services.Eps> readAllEPS() {
        services.CRUDEPSWs port = service_1.getCRUDEPSWsPort();
        return port.readAllEPS();
    }
}
