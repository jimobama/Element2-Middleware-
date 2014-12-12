/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Structure;
import java.util.List;
import javax.ejb.FinderException;
import javax.ejb.Remote;

/**
 *
 * @author Obaro I. Johnson
 */
@Remote
public interface IEntryStructureRemote {

    List<Structure> getStructures() throws FinderException;
    boolean createStructure(Structure parameter)throws FinderException;
    boolean isExists(Structure struct) throws FinderException;
    boolean delete(Structure s) throws FinderException;
    
}
