/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author William
 */
@Named(value = "emergenciaBean")
@RequestScoped
public class EmergenciaBean {
    @EJB
    private Conector conector;

    private int idPaciente;
    private int idHospital;
    @ManagedProperty("#{loginBean.session.id}")
    private int idDoctor;
    private String costo;
    private Date fecha;
    
    public EmergenciaBean() {
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String cadena) {
        this.costo = cadena;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void btnNuevaEmergencia( ActionEvent actionEvent) throws DatatypeConfigurationException {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(fecha);
        XMLGregorianCalendar xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        //conector.createEmergency(idDoctor, xmlCal, new services.Time(),idPaciente , costo, idHospital);
    }
}
