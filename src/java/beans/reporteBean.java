/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Reporte;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Alejandro
 */

@Named(value = "reporteBean")
@RequestScoped
public class reporteBean {
    private Reporte selectedReporte;

    public reporteBean() {
    }

    public Reporte getSelectedReporte() {
        return selectedReporte;
    }

    public void setSelectedReporte(Reporte selectedReporte) {
        this.selectedReporte = selectedReporte;
    }
}
