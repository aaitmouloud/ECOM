/*
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution.
 * - Pas d’Utilisation Commerciale. 
 * - Partage dans les Mêmes Conditions 4.0 International.
 */
package fr.imag.entities;

import fr.imag.entities.dto.UtilisateurDTO;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test that the mdp hash method does a one-way unique hashing.
 * 
 * @author aaitmouloud
 */
public class MdpHashTest {

    @Test
    public void test() {
        String testString = "This is a very very very very long long string kmjlmds57#\"\"512155,;;n4578aamien";
        
        String one = UtilisateurDTO.hashMdp(new String(testString.getBytes()));
        String two = UtilisateurDTO.hashMdp(new String(testString.getBytes()));
        
        Assert.assertTrue("Hash is not the same.", one.equals(two) && two.equals(one));
    }
}
