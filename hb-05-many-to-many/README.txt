Hibernate many to many

Course Student M-M
Deleting a course should not delete any related student
Use lazy loading using fetch=FetchType.LAZY


In Course class
	@ManyToMany(
			fetch=FetchType.LAZY,
			cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
			)
	@JoinTable(
			name="course_student",
			joinColumns=@JoinColumn(name="course_id"),
			inverseJoinColumns = @JoinColumn(name="student_id")
			)
	private List<Student> students;
	
In Student class
	@ManyToMany(
			fetch=FetchType.LAZY,
			cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
			)
	@JoinTable(
			name="course_student",
			joinColumns=@JoinColumn(name="student_id"),
			inverseJoinColumns = @JoinColumn(name="course_id")
			)
	private List<Course> courses;