import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DateMain {
	
	static int year;
	static int day;
	static int month;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Geben Sie ihr Geburtstag ein:");
		System.out.print("Tag :");
		day = sc.nextInt();
		System.out.print("Monat :");
		month = sc.nextInt();
		System.out.println("Von welchem jahr aus wollen Sie die Wochentage zählen?");
		year = sc.nextInt();
		geburtsTage(day,month);
		weekDaysPerYear(year,DayOfWeek.MONDAY);
		
		sc.close();
	}
	
	static void geburtsTage(int day, int month)
	{
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(today.getYear(), month, day);
		Period between = Period.between(today, birthday);
		if(between.getDays()<0 || between.getMonths()<0)
		{
			birthday =birthday.plusYears(1);
			between = Period.between(today, birthday);
		}
		System.out.println("Ihr Geburtstag ist in "+between.getMonths()+" Monate und "+between.getDays()+" Tagen!");
		System.out.println();
	}

	static void weekDaysPerYear(int year,DayOfWeek wochentag)
	{
		int days = 0;
		LocalDate jahr = LocalDate.of(year,01,01);
		LocalDate lastWeekDay = null;
		do{	
			if(jahr.getDayOfWeek().equals(wochentag))
			{
				days++;
				lastWeekDay = LocalDate.of(jahr.getYear(),jahr.getMonth(),jahr.getDayOfMonth());
			}
			jahr = jahr.plusDays(1);	
		}while(jahr.getYear() <= LocalDate.now().getYear());
		String europe = lastWeekDay.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String america = lastWeekDay.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		System.out.println("Letzter "+wochentag+": "+europe+" || "+america);
		System.out.println("Von "+year+" bis "+LocalDate.now().getYear()+" gibt es "+days+" Montage");
	}
}