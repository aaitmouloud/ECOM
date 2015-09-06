/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.imag;

import javax.ejb.Remote;

/**
 *
 * @author seb
 */
@Remote
public interface SessionTestRemote {
    public String Message();
}
