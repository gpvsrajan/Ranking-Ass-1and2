import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

public class Test {

private void testDummyData() {
	
	
	
		People p1=new People();
		p1.setName("Swadish");
		p1=DAO.savePeople(p1);
	
		People p2=new People();
		p2.setName("Mahesh");
		p2=DAO.savePeople(p2);
		
		People p3=new People();
		p3.setName("Rajan");
		p3=DAO.savePeople(p3);
		
		People p4=new People();
		p4.setName("Pradeep");
		p4=DAO.savePeople(p4);
		
		People p5=new People();
		p5.setName("Dilip");
		p5=DAO.savePeople(p5);
		
		Skill s1=new Skill();
		s1.setName("Java");
		s1=DAO.saveSkill(s1);
		
		Skill s2=new Skill();
		s2.setName("Testing");
		s2=DAO.saveSkill(s2);
		
		Skill s3=new Skill();
		s3.setName("PHP");
		s3=DAO.saveSkill(s3);
		
	
		Rank r1=new Rank(s1,p3,p2,9);
		r1=DAO.saveRank(r1);
		Rank r2=new Rank(s1,p3,p1,10);
		r2=DAO.saveRank(r2);
		Rank r3=new Rank(s2,p3,p2,9);
		r3=DAO.saveRank(r3);
		Rank r4=new Rank(s2,p3,p1,8);
		r4=DAO.saveRank(r4);
		Rank r5=new Rank(s1,p4,p2,9);
		r5=DAO.saveRank(r5);
		Rank r6=new Rank(s2,p5,p1,9);
		r6=DAO.saveRank(r6);
		Rank r7=new Rank(s1,p5,p2,9);
		r7=DAO.saveRank(r7);
		Rank r8=new Rank(s2,p4,p2,1);
		r8=DAO.saveRank(r8);
		
		
	}

private void showskill_people() {
	System.out.println(" Showing Availabe People(Subject and Objeserver) and  Skill:-");
	
	System.out.println("Showing skills:-");
	List<Skill>  skills=DAO.fetchALLSkill();
	 for (Skill skill : skills) {
		 System.out.print("Id:- "+skill.getId()+"\t");
		System.out.print("Name:- "+skill.getName()+"\t");
		
		System.out.println();
	 }
	 System.out.println("Showing people:-");
	 List<People>  peoples=DAO.fetchALLPeople();
	 for (People people : peoples) {
		 System.out.print("Id:- "+people.getId()+"\t");
		System.out.print("Name:- "+people.getName()+"\t");
		
		System.out.println();
	 }

}

private int CreateRanking() {
	Rank r=new Rank();
	Scanner input = new Scanner(System.in);
	System.out.println("Enter the Subject id:-");
	People sub=DAO.fetchPeopleById(input.nextLong());
	if(sub==null){ 
		System.out.println("Invalid Subject id:-");
		return 0;
		}
	r.setSubject(sub);
	

	System.out.println("Enter the Observer id:-");
	People obs=DAO.fetchPeopleById(input.nextLong());
	if(obs==null){ 
		System.out.println("Invalid Observer id:-");
		return 0;
		}
	r.setObserver(obs);
	
	System.out.println("Enter the Skill id:-");
	Skill sk=DAO.fetchSkillByid(input.nextLong());
	if(sk==null){ 
		System.out.println("Invalid Skill id:-");
		return 0;}
	r.setSkill(sk);
	
	System.out.println("Enter the Score :-");
	Long score=input.nextLong();
	r.setScore(score);
	DAO.saveRank(r);
	
	System.out.println("Rank Saved Successfully ");
	return 1;
	
}


private void ShowRank() {
	System.out.println("Showing Ranks:-");
	List<Rank>  ranks=DAO.fetchALLRank();
	 for (Rank rank : ranks) {
		 System.out.print("Id:- "+rank.getId()+"\t");
		System.out.print("Observer Name:- "+rank.getObserver().getName()+"("+rank.getObserver().getId()+")"+"\t");
		System.out.print("Subject Name:- "+rank.getSubject().getName()+"("+rank.getSubject().getId()+")"+"\t");
		System.out.print("Skill :- "+rank.getSkill().getName()+"("+rank.getSkill().getId()+")"+"\t");
		System.out.print("Score :- "+rank.getScore()+"\t");
		System.out.println();
	 }
}

private void ShowAverage() {
	System.out.println("Showing Average for each student for each skill:-");
	List<RankResultMap>  ranks=DAO.fetchAvgSubRank();
	if(ranks==null){
		 System.out.println("No average Rank");
	}else{
				
		for (RankResultMap rank : ranks) {
		
			System.out.print("Subject Name:- "+rank.getSubject().getName()+"("+rank.getSubject().getId()+")"+"\t");
			System.out.print("Skill :- "+rank.getSkill().getName()+"("+rank.getSkill().getId()+")"+"\t");
			System.out.print("Score :- "+rank.getScore()+"\t"+"\n");
		}
	 }
}

private void ShowTopper() {
	System.out.println("Showing Topper:- Skill wise :-");
	List<RankResultMap>  ranks=DAO.fetchTopSubRank();
	if(ranks==null){
		 System.out.println("No average Rank");
	}else{
				
		for (RankResultMap rank : ranks) {
		
			System.out.print("Subject Name:- "+rank.getSubject().getName()+"("+rank.getSubject().getId()+")"+"\t");
			System.out.print("Skill :- "+rank.getSkill().getName()+"("+rank.getSkill().getId()+")"+"\t");
			System.out.print("Score :- "+rank.getScore()+"\t"+"\n");
		}
	 }
	
}
private void RemoveRanking() {

	Scanner input = new Scanner(System.in);
	System.out.println("Enter the Rank id which you want to delete:-");
	long id=input.nextLong();
	Rank rank=DAO.fetchRankByID(id);
	if(rank==null){
		 System.out.println("No rank are found on this id"+id);
	}else{
	System.out.println("Are you sure want to delete the rank of subject-"+rank.getSubject().getName()+" having socre -"+rank.getScore());
	System.out.println(" If yes ;Press Y or to Cancel Press N");
	String confirm=input.next();
	 if(confirm!=null && confirm.equalsIgnoreCase("Y")){
		DAO.deleteRank(rank); 
		 System.out.println("Rank deleted successfully");
	 }
	}
	
}


private void sortbyskill() {
	System.out.println("Sort students based on rank of certain skill:-");
	Scanner input = new Scanner(System.in);
	System.out.println("Enter the skill id which you want to Sort students:-");
	long id=input.nextLong();
	List<Rank>  ranks=DAO.fetchRankbySkillid(id);
	 for (Rank rank : ranks) {
		 System.out.print("Id:- "+rank.getId()+"\t");
		System.out.print("Observer Name:- "+rank.getObserver().getName()+"("+rank.getObserver().getId()+")"+"\t");
		System.out.print("Subject Name:- "+rank.getSubject().getName()+"("+rank.getSubject().getId()+")"+"\t");
		System.out.print("Skill :- "+rank.getSkill().getName()+"("+rank.getSkill().getId()+")"+"\t");
		System.out.print("Score :- "+rank.getScore()+"\t");
		System.out.println();
	 }
}


	public static void main(String[] args) {
		System.out.println("Wlecome to 'Event Registration' system:-");
		Test test = new Test();
		test.testDummyData();
		test.showMenu();

		Scanner scan = new Scanner(System.in);
		try {
			int i = scan.nextInt();
			System.out.println(i);
			while (i > 0 && i < 9) {
				switch (i) {
				case 1:
					test.showskill_people();
					test.showMenu();
					break;
				case 2:
					test.ShowRank();
					
					test.showMenu();
					break;
				case 3:
					test.CreateRanking();
					
					test.showMenu();
					break;
				case 4:
					test.RemoveRanking();
					test.showMenu();
					break;
				case 5:
					test.ShowAverage();
					test.showMenu();
					break;
				case 6:
					test.ShowTopper();
					test.showMenu();
					break;
				case 7:
					test.sortbyskill();
					test.showMenu();
					break;
					case 8:
						
						test.showMenu();
						break;
				}
				scan = new Scanner(System.in);
				i = scan.nextInt();
				System.out.println(i);

			}
		} catch (Exception c) {
			System.out.println("Error:-");
			System.out.println(c);
	HibernateUtil.shutdown();
	
		}
		
		HibernateUtil.shutdown();
		System.out.println("Thanks for Using Event Registration ");
	
		
	}
	
	




	public void showMenu() {
		System.out.println(" Choose your option");
		System.out.println(" 1 for Show all People & Skill :-");
		System.out.println(" 2 for Show all Rank :-");
		System.out.println(" 3 for Create Ranking:-");
		System.out.println(" 4 for Remove Ranking:-");
		System.out.println(" 5 for Show Average for each student for each skill:-");
		System.out.println(" 6 for Show Topper");
		System.out.println(" 7 for Sort students based on rank of certain skill");
		System.out.println(" 8 for Show Menus");
		System.out.println(" press 0 or any key other than choice to  Exit app");
	}
	
	

	
	 


}
