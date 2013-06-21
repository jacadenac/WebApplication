/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Hospital;
import classes.Reporte;
import java.awt.event.ActionEvent;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Alejandro
 */

@Named(value = "reporteBean")
@RequestScoped
public class reporteBean {
    @EJB
    private Conector conector;
    
    private String nombre;
    private Long idPersona;
    private String nombreHospital;
    private Reporte selectedReporte;
    private Hospital selectedHospital;

    public reporteBean() {
        selectedHospital = new Hospital();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombreHospital() {
        return nombreHospital;
    }

    public void setNombreHospital(String nombreHospital) {
        this.nombreHospital = nombreHospital;
    }

    public Hospital getSelectedHospital() {
        System.out.println("Id " + selectedHospital.getId() + "  " + " Nombre  " + selectedHospital.getNombre());
        return selectedHospital;
    }

    public void setSelectedHospital(Hospital selectedHospital) {
        this.selectedHospital = selectedHospital;
    }
    
    public Reporte getSelectedReporte() {
        return selectedReporte;
    }

    public void setSelectedReporte(Reporte selectedReporte) {
        this.selectedReporte = selectedReporte;
    }
    
    
    
    public void btnReportarNacimiento( ) {
        System.out.println("Reportar Nacimiento");
        conector.reportBirth(nombre, selectedHospital.getId());
    }  
    
    public void btnReportarMuerte(ActionEvent actionEvent ) {
        conector.reportDeath(idPersona, selectedHospital.getId());
    }      
}
