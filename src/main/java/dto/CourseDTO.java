package dto;

import java.sql.Date;

public class CourseDTO {
	
	private int id;
	private int userId;
	private int hashtagId;
	private int categoryId;
	private String title;
	private Double rating;
	private int registerCount;
	private int pricePerDay;
	private int like;
	private String introduce;
	private String curriculum;
	private String recommandFor;
	private String beforeCheck;
	private Date createdAt;
	private Date updatedAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getHashtagId() {
		return hashtagId;
	}
	public void setHashtagId(int hashtagId) {
		this.hashtagId = hashtagId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public int getRegisterCount() {
		return registerCount;
	}
	public void setRegisterCount(int registerCount) {
		this.registerCount = registerCount;
	}
	public int getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}
	public String getRecommandFor() {
		return recommandFor;
	}
	public void setRecommandFor(String recommandFor) {
		this.recommandFor = recommandFor;
	}
	public String getBeforeCheck() {
		return beforeCheck;
	}
	public void setBeforeCheck(String beforeCheck) {
		this.beforeCheck = beforeCheck;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
