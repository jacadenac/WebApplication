/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Cita;
import classes.Doctor;
import classes.Hospital;
import facadews.ProcessResult;
import facadews.UserRole;
import facadews.UserRoleWS_Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;
import services.AppointmentCRUDUrlWS_Service;
import services.CRUDHospital;
import services.ManageHospital_Service;

/**
 *
 * @author William
 */
@Stateless
public class Conector {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ManageHospital/ManageHospital.wsdl")
    private ManageHospital_Service service_3;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/AppointmentCRUDUrlWS/AppointmentCRUDUrlWS.wsdl")
    private AppointmentCRUDUrlWS_Service service_2;
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
    }

    public List<Hospital> getHospitales() {
        if(listaHospitales==null) return new ArrayList<Hospital>();
        return  listaHospitales;
    }
    
    //se supone que en cada hospital se cuenta con todas las especialidades
    //así que resulta más práctico dejar las opciones como están en la vista
    public List<String> getTipoCita() {
        List<String> lista = new ArrayList<String>();
        lista.add("GENERAL");
        lista.add("OPTOMETRIA");
        lista.add("PEDIATRIA");
        lista.add("ODONTOLOGIA");
        return lista;
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
        services.CRUDHospitalWS port = service_1.getCRUDHospitalWSPort();
        port.createHospital(arg0, arg1, arg2);
    }

    public void deleteHospital(int arg0) {
        services.CRUDHospitalWS port = service_1.getCRUDHospitalWSPort();
        port.deleteHospital(arg0);
    }

    public java.util.List<services.Hospital> readAllHospital() {
        services.CRUDHospitalWS port = service_1.getCRUDHospitalWSPort();
        return port.readAllHospital();
    }

    public services.Hospital readHospitalbyId(int arg0) {
        services.CRUDHospitalWS port = service_1.getCRUDHospitalWSPort();
        return port.readHospitalbyId(arg0);
    }    

    public void updateHospital(int arg0, java.lang.String arg1, java.lang.String arg2) {
        services.CRUDHospitalWS port = service_1.getCRUDHospitalWSPort();
        port.updateHospital(arg0, arg1, arg2);
    }

    //AppoitmentCRUD
    public void addAppoinment(int idRecord, fachadews.Appointment appointment, int idEPS) {
        services.AppointmentCRUDUrlWS port = service_2.getAppointmentCRUDUrlWSPort();
        port.addAppoinment(idRecord, appointment, idEPS);
    }
  
    //ManageHospital

    public boolean reportBirth(java.lang.String arg0, int arg1) {
        System.out.println();
        services.ManageHospital port = service_3.getManageHospitalPort();
        boolean b = port.reportBirth(arg0, arg1);
        System.out.println(b);
        return b;
    }

    public boolean reportDeath(java.lang.Long arg0, int arg1) {
        services.ManageHospital port = service_3.getManageHospitalPort();
        return port.reportDeath(arg0, arg1);
    }



    
    
}
