/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Cita;
import classes.Doctor;
import classes.Hospital;
import com.dataejbsra.ws.ROb;
import facadews.ProcessResult;
import facadews.UserRole;
import facadews.UserRoleWS_Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;
import servicios.AdmEPSService_Service;
import servicios.CRUDHospital;
import servicios.ManageHospital_Service;

/**
 *
 * @author William
 */
@Stateless
public class Conector {    
        
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ManageHospital/ManageHospital.wsdl")
    private ManageHospital_Service service_3;   
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/AdmEPSService/AdmEPSService.wsdl")
    private AdmEPSService_Service service_2;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/CRUDHospital/CRUDHospitalWS.wsdl")
    private CRUDHospital service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/UserRoleWS/UserRoleWS.wsdl")
    private UserRoleWS_Service service;
     
    private List<Hospital> listaHospitales;
    private List<services.Doctor> listaDoctores;
    private List<Cita> listaCitas;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 

    public Conector() {
    }

    public List<Hospital> getHospitales() {
        if(listaHospitales==null) return new ArrayList<Hospital>();
        return  listaHospitales;
    }
    
    //se supone que en cada hospital se cuenta con todas las especialidades
    //así que resulta más práctico dejar las opciones como están en la vista

    
    public List<services.Doctor> getDoctores(String hospitalName) {
        listaDoctores = this.getListDoctor(hospitalName);
        return listaDoctores;
    }      
    
    public List<services.Doctor> getDoctores(Integer idHospital, String especialidad) {
      /*List<Doctor> lista = new ArrayList<Doctor>();
       for(Doctor d:listaDoctores) {
           if(d.getHospital().intValue()==idHospital.intValue() && d.getEspecialidad().equals(especialidad))                
               lista.add(d);
        }
       lista.add(new Doctor(idHospital, "IdHospital"+idHospital.toString(), especialidad, idHospital));
       return lista;*/
        return null;
    }    
    
    
    /* ---- MÉTODOS WS ---- */
    //UserRoleWS
    public ProcessResult createUR(java.lang.Integer arg0, facadews.Role arg1, java.lang.Integer arg2) {
        facadews.UserRoleWS port = service.getUserRoleWSPort();
        return port.createUR(arg0, arg1, arg2);
    }

    public ProcessResult deleteUR(java.lang.Integer arg0) {
        facadews.UserRoleWS port = service.getUserRoleWSPort();
        return port.deleteUR(arg0);
    }

    public java.util.List<facadews.UserRole> getListUR(facadews.Role arg0) {
        facadews.UserRoleWS port = service.getUserRoleWSPort();
        return port.getListUR(arg0);
    }

    public java.util.List<facadews.UserRole> getListURByEntity(facadews.Role arg0, java.lang.Integer arg1) {
        facadews.UserRoleWS port = service.getUserRoleWSPort();
        return port.getListURByEntity(arg0, arg1);
    }

    public UserRole getUR(java.lang.Integer arg0) {
        facadews.UserRoleWS port = service.getUserRoleWSPort();
        return port.getUR(arg0);
    }

    public void setUR(facadews.UserRole arg0) {
        facadews.UserRoleWS port = service.getUserRoleWSPort();
        port.setUR(arg0);
    }
    
    //CRUDHospitalWS
    public void createHospital(int arg0, java.lang.String arg1, java.lang.String arg2) {
        servicios.CRUDHospitalWS port = service_1.getCRUDHospitalWSPort();
        port.createHospital(arg0, arg1, arg2);
    }

    public void deleteHospital(int arg0) {
        servicios.CRUDHospitalWS port = service_1.getCRUDHospitalWSPort();
        port.deleteHospital(arg0);
    }

    public servicios.Hospital readHospitalbyId(int arg0) {
        servicios.CRUDHospitalWS port = service_1.getCRUDHospitalWSPort();
        return port.readHospitalbyId(arg0);
    }    

    public void updateHospital(int arg0, java.lang.String arg1, java.lang.String arg2) {
        servicios.CRUDHospitalWS port = service_1.getCRUDHospitalWSPort();
        port.updateHospital(arg0, arg1, arg2);
    }

    //AppoitmentCRUD
    public void addAppoinment(int idRecord, fachadews.Appointment appointment, int idEPS) {
        servicios.AdmEPSService port = service_2.getAdmEPSServicePort();
        port.addAppoinment(idRecord, appointment, idEPS);
    }
  
    //ManageHospital

    public ROb reportBirth(java.lang.String arg0, String arg1) {
        System.out.println();
        servicios.ManageHospital port = service_3.getManageHospitalPort();
        ROb b = port.reportBirth(arg0, arg1);
        System.out.println(b);
        return b;
    }

    

    /*public boolean createEmergency(int arg0, java.lang.Object arg1, servicios.Time arg2, int arg3, java.lang.String arg4, int arg5) {
        servicios.ManageHospital port = service_3.getManageHospitalPort();
        return port.createEmergency(arg0, arg1, arg2, arg3, arg4, arg5);
    }*/    

    public java.util.List<servicios.Hospital> readAllHospital() {
        servicios.CRUDHospitalWS port = service_1.getCRUDHospitalWSPort();
        return port.readAllHospital();
    }   

    public ROb reportDeath(java.lang.Long arg0, java.lang.String arg1) {
        servicios.ManageHospital port = service_3.getManageHospitalPort();
        return port.reportDeath(arg0, arg1);
    }

    public java.util.List<java.lang.String> getSpecialties(java.lang.String arg0) {
        servicios.ManageHospital port = service_3.getManageHospitalPort();
        return port.getSpecialties(arg0);
    }
    
        //Acceso al administrador de EPS

    public void createEPS(int idEPS, java.lang.String nombreEPS, java.lang.String iPandPort, java.lang.String password) {
        servicios.AdmEPSService port = service_2.getAdmEPSServicePort();
        port.createEPS(idEPS, nombreEPS, iPandPort, password);
    }

    public void deleteEPS(int idEPS) {
        servicios.AdmEPSService port = service_2.getAdmEPSServicePort();
        port.deleteEPS(idEPS);
    }

    public java.util.List<servicios.Eps> readAllEPS() {
        servicios.AdmEPSService port = service_2.getAdmEPSServicePort();
        return port.readAllEPS();
    }

    public void updateEPS(int idEPS, java.lang.String nombreEPS, java.lang.String urlMedicalRecord, java.lang.String urlAppointment, java.lang.String urlFinancial) {
        servicios.AdmEPSService port = service_2.getAdmEPSServicePort();
        port.updateEPS(idEPS, nombreEPS, urlMedicalRecord, urlAppointment, urlFinancial);
    }

    public void registerEPS(java.lang.String name, java.lang.Long accountNumber) {
      /* servicios.OPIaccess port = service_4.getOPIaccessPort();
        port.registerEPS(name, accountNumber);*/
    }

    public void createDoctor(int arg0, int idH, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) {
        servicios.ManageHospital port = service_3.getManageHospitalPort();
        System.out.println("ID " + arg0 + "ID Hospital " + idH + " Nombre " + arg1 + " Especialidad " + arg2);
        port.createDoctor(arg0, idH, arg1, arg2, arg3);
    }

    public java.util.List<services.Doctor> getListDoctor(java.lang.String arg0) {
        servicios.ManageHospital port = service_3.getManageHospitalPort();
        return port.getListDoctor(arg0);
    }

    public services.Doctor getDoctor(int arg0, java.lang.String arg1) {
        servicios.ManageHospital port = service_3.getManageHospitalPort();
        return port.getDoctor(arg0, arg1);
    }

    public void deleteDoctor(int arg0, java.lang.String arg1) {
        servicios.ManageHospital port = service_3.getManageHospitalPort();
        port.deleteDoctor(arg0, arg1);
    }

    public void updateDoctor(services.Doctor doctor, java.lang.String arg1) {
        servicios.ManageHospital port = service_3.getManageHospitalPort();
        port.updateDoctor(doctor, arg1);
    }
}
