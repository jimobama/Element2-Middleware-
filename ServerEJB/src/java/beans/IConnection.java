/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import org.jboss.beans.metadata.api.annotations.Start;

/**
 *
 * @author Obaro I. Johnson
 */
@Stateless
@Remote
public class IConnection implements IConnectionRemote {

    @Override
    @Start
    public boolean connect(String connectionString) {
        return true;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
