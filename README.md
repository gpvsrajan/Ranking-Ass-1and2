# Ranking-Assingment - 1 and 2

Created A Simple java-hibernate Standalone application that allows peer ranking in various skill areas.
Ranking options:-

1.  Show all People & Skill 
2.  Show all Rank 
3.  Create Ranking
4.  Remove Ranking
5.  Show Average for each student for each skill
6.  Show Topper
7.  Sort students based on rank of certain skill

 
 Implemented:-

1. Create Serpated Table for Skill and People
2. Create HibernateUtil class to create singleton sesssionfactory pattern
3. Create DAO layer pattern structure.
4. Used Manytoone mapping on Rank class to map skill ,people(subject and observer)
5. Used Hibernate Interceptor to validate the score value is between 1-10.
6. Used Hibernate Second level cahce .
7. Used Hibernate Criteria  -for geting avg and max of rank.
8. Used Transformers.aliasToBean to convert ResultSet to the CustomerDTO(RankResultMap) attributes.
 
