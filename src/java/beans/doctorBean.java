/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Doctor;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import servicios.Hospital;

/**
 *
 * @author Alejandro
 */
@Named(value = "doctorBean")
@ManagedBean
@RequestScoped
public class doctorBean {
    @EJB
    private Conector conector;    
    
    private List<services.Doctor> doctores;
    private services.Doctor selectedDoctor;

    public doctorBean() {
        this.doctores = new ArrayList<services.Doctor>();
        //this.doctores = conector.getDoctores();
        this.selectedDoctor = new services.Doctor();
        //selectedDoctor = new Doctor(90, "MAURO MARTINEZ", "GENERAL", 1);
    }

    public List<String> getSpecialties() {
        return conector.getSpecialties("Hospital Sanitas");
    }
    
    //método editado
    public List<services.Doctor> getDoctores() {
        return conector.getDoctores("Hospital Sanitas");
    }
    
    //método creado
    public List<services.Doctor> getDoctores(int idHospital, String tipoCita) {
        return conector.getDoctores(idHospital, tipoCita);
    }    

    public services.Doctor getSelectedDoctor() {
        return selectedDoctor;
    }

    public void setSelectedDoctor(services.Doctor selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
    }    
    
    public void btnCreateDoctor(ActionEvent actionEvent) {
        String msg;
        FacesMessage message;
        if(existeDoctor(selectedDoctor.getIdDoctor())) {
            msg = "Ya existe un doctor con el id especificado";
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, null);
        }
        else {
            conector.createDoctor(selectedDoctor.getIdDoctor(), 1, selectedDoctor.getName(), selectedDoctor.getSpecialty().toString().toUpperCase(), 
                                  "Hospital Sanitas");
            msg = "Se ha creado un doctor";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);            
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void btnUpdateDoctor(ActionEvent actionEvent) {
                String msg;
        FacesMessage message;
                    System.out.println("New Name: " + selectedDoctor.getName() + " New Specialty: " + selectedDoctor.getSpecialty().toString());

        System.out.println("Id Doctor Selected " + getIdDoctor(selectedDoctor.getName()));
        if(!existeDoctor(selectedDoctor.getIdDoctor()) ) {
            msg = "No existe doctor con el id especificado";
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, null);
        }
        else {
            services.Doctor doctor = conector.getDoctor(selectedDoctor.getIdDoctor(), "Hospital Sanitas");
            doctor.setName(selectedDoctor.getName());
            doctor.setSpecialty(selectedDoctor.getSpecialty());
            System.out.println("New Name: " + selectedDoctor.getName() + " New Specialty: " + selectedDoctor.getSpecialty().toString());
            conector.updateDoctor(doctor, "Hospital Sanitas");
            msg = "Se ha actualizado el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);            
        }
        FacesContext.getCurrentInstance().addMessage(null, message);     
    }

    public void btnDeleteDoctor(ActionEvent actionEvent) {
        String msg;
        FacesMessage message;
        if(!existeDoctor(selectedDoctor.getIdDoctor())) {
            msg = "No existen doctores con el id especificado";
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, null);
        }
        else {
            conector.deleteDoctor(selectedDoctor.getIdDoctor(), "Hospital Sanitas");
            msg = "Se ha borrado el registro";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);            
        }
        FacesContext.getCurrentInstance().addMessage(null, message);   
    }
    
    private boolean existeDoctor(Integer id) {
        List<services.Doctor> list = conector.getListDoctor("Hospital Sanitas");
        
        for(services.Doctor d : list) {
            if(d.getIdDoctor() == id) {
                return true;
            }
        }
        
        return false;
    }
    
    private int getIdDoctor(String name) {
        List<services.Doctor> list = conector.getListDoctor("Hospital Sanitas");
        
        for(services.Doctor d : list) {
            if(d.getName().equals(name)) {
                return d.getIdDoctor();
            }
        }
        
        return 0;
    }
}
