/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import fachadews.MedicalRecord;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;
import services.AppointmentCRUDUrlWS_Service;
import services.Eps;
import services.MedicalRecordsCRUDUrlWS_Service;

/**
 *
 * @author Alejandro
 */
@Named(value = "historiaClinicaBean")
@RequestScoped
public class historiaClinicaBean {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/AppointmentCRUDUrlWS/AppointmentCRUDUrlWS.wsdl")
    private AppointmentCRUDUrlWS_Service service_1;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/MedicalRecordsCRUDUrlWS/MedicalRecordsCRUDUrlWS.wsdl")
    private MedicalRecordsCRUDUrlWS_Service service;
    private List<MedicalRecord> historias;
    private MedicalRecord selectedHistoria;
    private Eps epsAsociada;

    public historiaClinicaBean() {
        this.historias = new ArrayList<MedicalRecord>();
    }

    public List<MedicalRecord> getHistorias() {
        //historias = 
        return historias;
    }

    public MedicalRecord getSelectedHistoria() {
        return selectedHistoria;
    }

    public void setSelectedHistoria(MedicalRecord selectedHistoria) {
        this.selectedHistoria = selectedHistoria;
    }

    public void btnCreateHistoria(ActionEvent actionEvent) {
        createMedicalRecord(selectedHistoria.getIdHistoria(), epsAsociada.getId());
    }

    private void createMedicalRecord(int idRecord, int idEPS) {
        services.MedicalRecordsCRUDUrlWS port = service.getMedicalRecordsCRUDUrlWSPort();
        port.createMedicalRecord(idRecord, idEPS);
    }

    public void btnUpdateHistoria(ActionEvent actionEvent) {
        updateMedicalRecord(selectedHistoria, epsAsociada.getId());
    }

    private void updateMedicalRecord(fachadews.MedicalRecord record, int idEPS) {
        services.MedicalRecordsCRUDUrlWS port = service.getMedicalRecordsCRUDUrlWSPort();
        port.updateMedicalRecord(record, idEPS);
    }

    public void btnDeleteHistoria(ActionEvent actionEvent) {
        deleteMedicalRecord(selectedHistoria.getIdHistoria(), epsAsociada.getId());
    }

    private void deleteMedicalRecord(int idRecord, int idEPS) {
        services.MedicalRecordsCRUDUrlWS port = service.getMedicalRecordsCRUDUrlWSPort();
        port.deleteMedicalRecord(idRecord, idEPS);
    }

    private MedicalRecord readMedicalRecord(int idRecord, int idEPS) {
        services.MedicalRecordsCRUDUrlWS port = service.getMedicalRecordsCRUDUrlWSPort();
        return port.readMedicalRecord(idRecord, idEPS);
    }

}
