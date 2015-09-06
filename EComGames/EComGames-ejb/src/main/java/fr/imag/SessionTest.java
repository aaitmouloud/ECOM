/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag;

import javax.ejb.Stateless;

/**
 *
 * @author seb
 */
@Stateless(name="ECOMSessionTest", mappedName = "ECOMSessionTest")
public class SessionTest implements SessionTestRemote {

    @Override
    public String Message() {
        return "test";
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
