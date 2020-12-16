package bean;

import java.io.Serializable;


public class Review implements Serializable {

	// フィールド


	private int viewid;

	private String reviewsid;

	private String review;



	public int getViewid() {
		return viewid;
	}

	public void setViewid(int viewid) {
		this.viewid = viewid;
	}

	public String getReviewsid() {
		return reviewsid;
	}

	public void setReviewsid(String reviewsid) {
		this.reviewsid = reviewsid;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}



























}
