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
import javax.naming.NamingException;

/**
 *
 * @author Obaro I. Johnson
 */
@Remote
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public interface IEntrySiteRemote {

    public boolean createSite(entities.Site site) throws FinderException;

    public List<entities.Site> getSites() throws FinderException;

    public boolean deleteSite(entities.Site info);

    public boolean updateSite(int id, entities.Site info);

    public boolean isExists(entities.Site site) throws FinderException;

    public List<entities.Site> searchSites(entities.Site site) throws FinderException;

    public String getErrorMessage();

    List<Structure> getStructures(int id) throws FinderException, NamingException;
}
