# Ranking-Assingment - 1 and 2

Created A Simple java-hibernate Stand-alone application that allows peer ranking in various skill areas.
Ranking options:-

1.  Show all People & Skill 
2.  Show all Rank 
3.  Create Ranking
4.  Remove Ranking
5.  Show Average for each student for each skill
6.  Show Top-per
7.  Sort students based on rank of certain skill

 
 Implemented:-

1. Create Separated Table for Skill and People
2. Create HibernateUtil class to create singleton session-factory pattern
3. Create DAO layer pattern structure.
4. Used Many-to-one mapping on Rank class to map skill ,people(subject and observer)
5. Used Hibernate Interceptor to validate the score value is between 1-10.
6. Used Hibernate Second level cache .
7. Used Hibernate Criteria  -for getting avg  rank.
8. Used Native Sql query  -for getting top-per in avg rank.
9. Used Transformers.aliasToBean to convert ResultSet to the CustomerDTO(RankResultMap) attributes.
 
