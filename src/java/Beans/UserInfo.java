/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author mohammad
 */
public class UserInfo {
    private String userId;

    private Persona persona;

    public String getUserId ()
    {
        return userId;
    }

    public void setUserId (String userId)
    {
        this.userId = userId;
    }

    public Persona getPersona ()
    {
        return persona;
    }

    public void setPersona (Persona persona)
    {
        this.persona = persona;
    }
}
