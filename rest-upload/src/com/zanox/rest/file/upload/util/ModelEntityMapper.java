package com.zanox.rest.file.upload.util;

import org.apache.log4j.Logger;

/**
 * An interface to be used to impart a mapping behavior for all the mapping work we do
 * to transform a data transfer object to a domain entity.
 * <p>
 * This approach is in-line with the OOPS encapsulation property where each
 * class should do a job it is meant for.
 * </p>
 * <p>
 * Each service must have one or more mapping classes which extend the
 * {@link ModelEntityMapper} to map their domain entity objects with the data transfer
 * objects.
 * </p>
 * 
 * @param <A> - the application equivalent model of the data base entity. 
 * @param <D> - the data base model entity
 * 
 * @author Varma
 */
public interface ModelEntityMapper<Model, Entity> {

	static Logger logger = Logger.getLogger(ModelEntityMapper.class.getName());
	
	/**
	 * Method to get a data transfer object from a domain entity object.
	 */
	public Model getModel(Entity e);

	/**
	 * Method to get a domain entity object from a data transfer object.
	 */
	public Entity getEntity(Model m);

}
