package by.it_academy.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {
	
	public Role() {
		
	}
	
	public Role(int id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	
	@Column(name = "role")
	private String roleName;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	private List<User> listUser;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public List<User> getListUser() {
		return listUser;
	}


	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((listUser == null) ? 0 : listUser.hashCode());
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Role))
			return false;
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		if (listUser == null) {
			if (other.listUser != null)
				return false;
		} else if (!listUser.equals(other.listUser))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}
	
	
	
	

}
