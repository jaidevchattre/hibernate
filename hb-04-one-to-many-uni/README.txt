Hibernate one to many uni directional

Course  Review 1-M
Deleting a course should delete all related reviews
Use lazy loading using fetch=FetchType.LAZY
Use JoinColumn(name=course_id) in course to map to reviews
Use cascade=CascadeType.ALL for cascading delete

In course class
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="instructor_id")
	private Instructor instructor;
	
	@OneToMany(fetch=FetchType.LAZY,
			   cascade=CascadeType.ALL)
	@JoinColumn(name="course_id")
	private List<Review> reviews;