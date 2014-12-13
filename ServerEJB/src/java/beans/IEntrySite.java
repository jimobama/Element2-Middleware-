/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Site;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.Remote;
import javax.ejb.FinderException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Obaro I. Johnson
 */
@Stateless
@Remote(IEntrySiteRemote.class)
public class IEntrySite implements IEntrySiteRemote {

    //all private fields
    private static boolean isCreate;
    @PersistenceContext(unitName = helps.EJBServerConstants.Beans.PERSISTENCE_UNIT)
    private EntityManager siteManager;
    private String error;

    public IEntrySite() {

        isCreate = false;

    }

//Using the synchronized modifier to avoid prevent interference of client trying to modifier or create a site with same id or accessing
//the same variable
    @Override
    public synchronized boolean createSite(Site site) throws FinderException {
        isCreate = false;
        try {
            if (this.isExists(site) != true) {

                site.setName(site.getName().trim().toLowerCase());
                site.setFlag(site.getFlag().trim().toLowerCase());
                site.setRegion(site.getRegion().trim().toLowerCase());
                this.createNewRecord(site);
            }
        } catch (FinderException err) {
            throw err;
        }

        return isCreate;

    }//end method

    //function that will check if site details are valid
    private boolean isValidate(Site siteinfo) {
        return siteinfo.validate();
    }

    private void createNewRecord(Site info) {
        isCreate = false;
        this.siteManager.persist(info);
        isCreate = true;

    }

    //this return the current error message
    @Override
    public synchronized String getErrorMessage() {

        return this.error;
    }

    @Override
    public synchronized List<Site> getSites() throws FinderException {

        //this.siteManager.createQuery("DELETE From Site s ").executeUpdate();
        Query query = this.siteManager.createQuery("Select s From Site s ");

        List<Site> sites = query.getResultList();

        return sites;

    }

    @Override
    public boolean deleteSite(Site info) {
        boolean isOkay = false;
        String sql = "DELETE FROM Site s WHERE s.id=:id OR LOWER(s.name) =:name";

        Query query = this.siteManager.createQuery(sql);
        query.setParameter("id", info.getId());
        query.setParameter("name", info.getName().trim().toLowerCase());
        //excute the query to check the affected row;
        int affectedRow = query.executeUpdate();
        if (affectedRow > 0) {
            isOkay = true;
        }

        return isOkay;
    }

    @Override
    public boolean updateSite(int id, Site info) {
        boolean isOkay = false;
        try {
            if (this.isExists(info)) {
                if (info.validate()) {
                    //create a ejb query language
                    String eql = "UPDATE Site s SET s.name=:name, s.flag=:flag, s.region=:region  WHERE s.id=:id";
                    Query query = this.siteManager.createQuery(eql);
                    query.setParameter("name", info.getName().trim().toLowerCase());
                    query.setParameter("flag", info.getFlag().trim().toLowerCase());
                    query.setParameter("region", info.getRegion().trim().toLowerCase());
                    query.setParameter("id", info.getId());

                    if (query.executeUpdate() > 0) {
                        isOkay = true;
                    }
                }
            }
        } catch (FinderException e) {
            return false;
        }

        return isOkay;
    }

    @Override
    /*
     The method find the all the sites that matches any of the site fields and return a list of the sites found
     */
    public List<Site> searchSites(Site site) throws FinderException {
        if (site == null) {
            return null;
        }

        String eql = "";
        Query query;
        query = this.siteManager.createQuery("Select s From Site s WHERE lower(s.name)= :name OR s.id = :id OR LOWER(s.region) = :region ");
        query.setParameter("name", site.getName().toLowerCase().trim());
        query.setParameter("id", site.getId());
        query.setParameter("region", site.getRegion().toLowerCase().trim());

        List<Site> sites = query.getResultList();

        return sites;

    }
    /*
     The method check if a site  already exists it matches with the site's name 
     */

    @Override
    public boolean isExists(Site site) throws FinderException {

        boolean isOkay = false;
        String sql = "Select s From Site s WHERE lower(s.name) like '" + site.getName().toLowerCase().trim() + "%' OR s.id ='" + site.getId() + "' ";
        Query q = this.siteManager.createQuery(sql);

        if (q.getResultList().size() > 0) {
            isOkay = true;
        }

        return isOkay;

    }

}
