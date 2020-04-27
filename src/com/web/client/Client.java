package com.web.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.util.Scanner;

import com.web.service.WebInterface;



public class Client {
	
	static Service Monrreal, Quebec, Sherbrook;
	static WebInterface additionInterface;
	
	public static void main(String args[])
	{		
		try {
			URL sherbrookURL = new URL("http://localhost:8080/sherbrooke?wsdl");
			QName sherbrookQName = new QName("http://impl.service.web.com/", "ServerClassService");
			Sherbrook = Service.create(sherbrookURL, sherbrookQName);
			
			URL montrealURL = new URL("http://localhost:8081/montreal?wsdl");
			QName montreaQName = new QName("http://impl.service.web.com/", "ServerClassService");
			Monrreal = Service.create(montrealURL, montreaQName);
			
			URL quebecURL = new URL("http://localhost:8082/quebec?wsdl");
			QName quebecQName = new QName("http://impl.service.web.com/", "ServerClassService");
			Quebec = Service.create(quebecURL, quebecQName);
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter:\n1-To run the test\n2-To load data\n3-To enter your username ");
			String input = sc.nextLine().toLowerCase();
			
			if (input.equals("1"))
				Run_test();
			else if(input.equals("2"))
				LoadData();
			else if(input.equals("3"))
				Run();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Run_test()throws Exception {
		additionInterface = Monrreal.getPort(WebInterface.class);

		Runnable addEvent1 = () -> {
			String response = additionInterface.addEvent("MTLA101010", "Conferences", 3);
			System.out.println("addEvent(\"MTLA101010\", \"Conferences\", 2) -->" + response);
		};
		Runnable addEvent2 = () -> {
			String response = additionInterface.addEvent("MTLE202020", "Conferences", 2);
			System.out.println("addEvent(\"MTLE202020\", \"Conferences\", 1) -->" + response);
		};
		
		Runnable bookEvent1 = () -> {
			String response = additionInterface.bookEvent("MTLC1234", "MTLA101010", "Conferences");
			System.out.println("bookEvent(\"MTLC1234\", \"MTLA101010\", \"Conferences\") -->" + response);
		};
		Runnable bookEvent2 = () -> {
			String response = additionInterface.bookEvent("MTLC5678", "MTLA101010", "Conferences");
			System.out.println("bookEvent(\"MTLC5678\", \"MTLA101010\", \"Conferences\") -->" + response);
		};
		Runnable bookEvent3 = () -> {
			String response = additionInterface.bookEvent("MTLC4321", "MTLA101010", "Conferences");
			System.out.println("bookEvent(\"MTLC4321\", \"MTLA101010\", \"Conferences\") -->" + response);
		};
		Runnable bookEvent4 = () -> {
			String response = additionInterface.bookEvent("MTLC8765", "MTLA101010", "Conferences");
			System.out.println("bookEvent(\"MTLC8765\", \"MTLA101010\", \"Conferences\") -->" + response);
		};
		Runnable bookEvent5 = () -> {
			String response = additionInterface.bookEvent("MTLC0000", "MTLA101010", "Conferences");
			System.out.println("bookEvent(\"MTLC0000\", \"MTLA101010\", \"Conferences\") -->" + response);
		};
		
		
		Runnable removeEvent1 = () -> {
			String response = additionInterface.removeEvent("MTLA101010", "Conferences");
			System.out.println("removeEvent(\"MTLA101010\", \"Conferences\") -->" + response);
		};
		Runnable removeEvent2 = () -> {
			String response = additionInterface.removeEvent("MTLE202020", "Conferences");
			System.out.println("removeEvent(\"MTLE202020\", \"Conferences\") -->" + response);
		};
		
		Thread thread1 = new Thread(addEvent1);
		Thread thread2 = new Thread(addEvent2);
		thread1.start();
		thread2.start();
		Thread.sleep(500);
		System.out.println("------------------------------------------------------------");

		Thread thread3 = new Thread(bookEvent1);
		Thread thread4 = new Thread(bookEvent2);
		Thread thread5 = new Thread(bookEvent3);
		Thread thread6 = new Thread(bookEvent4);
		Thread thread7 = new Thread(bookEvent5);

		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		Thread.sleep(500);

		
		
		Thread thread9 = new Thread(removeEvent1);
		Thread thread10 = new Thread(removeEvent2);
		thread9.start();
		thread10.start();
		Thread.sleep(500);
		System.out.println("------------------------------------------------------------");

		
	}

	private static void LoadData()throws Exception {
		WebInterface ServerImplMTL = Monrreal.getPort(WebInterface.class);
		WebInterface ServerImplQUE = Quebec.getPort(WebInterface.class);
		WebInterface ServerImplSHE = Sherbrook.getPort(WebInterface.class);

		
		//add events
		Runnable addEvent1 = () -> {
			String response = ServerImplSHE.addEvent("SHEE150620", "Conferences", 2);
			System.out.println(response);
		};
		Runnable addEvent2 = () -> {
			String response = ServerImplSHE.addEvent("SHEE160620", "Seminars", 1);
			System.out.println(response);
		};
		
		Runnable addEvent3 = () -> {
			String response = ServerImplMTL.addEvent("MTLA160620", "Conferences", 2);
			System.out.println(response);
		};
		
		Runnable addEvent4 = () -> {
			String response = ServerImplMTL.addEvent("MTLA150620", "Trade Shows", 1);
			System.out.println(response);
		};
		
		Runnable addEvent5 = () -> {
			String response = ServerImplMTL.addEvent("MTLA170620", "Trade Shows", 1);
			System.out.println(response);
		};
		
		Runnable addEvent6 = () -> {
			String response = ServerImplQUE.addEvent("QUEA150620", "Conferences", 1);
			System.out.println(response);
		};
		
		Runnable addEvent7 = () -> {
			String response = ServerImplQUE.addEvent("QUEA160620", "Seminars", 1);
			System.out.println(response);
		};
		
		//book events
		Runnable bookEvent1 = () -> {
			String response = ServerImplQUE.bookEvent("QUEC1234", "SHEE150620", "Conferences");
			System.out.println(response);
		};
		Runnable bookEvent2 = () -> {
			String response = ServerImplQUE.bookEvent("QUEC1234", "SHEE160620", "Seminars");
			System.out.println(response);
		};
		Runnable bookEvent3 = () -> {
			String response = ServerImplQUE.bookEvent("QUEC1234", "MTLA160620", "Conferences");
			System.out.println(response);
		};
		Runnable bookEvent4 = () -> {
			String response = ServerImplQUE.bookEvent("QUEC1234", "QUEA150620", "Conferences");
			System.out.println(response);
		};
		Runnable bookEvent5 = () -> {
			String response = ServerImplSHE.bookEvent("SHEC1234", "MTLA170620", "Trade Shows");
			System.out.println(response);
		};
		
		
		Thread thread1 = new Thread(addEvent1);
		Thread thread2 = new Thread(addEvent2);
		Thread thread3 = new Thread(addEvent3);
		Thread thread4 = new Thread(addEvent4);
		Thread thread5 = new Thread(addEvent5);
		Thread thread6 = new Thread(addEvent6);
		Thread thread7 = new Thread(addEvent7);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		Thread.sleep(500);
		System.out.println("------------------------------------------------------------");

		Thread thread8 = new Thread(bookEvent1);
		Thread thread9 = new Thread(bookEvent2);
		Thread thread10 = new Thread(bookEvent3);
		Thread thread11 = new Thread(bookEvent4);
		Thread thread12 = new Thread(bookEvent5);

		thread8.start();
		thread9.start();
		thread10.start();
		thread11.start();
		thread12.start();
		System.out.println("------------------------------------------------------------");
	}
	
	private static void Run() {
		System.out.println("Please enter your username:");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine().toLowerCase();
		
		if(input.length() != 8) {
			System.out.println("Please write a proper ID!");
			Run();
		}
		
		String eventManager = input.substring(3,4);
		
		if(eventManager.equals(("m")))
		{
			try
			{
				manager(input);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(eventManager.equals("c"))
		{
			try
			{
				customer(input);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Your user access is not correct!");
			Run();
		}
	}

	private static void customer(String customerID) throws Exception{
		String portNumber = serverPort(customerID);
		if(portNumber.equals("Null"))
		{
			System.out.println("Invalid branch! Please write a proprer username!");
			return;
		}
		System.out.println("Please enter number of the action: \n "
				+ "1.Book an event \n "
				+ "2.Get booking schedule \n "
				+ "3.Cancel event \n "
				+ "4.Swap event \n "
				+ "5.Exit \n");
		Scanner sc = new Scanner(System.in);
		String action = sc.nextLine();
		if(action.equals("1")) 
		{
			String eventID = set_everntID(customerID.substring(0,4));
			String event_type = set_event_type();
			
			String response = additionInterface.bookEvent(customerID, eventID, event_type);
			System.out.println(response);
			customer(customerID);
			
		}
		else if(action.equals("2"))
		{
			String response = additionInterface.getBookingSchedule(customerID);
			System.out.println(response);
			customer(customerID);
		}
		else if(action.equals("3"))
		{
			String eventID = set_everntID(customerID.substring(0,4));
			String event_type = set_event_type();
			String response = additionInterface.cancelEvent(customerID, eventID, event_type);
			System.out.println(response);
			customer(customerID);		
		}
		else if(action.equals("4"))
		{
			System.out.println("Old Event:");
			String oldEventID = set_everntID(customerID.substring(0,4));
			String oldEventType = set_event_type();
			System.out.println("New Event:");
			String newEventID = set_everntID(customerID.substring(0,4));
			String newEventType = set_event_type();
			String response = additionInterface.swapEvent(customerID, newEventID, newEventType, oldEventID, oldEventType);
			System.out.println(response);
			customer(customerID);		
		}
		else if(action.equals("5"))
		{
			Run();
		}
		else
		{
			customer(customerID);
			System.out.println("Invalid action!");
		}
	}

	private static void manager(String input) throws Exception{
		String portNumber = serverPort(input);
		if(portNumber == "Null")
		{
			System.out.println("Invalid branch! Please write a proprer username!");
			return;
		}
		System.out.println("Please enter number of the action: \n "
				+ "1.Add new event \n "
				+ "2.Remove an event \n "
				+ "3.Check availability of an event \n "
				+ "4.log in as a customer\n "
				+ "5.Exit \n");

		Scanner sc = new Scanner(System.in);
		String action = sc.nextLine();
		if(action.equals("1")) 
		{
			String eventID = set_everntID(input.substring(0,4));
			String event_type = set_event_type();
			System.out.println("Please choose a capacity:");
			int booking_capacity = sc.nextInt();
			String response = additionInterface.addEvent(eventID, event_type,booking_capacity);
			System.out.println(response);
			manager(input);
			
		}
		else if(action.equals("2"))
		{	
			String event_type = set_event_type();
			System.out.println("Please enter the eventID: \n");
			String eventID = sc.nextLine();
			String response = additionInterface.removeEvent(eventID, event_type);
			System.out.println(response);
			manager(input);
		}
		else if(action.equals("3"))
		{
			String event_type = set_event_type();
			String response = additionInterface.listEventAvailability(event_type);
			System.out.println(response);
			manager(input);		
		}
		else if(action.equals("4"))
		{
			System.out.println("Please enter your username:");
			String data = sc.nextLine().toLowerCase();	
			customer(data);
		}
		else if(action.equals("5"))
		{
			Run();
		}
		else
		{
			manager(input);
			System.out.println("Invalid action!");
		}
		
	}
	
	private static String set_event_type() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please choose your event type:"
				+ "1.Conferences"
				+ "2.Trade Shows"
				+ "3.Seminars):"); 
		String event_type = sc.nextLine();
		if(event_type.equals("1")) 
		{
			event_type = "Conferences";
		}
		else if(event_type.equals("2")) 
		{
			event_type = "Trade Shows";
		}
		else if(event_type.equals("3")) 
		{
			event_type = "Seminars";
		}
		else 
		{
			System.out.println("Invalid event type!");
			Run();
		}
		return event_type;
	}

	private static String set_everntID(String data) {
		String eventID;
		if(data.substring(3,4).toUpperCase().equals("C"))
		{
			eventID = set_city();
		}
		else
		{
			eventID = data.substring(0,3).toUpperCase();
		}
		eventID += set_time_slot();
		eventID += set_event_date();
		
		return eventID;
	}

	private static String set_city() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please choose one of the city codes as belllow: \n"
				+ "Quebec (code: QUE) \n"
				+ "Montreal (code: MTL) \n"
				+ "Sherbrooke (code: SHE) \n");		
		String city = sc.nextLine().toUpperCase();
		if(city.length() != 3) {
			System.out.println("Please write a proper code for city!");
			Run();
		}
		return city;
	}

	private static String set_time_slot() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the time slot "
				+ "Morning (M), "
				+ "Afternoon (A) and "
				+ "Evening (E):");
		String time_slot = sc.nextLine().toUpperCase();

		if(time_slot.length()!=1)
		{
			System.out.println("Please write a proper time slot!");
			Run();
		}
		return time_slot;
	}
	
	private static String set_event_date() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the event date digits(ddmmyy)");
		String event_date = sc.nextLine().toUpperCase();
		if(event_date.length()!=6)
		{
			System.out.println("Please write a proper event date!");
			Run();
		}
		return event_date;
	}

	private static String serverPort(String input)
	{
		String branch = input.substring(0,3);
		String portNumber = "Null";
				
		if(branch.equals("que"))
		{
			additionInterface = Quebec.getPort(WebInterface.class);
			portNumber="QUE";
		}
		else if(branch.equals("mtl"))
		{
			additionInterface = Monrreal.getPort(WebInterface.class);
			portNumber="MTL";
		}
		else if(branch.equals("she"))
		{
			additionInterface = Sherbrook.getPort(WebInterface.class);
			portNumber="SHE";
		}
			
		return portNumber;
	}
}
