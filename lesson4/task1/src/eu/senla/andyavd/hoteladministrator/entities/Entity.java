package eu.senla.andyavd.hoteladministrator.entities;

public abstract class Entity {

	protected abstract int getId();

	public abstract void setId(int id);

	public abstract String getEntityParameters();
}
