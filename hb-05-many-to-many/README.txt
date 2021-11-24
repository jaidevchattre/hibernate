Hibernate one to many uni directional

Course  Review 1-M
Deleting a course should delete all related reviews
Use lazy loading using fetch=FetchType.LAZY
Use JoinColumn(name=course_id) in course to map to reviews
Use cascade=CascadeType.ALL for cascading delete