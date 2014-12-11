/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Remote;

/**
 *
 * @author Obaro I. Johnson
 */
@Remote
public interface IConnectionRemote {

    boolean connect(String connectionString);
}
