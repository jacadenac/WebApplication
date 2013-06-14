/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Cita;
import classes.Doctor;
import classes.Hospital;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author William
 */
@Stateless
public class Conector {
     
    private List<Hospital> listaHospitales;
    private List<Doctor> listaDoctores;
    private List<Cita> listaCitas;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 

    public Conector() {
        //creación de un listado de hospitales
        listaHospitales = new ArrayList<Hospital>();
        listaHospitales.add(new Hospital(1, "HOSPITAL DE FONTIBON"));
        listaHospitales.add(new Hospital(2, "HOSPITAL TUNAL"));
        listaHospitales.add(new Hospital(3, "HOSPITAL USME"));
        listaHospitales.add(new Hospital(4, "HOSPITAL MEISSEN"));
        
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

}
