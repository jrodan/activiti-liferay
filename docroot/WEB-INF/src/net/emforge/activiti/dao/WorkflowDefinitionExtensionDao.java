package net.emforge.activiti.dao;

import java.util.List;

import net.emforge.activiti.entity.WorkflowDefinitionExtensionImpl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/** Dao for working with WorkflowDefinitionExtension
 *  
 * @author akakunin
 *
 */
@Repository
public class WorkflowDefinitionExtensionDao extends HibernateTemplate {
    @Autowired
    public void setHibernateSessionFactory(SessionFactory sessionFactory) {
    	setSessionFactory(sessionFactory);
    }
    
    /** Find workflow definitions
     * 
     * @param companyId
     * @param name
     * @param active
     * @param start
     * @param end
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<WorkflowDefinitionExtensionImpl> find(Long companyId, String name, Boolean active,  
													  int start, int end) {
        DetachedCriteria criteria = DetachedCriteria.forClass(WorkflowDefinitionExtensionImpl.class);

        if (companyId != null) {
        	criteria.add(Restrictions.eq("companyId", companyId));
        }
        
        if (active != null) {
        	criteria.add(Restrictions.eq("active", active));
        }
        
        if (name != null) {
        	criteria.add(Restrictions.eq("name", name));
        }

        return findByCriteria(criteria, start, end-start);
    }

    /** Find Workflow Definition
     * 
     * @param companyId
     * @param name
     * @param version
     * @return
     */
	@SuppressWarnings("unchecked")
	public WorkflowDefinitionExtensionImpl find(Long companyId, String name, Integer version) {
		DetachedCriteria criteria = DetachedCriteria.forClass(WorkflowDefinitionExtensionImpl.class);
		criteria.add(Restrictions.eq("companyId", companyId));
		criteria.add(Restrictions.eq("name", name));
		criteria.add(Restrictions.eq("version", version));
		
		List<WorkflowDefinitionExtensionImpl> result = findByCriteria(criteria);
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
		
	}
	
	/** Count Workflow Definitions
	 * 
	 * @param companyId
	 * @param name
	 * @param active
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Long count(Long companyId, String name, Boolean active) {
        DetachedCriteria criteria = DetachedCriteria.forClass(WorkflowDefinitionExtensionImpl.class);
        if (companyId != null) {
        	criteria.add(Restrictions.eq("companyId", companyId));
        }
        
        if (active != null) {
        	criteria.add(Restrictions.eq("active", active));
        }
        
        if (name != null) {
        	criteria.add(Restrictions.eq("name", name));
        }
        
        criteria.setProjection(Projections.rowCount());
    	
        List<Object> result = findByCriteria(criteria);
        return (Long)result.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public WorkflowDefinitionExtensionImpl findByProcessDefinitionId(String processDefinitionId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(WorkflowDefinitionExtensionImpl.class);
        criteria.add(Restrictions.eq("processDefinitionId", processDefinitionId));
        
		List<WorkflowDefinitionExtensionImpl> result = findByCriteria(criteria);
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}
}