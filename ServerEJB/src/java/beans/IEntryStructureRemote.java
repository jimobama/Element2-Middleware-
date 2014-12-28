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
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Obaro I. Johnson
 */
@Remote
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public interface IEntryStructureRemote {

    List<Structure> getStructures() throws FinderException;

    boolean createStructure(Structure parameter) throws FinderException;

    boolean isExists(Structure struct) throws FinderException;

    boolean delete(Structure s) throws FinderException;

    public List<Structure> getStructures(int id) throws FinderException;

    public void deleteBySiteID(Long id);

}
