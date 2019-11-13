/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufidelitas.beans;

import java.io.StringReader;
import java.io.StringWriter;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;

/**
 *
 * @author gcig
 */
@Named(value="JSONPModelAPIBean")
@Stateless
public class JSONPModelAPIBean {
    @Inject
    private Persona persona;
    private String salidaJSON;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getSalidaJSON() {
        return salidaJSON;
    }

    public void setSalidaJSON(String salidaJSON) {
        this.salidaJSON = salidaJSON;
    }
    
    
    
    //lee un json y genera un objeto java
    public String traducirJSON(){
        JsonReader lectorJSON =Json.createReader(new StringReader(salidaJSON));
        JsonObject objetoJson = lectorJSON.readObject();
        persona.setNombre(objetoJson.getString("nombre"));
        persona.setApellidos(objetoJson.getString("apellidos"));
        persona.setGenero(objetoJson.getString("genero"));
        persona.setEdad(objetoJson.getString("edad"));
        
        return "traducida";
    }
    
    //genera json
    public String generaJSON(){
        JsonObjectBuilder creadorJSON = Json.createObjectBuilder();
        JsonObject objetoJson = creadorJSON.
                add("nombre",persona.getNombre()).
                add("apellidos",persona.getApellidos()).
                add("genero",persona.getGenero()).
                add("edad",persona.getEdad()).
                build();
        
        StringWriter tira = new StringWriter();//escribir string en documentos
        JsonWriter jsonWriter = Json.createWriter(tira);
        jsonWriter.writeObject(objetoJson);
        setSalidaJSON(tira.toString());
        
        return "traducir";
        
    }
            

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
