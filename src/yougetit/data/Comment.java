package yougetit.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Entity class. Represents blog comment instance
 * 
 * @author 	Kostyantyn Panchenko
 * @version 1.0
 * @since 	01.08.2016
 */
@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(name = "content", length = 400)
	private String content;

	//TODO consider replacing with int postId
	@ManyToOne		// defaults to FetchType.EAGER
	@JoinColumn(name = "post_id")
	private Post post;

	//TODO consider replacing with String author
	@ManyToOne		// defaults to FetchType.EAGER
	@JoinColumn(name = "author_id", nullable = false)
	private BlogUser author; // unidirectional mapping

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "added")
	private Date added;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified")
	private Date lastModified;

	@Column(name = "modified")
	private boolean modified;

	/**
	 * No-args constructors
	 */
	public Comment() {
		added = lastModified = new Date();		
	}

	/**
	 * Field-based constructor
	 * @param comment - text of comment
	 * @param post - post ot be commented
	 * @param user - use adding current comment1
	 */
	public Comment(String comment, Post post, BlogUser user) {
		this.content = comment;
		this.author = user;
		this.post = post;
		added = new Date();
	}
	
	/*
	 * getters and setters
	 */
	public final String getContent() {
		return content;
	}

	public final void setContent(String content) {
		this.content = content;
	}

	public final Post getPost() {
		return post;
	}

	public final void setPost(Post post) {
		this.post = post;
	}

	public final BlogUser getAuthor() {
		return author;
	}

	public final void setAuthor(BlogUser user) {
		this.author = user;
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
		return "Comment [id=" + id + ", comment=" + content + ", user=" + author + "]";
	}
}
