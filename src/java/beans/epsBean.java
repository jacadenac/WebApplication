/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

//import fachadews.ProcessResult;
//import fachadews.RegisterEPSBankAccount_Service;
import fachadews.ProcessResult;
import fachadews.RegisterEPSBankAccount_Service;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;
import services.CRUDEPSWs_Service;
import services.Eps;
import services.OPIaccess_Service;

/**
 *
 * @author Alejandro
 */
@Named(value = "epsBean")
@RequestScoped
public class epsBean {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/registerEPSBankAccount/RegisterEPSBankAccount.wsdl")
    private RegisterEPSBankAccount_Service service_2;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/OPIaccess/OPIaccess.wsdl")
    private OPIaccess_Service service_1;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/CRUD_EPS_ws/CRUD_EPS_ws.wsdl")
    private CRUDEPSWs_Service service;
    private List<Eps> epss;
    private Eps selectedEps;
    private Long accountNumber;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

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
        createEPS(  this.selectedEps.getId(), 
                    this.selectedEps.getName(), 
                    this.selectedEps.getUrlRecords());
    }

    private static void createEPS(int idEPS, java.lang.String nombreEPS, java.lang.String urlMedicalRecord) {
        services.CRUDEPSWs_Service service = new services.CRUDEPSWs_Service();
        services.CRUDEPSWs port = service.getCRUDEPSWsPort();
        port.createEPS(idEPS, nombreEPS, urlMedicalRecord);
    }
    
    public void btnRegisterEps(ActionEvent ae)
    {
        registerEPS(this.selectedEps.getName(), accountNumber);
    }
    
    
    private void registerEPS(java.lang.String name, java.lang.Long accountNumber) {
        /*services.OPIaccess port = service_1.getOPIaccessPort();
        port.registerEPS(name, accountNumber);*/
        //registerEPSBankAccount(accountNumber, name, name);
    }

    public void btnUpdateEps(ActionEvent actionEvent) {
        updateEPS(this.selectedEps.getId(), this.selectedEps.getName(), selectedEps.getUrlRecords(), selectedEps.getUrlAppointments());
    }

    private static void updateEPS(int idEPS, java.lang.String nombreEPS, java.lang.String urlMedicalRecord, java.lang.String urlAppointment) {
        services.CRUDEPSWs_Service service = new services.CRUDEPSWs_Service();
        services.CRUDEPSWs port = service.getCRUDEPSWsPort();
        port.updateEPS(idEPS, nombreEPS, urlMedicalRecord, urlAppointment);
    }

    public void btnDeleteEps(ActionEvent actionEvent) {
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
        this.epss = list;
        return list;
    }

    private Eps readOneEPS(int idEPS) {
        services.CRUDEPSWs port = this.service.getCRUDEPSWsPort();
        return port.readOneEPS(idEPS);
    }

    private ProcessResult registerEPSBankAccount(java.lang.Long arg0, java.lang.String arg1, java.lang.String arg2) {
        fachadews.RegisterEPSBankAccount port = service_2.getRegisterEPSBankAccountPort();
        return port.registerEPSBankAccount(arg0, arg1, arg2);
    }

}
