import java.io.IOException;
import java.util.Scanner;

public class FantasyFRC {
	
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("What team?");
		int answer = scan.nextInt();
		RobotTeam team = new RobotTeam(answer, 2016, 5);
		System.out.println(team.getName());
		team.listEvents();
		System.out.println("Which competition?");
		int comp = scan.nextInt();
		System.out.println(team.getOvrRecord(comp));
		System.out.println(team.getQualRecord(comp));
		System.out.println(team.getRank(comp));
		System.out.println(team.getAwards(comp));

	}

}
