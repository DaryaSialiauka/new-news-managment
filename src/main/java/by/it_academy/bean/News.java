package by.it_academy.bean;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "news")
public class News implements Serializable{

	private static final long serialVersionUID = -8067498363285174962L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "author_id")
	private User user;

	@Size(min = 5, max = 45, message  
		      = "{validation.news.title.size}") 
	@NotBlank( message  
		      = "{validation.news.title.notblank}")
	@Column(name = "title")
	private String title;

	@Size(min = 20, max = 400, message 
			= "{validation.news.brief.size}") 
	@NotBlank( message  
		      = "{validation.news.brief.notblank}")
	@Column(name = "brief")
	private String brief;

	@Size(min = 20, max = 4000, message 
			= "{validation.news.content.size}") 
	@NotBlank( message  
		      = "{validation.news.content.notblank}")
	@Column(name = "content")
	private String content;

	@PastOrPresent(message = "{validation.news.data.pastorpresent}")
	@Column(name = "date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateCreate;

	public News(User user, String title, String brief, String content, LocalDate dateCreate) {
		this.user = user;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.dateCreate = dateCreate;
	}

	public News() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(LocalDate dateCreate) {
		this.dateCreate = dateCreate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brief == null) ? 0 : brief.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((dateCreate == null) ? 0 : dateCreate.hashCode());
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof News))
			return false;
		News other = (News) obj;
		if (brief == null) {
			if (other.brief != null)
				return false;
		} else if (!brief.equals(other.brief))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (dateCreate == null) {
			if (other.dateCreate != null)
				return false;
		} else if (!dateCreate.equals(other.dateCreate))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("News [id=").append(id).append(", user=").append(user).append(", title=").append(title)
				.append(", brief=").append(brief).append(", content=").append(content).append(", dateCreate=")
				.append(dateCreate).append("]");
		return builder.toString();
	}

}
