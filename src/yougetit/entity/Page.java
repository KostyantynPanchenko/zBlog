/*
 * Simple web application which utilizes Spring MVC, Spring Security and Hibernate. 
 */
package yougetit.entity;

import java.util.List;

/**
 * Page class represents single page to be displayed.
 * @author Kostyantyn Panchneko
 * @version 1.0
 * @since	07.08.2016
 *
 */
public class Page {
	
	public final static int PAGE_SIZE = 5;
	private int pagesTotal;
	private int pageNumber;
	private List<Post> posts;	
	
	/**
	 * No-args constructor
	 */
	public Page() { }
	
	/**
	 * Field-based constructor
	 * @param pageNumber - current page's number
	 */
	public Page(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/*
	 * getters and setters
	 */
	public final synchronized int getPagesTotal() {
		return pagesTotal;
	}

	public final synchronized void setPagesTotal(int pagesTotal) {
		this.pagesTotal = pagesTotal;
	}

	public final int getPageNumber() {
		return pageNumber;
	}

	public final void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public final List<Post> getPosts() {
		return posts;
	}

	public final void setPosts(List<Post> posts) {
		this.posts = posts;
	}	
}
