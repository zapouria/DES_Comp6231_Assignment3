	package com.web.server;
	
	import javax.xml.ws.Endpoint;
	
	import java.io.IOException;
	import java.net.DatagramPacket;
	import java.net.DatagramSocket;
	import java.net.SocketException;
	
	import com.web.service.impl.ServerClass;


	public class Sherbrooke {
		public static void main(String args[]) throws Exception
		{
			try {
				System.out.println("Sherbrooke Server Ready and Waiting...");
				
				ServerClass ServerImpl = new ServerClass(5555, 6666, 7777, "SHE");
				Endpoint endpoint = Endpoint.publish("http://localhost:8080/sherbrooke", ServerImpl);
				
				Runnable task = () -> {
				run_server(ServerImpl);
				};
				Thread thread = new Thread(task);
				thread.start();
								
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	private static void run_server(ServerClass stub) {
		DatagramSocket socket = null;
		String response = "";
		try {
			socket = new DatagramSocket(7777);
			byte[] buffer = new byte[5000];
			System.out.println("Sherbrook UDP Server Started at 7777!");
			while (true) {
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				socket.receive(request);
				String data = new String( request.getData(), 0, request.getLength() );
				String[] parts = data.split(";");
				String function = parts[0]; 
				String eventID = parts[1]; 
				String eventType = parts[2]; 
				String customerID= parts[3];
				if(function.equals("remove_client_event")) {
					String result = stub.remove_client_event(eventID, eventType);
					response= result;
				}
				else if(function.equals("bookEvent")) {
					String result = stub.bookEvent(customerID, eventID, eventType);
					response= result;
				}
				else if(function.equals("list_events")) {
					String result = stub.list_events(eventType);
					response= result;
				}
				else if(function.equals("cancel_client_event")) {
					String result = stub.cancel_client_event(eventID, eventType);
					response= result;
				}
				else if(function.equals("boook_next_event")) {
					String result = stub.boook_next_event(eventID, eventType, customerID);
					response= result;
				}
				byte[] sendData = response.getBytes();
				DatagramPacket reply = new DatagramPacket(sendData, response.length(), request.getAddress(),request.getPort());
				socket.send(reply);
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (socket != null)
				socket.close();
		}
		
	}
}
