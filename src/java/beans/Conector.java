/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Cita;
import classes.Doctor;
import classes.Hospital;
import com.dataejbsra.ws.CompanyPersonWs_Service;
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
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/25.16.26.205_8080/CompanyPersonWs/CompanyPersonWs.wsdl")
    private CompanyPersonWs_Service service_4;
        
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ManageHospital/ManageHospital.wsdl")
    private ManageHospital_Service service_3;   
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/AdmEPSService/AdmEPSService.wsdl")
    private AdmEPSService_Service service_2;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/CRUDHospital/CRUDHospitalWS.wsdl")
    private CRUDHospital service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/UserRoleWS/UserRoleWS.wsdl")
    private UserRoleWS_Service service;
     
    private List<Hospital> listaHospitales;
    private List<Doctor> listaDoctores;
    private List<Cita> listaCitas;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 

    public Conector() {
        //creación de un listado de hospitales
        listaHospitales = new ArrayList<Hospital>();
        listaHospitales.add(new Hospital(1, "HOSPITAL DE FONTIBON","www.com"));
        listaHospitales.add(new Hospital(2, "HOSPITAL TUNAL","www.com"));
        listaHospitales.add(new Hospital(3, "HOSPITAL USME","www.com"));
        listaHospitales.add(new Hospital(4, "HOSPITAL MEISSEN","www.com"));
        
        //creación de un listado de doctores
        listaDoctores = new ArrayList<Doctor>();
        listaDoctores.add(new Doctor(10, "ROBERTO SANCHEZ", "GENERAL",1));
        listaDoctores.add(new Doctor(12, "MARCELA PARRA", "ODONTOLOGIA",1));
        listaDoctores.add(new Doctor(13, "PEDRO PEREZ", "OPTOMETRIA",1));   
        listaDoctores.add(new Doctor(13, "PEDRO PEREZ", "PEDIATRIA",1));  
        listaDoctores.add(new Doctor(14, "BERTA BARRERA", "GENERAL",2));
        listaDoctores.add(new Doctor(15, "SOFIA SUAREZ", "ODONTOLOGIA",2));
        listaDoctores.add(new Doctor(16, "WILSON HERRERA", "OPTOMETRIA",2));   
        listaDoctores.add(new Doctor(17, "FABIO GONZALEZ", "PEDIATRIA",2)); 
        listaDoctores.add(new Doctor(18, "ADRIANA ARIAS", "GENERAL",3));
        listaDoctores.add(new Doctor(19, "CESAR CORREA", "ODONTOLOGIA",3));
        listaDoctores.add(new Doctor(20, "DANIEL DUARTE", "OPTOMETRIA",3));   
        listaDoctores.add(new Doctor(21, "EDUARDO ESTRELLA", "PEDIATRIA",3));  
        listaDoctores.add(new Doctor(22, "FERNANDO JIMENEZ", "GENERAL",4));
        listaDoctores.add(new Doctor(23, "HUGO PINEDA", "ODONTOLOGIA",4));
        listaDoctores.add(new Doctor(24, "JAVIER GIRALDO", "OPTOMETRIA",4));   
        listaDoctores.add(new Doctor(25, "GUSTAVO PAYARES", "PEDIATRIA",4));    
        
        System.out.println("Initialize Doctors");
    }

    public List<Hospital> getHospitales() {
        if(listaHospitales==null) return new ArrayList<Hospital>();
        return  listaHospitales;
    }
    
    //se supone que en cada hospital se cuenta con todas las especialidades
    //así que resulta más práctico dejar las opciones como están en la vista
    public List<String> getTipoCita() {
        return getSpecialties();
    }
    
    public List<Doctor> getDoctores() {
        return listaDoctores;
    }      
    
    public List<Doctor> getDoctores(Integer idHospital, String especialidad) {
       List<Doctor> lista = new ArrayList<Doctor>();
       for(Doctor d:listaDoctores) {
           if(d.getHospital().intValue()==idHospital.intValue() && d.getEspecialidad().equals(especialidad))                
               lista.add(d);
        }
       lista.add(new Doctor(idHospital, "IdHospital"+idHospital.toString(), especialidad, idHospital));
       return lista;
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

    public java.util.List<java.lang.String> getSpecialties() {
        servicios.ManageHospital port = service_3.getManageHospitalPort();
        return port.getSpecialties();
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
    
    //métodos de la registraduría para el registro, validación y eliminación de claves

    public ROb registerRelation(java.lang.Long personCedule, java.lang.Long companyId, java.lang.String rolPerson, java.lang.String passwordCompany) {
        com.dataejbsra.ws.CompanyPersonWs port = service_4.getCompanyPersonWsPort();
        return port.registerRelation(personCedule, companyId, rolPerson, passwordCompany);
    }
    
}
