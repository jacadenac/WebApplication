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
import servicios.AdmEPSService_Service;
import servicios.Eps;

/**
 *
 * @author Alejandro
 */
@Named(value = "historiaClinicaBean")
@RequestScoped
public class historiaClinicaBean {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/AdmEPSService/AdmEPSService.wsdl")
    private AdmEPSService_Service service_2;
    
    private List<MedicalRecord> historias;
    private MedicalRecord selectedHistoria;
    private Eps epsAsociada;

    public historiaClinicaBean() {
        this.epsAsociada = new Eps();
        this.epsAsociada.setId(new Integer(1));
        this.selectedHistoria = new MedicalRecord();
        this.historias = new ArrayList<MedicalRecord>();
    }

    public List<MedicalRecord> getHistorias() {
        historias = readAllMedicalRecords(1);
        return historias;
    }

    public MedicalRecord getSelectedHistoria() {
        return selectedHistoria;
    }

    public void setSelectedHistoria(MedicalRecord selectedHistoria) {
        this.selectedHistoria = selectedHistoria;
    }

    public void btnCreateHistoria(ActionEvent actionEvent) {
        System.out.println(selectedHistoria.getIdHistoria());
        System.out.println(epsAsociada.getId());
        createMedicalRecord(selectedHistoria.getIdHistoria(), epsAsociada.getId());
    }

    private void createMedicalRecord(int idRecord, int idEPS) {
        servicios.AdmEPSService port = service_2.getAdmEPSServicePort();
        port.createMedicalRecord(idRecord, idEPS);
    }

    public void btnUpdateHistoria(ActionEvent actionEvent) {
        updateMedicalRecord(selectedHistoria, epsAsociada.getId());
    }

    private void updateMedicalRecord(fachadews.MedicalRecord record, int idEPS) {
        servicios.AdmEPSService port = service_2.getAdmEPSServicePort();
        port.updateMedicalRecord(record, idEPS);
    }

    public void btnDeleteHistoria(ActionEvent actionEvent) {
        deleteMedicalRecord(selectedHistoria.getIdHistoria(), epsAsociada.getId());
    }

    private void deleteMedicalRecord(int idRecord, int idEPS) {
        servicios.AdmEPSService port = service_2.getAdmEPSServicePort();
        port.deleteMedicalRecord(idRecord, idEPS);
    }

    private List<MedicalRecord> readAllMedicalRecords(int idEPS) {
        servicios.AdmEPSService port = service_2.getAdmEPSServicePort();
        return port.readAllMedicalRecord(idEPS);
    }
    
    private MedicalRecord readMedicalRecord(int idRecord, int idEPS) {
        servicios.AdmEPSService port = service_2.getAdmEPSServicePort();
        return port.readMedicalRecord(idRecord, idEPS);
    }

}
