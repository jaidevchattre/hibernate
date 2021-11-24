Hibernate Eager and Lazy Loading

For these demos debug the code while getting data from database

Inside instructor class
	@OneToMany(
			fetch=FetchType.LAZY,
			mappedBy="instructor",
			cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Course> courses;
	
	
Default	Fetch type for mappings
@OneToOne    EAGER
@OneToMany   LAZY
@ManyToOne   EAGER
@ManyToMany  LAZY