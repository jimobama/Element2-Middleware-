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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Obaro I. Johnson
 */
@Stateless

@Remote(IEntryStructureRemote.class)
public class IEntryStructure implements IEntryStructureRemote {

     @PersistenceContext(unitName = helps.EJBServerConstants.Beans.PERSISTENCE_UNIT)
    EntityManager structureManager;
    private static boolean isCreate = false;

    public IEntryStructure() {

    }

    @Override
    public List<Structure> getStructures() {
        //this.siteManager.createQuery("DELETE From Site s ").executeUpdate();
        Query query = this.structureManager.createQuery("Select s From Structure s ");

        List<Structure> s = (List<Structure>) query.getResultList();

        return s;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean createStructure(Structure parameter) throws FinderException {
        isCreate = false;
        if (this.isExists(parameter) != true) {

            parameter.setType(parameter.getType().trim().toLowerCase());
            parameter.setLocation(parameter.getLocation().trim().toLowerCase());
            parameter.setDescription(parameter.getDescription().trim().toLowerCase());
            this.createNewRecord(parameter);
        }

        return isCreate;
    }

    @Override
    public boolean isExists(Structure struct) {
        boolean isOkay = false;
        String sql = "Select s From Structure s WHERE (lower(s.id)=:id AND lower(s.siteId) =:siteID) "
                + " OR  (lower(s.id)=:id AND lower(s.siteId) =:siteID AND lower"
                + "(s.type)=:type)";
        Query q = this.structureManager.createQuery(sql);
        q.setParameter("id", struct.getId());
        q.setParameter("siteID", struct.getSiteId());
        q.setParameter("type", struct.getType().trim().toLowerCase());
        //check the number of query returns
        if (q.getResultList().size() > 0) {
            isOkay = true;
        }

        return isOkay;

    }

    @Override
    public boolean delete(Structure s) throws FinderException {
        boolean isOkay = false;
        String sql = "DELETE FROM Structure s WHERE s.id=:id";

        Query query = this.structureManager.createQuery(sql);
        query.setParameter("id", s.getId());
       
        //excute the query to check the affected row;
        int affectedRow = query.executeUpdate();
        if (affectedRow > 0) {
            isOkay = true;
        }

        return isOkay;
    }

    private void createNewRecord(Structure s) {
        isCreate = false;
        try
        {
           
        this.structureManager.persist(s);
         isCreate = true;
        }catch(Exception err)
        {
         err.printStackTrace();
        }
      
    }

}
