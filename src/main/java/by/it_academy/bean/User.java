package by.it_academy.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import by.it_academy.util.ValidAge;

@Entity
@Table(name = "user")
@SecondaryTable(name = "user_data", pkJoinColumns = {
		@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id") })
public class User implements Serializable {

	private static final long serialVersionUID = 2417749186915478331L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotBlank(message = "{validation.user.login.notblank}")
	@Size(min = 4, max = 45, message = "{validation.user.login.size}")
	@Column(name = "login")
	String login;

	@NotBlank(message = "{validation.user.password.notblank}")
	@Size(min = 5, message = "{validation.user.password.size}")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{5,}", message = "{validation.user.password.pattern}")
	@Column(name = "password")
	String password;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", table = "user_data")
	int idUserData;

	@NotBlank(message = "{validation.user.firstname.notblank}")
	@Size(min = 2, max = 20, message = "{validation.user.firstname.size}")
	@Column(name = "firstname", table = "user_data")
	String firstname;

	@NotBlank(message = "{validation.user.lastname.notblank}")
	@Size(min = 2, max = 20, message = "{validation.user.lastname.size}")
	@Column(name = "lastname", table = "user_data")
	String lastname;

	@ValidAge(message = "{validation.user.datebirth.minage}")
	@Column(name = "datebirth", table = "user_data")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate datebirth;

	@NotBlank(message = "{validation.user.phone.notblank}")
	@Size(min = 13, max = 13, message = "{validation.user.phone.size}")
	@Pattern(regexp = "^\\+375(\\s+)?\\(?(17|25|29|33|44)\\)?(\\s+)?[0-9]{3}-?[0-9]{2}-?[0-9]{2}$", message = "{validation.user.phone.pattern}")
	@Column(name = "phone", table = "user_data")
	String phone;

	@Email(message = "{validation.user.email.email}")
	@Column(name = "email", table = "user_data")
	String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	private List<News> news;

	@ManyToOne
	@JoinColumn(name = "roles_id", referencedColumnName = "id")
	private Role role;

	public User() {

	}

	public User(String login, String password, String firstname, String lastname, LocalDate datebirth, String phone,
			String email) {
		super();
		this.login = login;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.datebirth = datebirth;
		this.phone = phone;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIdUserData() {
		return idUserData;
	}

	public void setIdUserData(int idUserData) {
		this.idUserData = idUserData;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getDatebirth() {
		return datebirth;
	}

	public void setDatebirth(LocalDate datebirth) {
		this.datebirth = datebirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datebirth == null) ? 0 : datebirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + id;
		result = prime * result + idUserData;
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((news == null) ? 0 : news.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (datebirth == null) {
			if (other.datebirth != null)
				return false;
		} else if (!datebirth.equals(other.datebirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id != other.id)
			return false;
		if (idUserData != other.idUserData)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (news == null) {
			if (other.news != null)
				return false;
		} else if (!news.equals(other.news))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", login=").append(login).append(", password=").append(password)
				.append(", idUserData=").append(idUserData).append(", firstname=").append(firstname)
				.append(", lastname=").append(lastname).append(", datebirth=").append(datebirth).append(", phone=")
				.append(phone).append(", email=").append(email).append(", news=").append(news).append("]");
		return builder.toString();
	}

}