package yougetit.data;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Entity class. Represents blog post instance
 * 
 * @author 	Kostyantyn Panchenko
 * @version 1.0
 * @since 	01.08.2016
 */
@Entity
@Table(name = "POSTS")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name = "title", length = 100)
	private String title;
	
	@NotNull
	@Column(name = "content", length = 4000)
	private String content;
	
	// TODO consider replacing with String author
	@NotNull
	@ManyToOne	// defaults to FetchType.EAGER
	@JoinColumn(name = "author_id", nullable = false)
	private BlogUser author;
	
	@OneToMany(mappedBy = "post", 
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Comment> comments;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "added")
	private Date added;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified")
	private Date lastModified;
	
	private boolean modified;
	
	/**
	 * No-args constructor
	 */
	public Post() { 
		added = lastModified = new Date();
	}
		
	/**
	 * Field-based constructor
	 * @param title - post title
	 * @param content - post text
	 * @param author - user
	 */
	public Post(String title, String content, BlogUser author) {
		this.title = title;
		this.content = content;
		this.author = author;
		added = lastModified = new Date();
	}
	
	/**
	 * Adding new comment to post and establishing bidirectional reference
	 * @param comment - comment to be added
	 * @return - true if successfully, false otherwise
	 */
	public boolean addComment(Comment comment) {
		boolean result = comments.add(comment);
		if (comment.getPost() != this) {
			comment.setPost(this);
		}		
		return result;
	}

	/*
	 * getters and setters
	 */
	public final String getTitle() {
		return title;
	}
	
	public final void setTitle(String title) {
		this.title = title;
	}
	
	public final String getContent() {
		return content;
	}
	
	public final void setContent(String content) {
		this.content = content;
	}
	
	public final BlogUser getAuthor() {
		return author;
	}
	
	public final void setAuthor(BlogUser username) {
		this.author = username;
	}
	
	public final List<Comment> getComments() {
		return comments;
	}
	
	public final void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public final Date getAdded() {
		return added;
	}
	
	public final void setAdded(Date added) {
		this.added = added;
	}
	
	public final Date getLastModified() {
		return lastModified;
	}
	
	public final void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	public final boolean isModified() {
		return modified;
	}
	
	public final void setModified(boolean modified) {
		this.modified = modified;
	}
	
	public final int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return getContent();
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		
		if (object == null || object.getClass() != Post.class) {
			return false;
		}
		
		Post that = (Post) object;
		
		if(that.getId() != this.id || that.getTitle() != this.title
				|| that.getContent() != this.content
				|| that.getAuthor() != this.author) {
			return false;
		}
				
		return true;	
	}
	
	@Override
	public int hashCode() {
		int result = content.hashCode();
		result = 31 * result + title.hashCode();
		result = 31 * result + 31 * id;		
		return result;
	}
}
