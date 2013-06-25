/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Reporte;
import java.awt.event.ActionEvent;
import java.util.List;
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
    private String selected;

    public reporteBean() {
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
    
    public Reporte getSelectedReporte() {
        return selectedReporte;
    }

    public void setSelectedReporte(Reporte selectedReporte) {
        this.selectedReporte = selectedReporte;
    }

    public void btnReportarNacimiento(ActionEvent actionEvent) {
        System.out.println("Reportar Nacimiento " + nombreHospital);
        List<servicios.Hospital> listHospital = conector.readAllHospital();
        servicios.Hospital hospital = new servicios.Hospital();
        
        for(servicios.Hospital h : listHospital) {
            if(h.getName().equals(nombreHospital)) {
                hospital = h;
            }
        }

        conector.reportBirth(nombre, hospital.getName());
    }  
    
    public void btnReportarMuerte(ActionEvent actionEvent ) {
        //conector.reportDeath(idPersona, selectedHospital.getID());
    }      
}
