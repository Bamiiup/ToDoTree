package aaandrey.todotree.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TODO")
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name = "START_DATE")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Column(name = "PRIORITY")
	@Enumerated(EnumType.ORDINAL)
	private PriorityType priority;

	@Column(name = "IMPORTANT")
	private Boolean important;

	@Column(name = "WEIGHT")
	private Long weight;

	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;

	@ManyToMany
	@JoinTable(name = "TODO_TAG", joinColumns = @JoinColumn(name = "TODO_ID"), inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
	private Set<Tag> tags = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public PriorityType getPriority() {
		return priority;
	}

	public void setPriority(PriorityType priority) {
		this.priority = priority;
	}

	public Boolean getImportant() {
		return important;
	}

	public void setImportant(Boolean important) {
		this.important = important;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		setUser(user, false);
	}

	public void setUser(User user, boolean otherSideHasBeenAlreadySet) {
		this.user = user;
		if (otherSideHasBeenAlreadySet) {
			return;
		}
		user.addTodo(this, true);
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void addTag(Tag tag) {
		addTag(tag, false);
	}

	public void addTag(Tag tag, boolean otherSideHasBeenAlreadySet) {
		getTags().add(tag);
		if (otherSideHasBeenAlreadySet) {
			return;
		}

		tag.addTodo(this, true);
	}

	public void setTags(Collection<Tag> tags) {
		this.removeAllTags();
		tags.forEach(this::addTag);
	}

	public void removeAllTags() {
		getTags().stream().collect(Collectors.toList()).forEach(this::removeTag);
	}

	public void removeTag(Tag tag) {
		removeTag(tag, false);
	}

	public void removeTag(Tag tag, boolean otherSideRemoved) {
		this.getTags().remove(tag);
		if (otherSideRemoved) {
			return;
		}
		tag.removeTodo(this, true);
	}

	public int hash() {
		return Objects.hash(this.getId(), this.getName(), this.getStartDate(), this.getEndDate());
	}

	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}

		if (!(object instanceof Todo)) {
			return false;
		}

		Todo that = (Todo) object;

		if (!Objects.equals(this.getId(), that.getId())) {
			return false;
		}

		if (!Objects.equals(this.getName(), that.getName())) {
			return false;
		}

		if (!Objects.equals(this.getStartDate(), that.getStartDate())) {
			return false;
		}

		if (!Objects.equals(this.getEndDate(), that.getEndDate())) {
			return false;
		}

		return true;
	}
	
	public void removeUser() {
		removeUser(false);
	}

	public void removeUser(boolean otherSideWasRemoved) {
		User user = this.getUser();
		this.user = null;
		if(otherSideWasRemoved) {
			return;
		}
		user.removeTodo(this, true);
	}
}
