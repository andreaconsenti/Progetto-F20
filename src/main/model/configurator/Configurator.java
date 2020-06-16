package main.model.configurator;


import java.util.ArrayList;
import java.util.List;

import main.model.configurator.component.Component;
import main.model.configurator.configuration.Configuration;
import main.model.configurator.constraint.AbstractConstraint;
import main.model.people.costumer.Customer;
import main.services.persistence.PersistenceFacade;

/**
 * 
 * @author Capici Alessandro , Ripari Irene
 * 
 * 
 * */
public class Configurator {

	private ComponentCatalog catalog;
	private Configuration configuration;
	
	
	public Configurator(){
		catalog = ComponentCatalog.getInstance();
	}
	
	
	public Configurator(ComponentCatalog catalog) {
		super();
		this.catalog = catalog;
	}
	
	public Configurator(ComponentCatalog catalog,Configuration configuration) {
		this.catalog = catalog;
		this.configuration=configuration;
	}
	
	public void newConfiguration(){
		configuration = new Configuration();
	}	
	
	/**
	 * @param comp - Component that you would like to add.
	 * @return 
	 * @see {@link Configuration}
	 * 
	 * */
	public boolean addComponent(Component comp) {
		return configuration.addComponent(comp);
	}
	
	public boolean removeComponent(Component comp) {
		return configuration.removeComponent(comp);
	}
	
	/**
	 * @return true - if the configuration is valid
	 * @return false - if the configuration is not valid
	 * @see {@link Configuration} 
	 * */
	public boolean checkConf() {
		return configuration.checkConf();
	}
	/**
	 * @return configuration - returns the current configuration
	 * @see Configuration {@link Configuration}
	 * */
	public Configuration getConfiguration() {
		return configuration;
	}
	
	/**
	 * @return addedComponents - returns the current component's list of your configuration 
	 * @see Configuration {@link Configuration}
	 * */
	public List<Component> getAddedComponents(){
		return configuration.getAddedComponents();
	}

	@Override
	public String toString() {
		return "System [configuration=" + configuration + "]";
	}
	

	/**
	 * 
	 * 
	 * @return the last constrinatErrorList that the configurations getted trying to 
	 * add an incompatible component.
	 * If returns null means that the last add of a component worked correctly.
	 */
	public List<String> getListStringOfConstraintErrors(){
		List<String> list = new ArrayList<String>();
		
		List<AbstractConstraint> costraintErrorsList = configuration.getConstraintErrors();
		
		for(AbstractConstraint constraint : costraintErrorsList){
			list.add(constraint.getConstraintName());
		}
		
		if(list.isEmpty())
			return null;
		
		return list;
	}
	
	public boolean saveConfiguration(Customer customer){
		PersistenceFacade facade = PersistenceFacade.getIstance();
		return facade.updateConfiguration(configuration, customer);
	}

}
