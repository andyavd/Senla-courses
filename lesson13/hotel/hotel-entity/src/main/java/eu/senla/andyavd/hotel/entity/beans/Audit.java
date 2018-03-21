package eu.senla.andyavd.hotel.entity.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "audit")
public class Audit extends AEntity implements Serializable {

	private static final long serialVersionUID = -4250718459923772140L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "action_time")
	private String actionTime;
	@Column(name = "username")
	private String userName;
	@Column(name = "action_name")
	private String action;

	public Audit() {
		super();
	}

	public Audit(String actionTime, String userName, String action) {
		super();
		this.actionTime = actionTime;
		this.userName = userName;
		this.action = action;
	}
	
	public Audit(int id, String actionTime, String userName, String action) {
		super();
		this.id = id;
		this.actionTime = actionTime;
		this.userName = userName;
		this.action = action;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public String getActionTime() {
		return actionTime;
	}

	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	@Override
	public String toString() {
		return String.valueOf(new StringBuilder().append(id).append(" ").append(actionTime).append(" ").append(userName)
				.append(" ").append(action));
	}
}