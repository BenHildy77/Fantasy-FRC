import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RobotTeam {

	private Elements ovrRecords, qualWins, qualMatches, ranks, awards;
	private String name;
	private int number, year, week;
	private Document doc;
	private Elements events;

	public RobotTeam(int num, int yearInput, int weekInput) throws IOException {
		number = num;
		year = yearInput;
		week = weekInput;
		doc = Jsoup.connect("https://www.thebluealliance.com/team/"+number+"/"+year).get();

		name = doc.select("#team-info > div > h2").text(); // finds the name of the team 
		events = doc.select("div.col-sm-4 h3 a[href]"); // finds the names of all events
		ovrRecords = doc.select("div.col-sm-4 > p > strong:contains(-)"); // finds all records from all competitions
		
		ranks = doc.select("div.col-sm-4 > p > strong:contains(Rank)"); // finds all rankings from all competitions

	}

	public String getName() {
		return name+"\n";
	}

	public void listEvents() {
		int i = 1;
		for(Element event : events) {
			System.out.println(i + ": " + event.text());
			i++;
		}
	}

	public String getOvrRecord(int indexIn) {
		int index = indexIn - 1;
		return "Overall Record: " + ovrRecords.get(index).text();
	}
	
	public String getQualRecord(int indexIn) throws IOException {
		int index = indexIn + 1;
		Element compID = doc.select(".col-xs-12 .col-xs-12 > div:nth-of-type(" + index + ")").first();
		String id = compID.id();
		
		Document compDoc = Jsoup.connect("https://www.thebluealliance.com/event/" + id + "#rankings").get();
		Element qualRec = compDoc.select("#rankingsTable > tbody > tr:contains("+number+") > td:contains(-)").first();
		String rec = qualRec.text();
		return rec;
	}

	public String getRank(int indexIn) {
		int index = indexIn - 1;
		return "Rank: " + ranks.get(index).text();
	}

	public String getAwards(int indexIn) {
		int index = indexIn + 1; //body > div:nth-child(5) > div > div.col-xs-12.col-sm-9.col-lg-10
		//String id = events.get(index).id();
		String selector = "body > div:nth-child(5) > div > div.col-xs-12.col-sm-9.col-lg-10 > div:nth-child(3) > .col-xs-12 > div:nth-of-type("+index+") > div:nth-child(1) > ul > li"; //:eq("+index+")
		awards = doc.select(selector); // finds the awards from indexed competition
		String awardsWon = "";
		System.out.println("Accomplishments:");
		for (Element award : awards){
			awardsWon = awardsWon + "-" +  award.text() + "\n";
		}
		return awardsWon;


	}
}
