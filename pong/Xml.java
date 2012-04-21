package pong;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.ArrayList;

public class Xml{
	private static void trace(String msg){System.out.println(msg);}
	
	public static ArrayList<Player> getPlayerlist(){
		trace("getting");
		Document doc = request("http://www.officewarz.com/sandbox/Pong/players.xml");
		ArrayList<Player> playerlist = new ArrayList<Player>();
		Player p;
		try{
			NodeList requests = doc.getChildNodes(); 
	
			int numRequests = requests.getLength();
			if (numRequests > 0)
			{
				for (int i = 0; i < numRequests; i++)
				{
					NodeList individualRequests = requests.item(i).getChildNodes();
					int numIndividualRequests = individualRequests.getLength();
					for(int j = 0; j < numIndividualRequests; j++)
					{
						
						String nodeName = individualRequests.item(j).getNodeName();
						if(!(nodeName.compareTo("#text")==0)){
							trace("\nTag: " + nodeName);
							
							String textContent = individualRequests.item(j).getTextContent();
							if(textContent.length() > 0) trace("Text: "+ textContent);
							
							NamedNodeMap nmap = individualRequests.item(j).getAttributes();
							
							if(nmap != null)
							{
								p = new Player(0);
								trace("Message to " + textContent);
								p.message = textContent;
								trace("Message set to " + p.message);
								for(int k = 0; k < nmap.getLength(); k++)
								{
									trace(nmap.item(k).getNodeName() + ": " + nmap.item(k).getNodeValue());
									if(nmap.item(k).getNodeName().compareTo("id") == 0)
									{
										p.id = MyMath.stringToInt(nmap.item(k).getNodeValue());
										trace("id set to " + p.id);
									}
									if(nmap.item(k).getNodeName().compareTo("name") == 0)
									{
										p.name = nmap.item(k).getNodeValue();
										trace("Name set to " + p.name);
									}
									
								}
								playerlist.add(p);
							}
						}
					}
				}
			}
		}catch(Exception e){
			trace("Error connecting to: " + e.getMessage());
		}
		trace ("Got Playerlsit");
		for(Player pl: playerlist){
			trace("ID: " + pl.id + ", Name: " + pl.name + ", Message: " +  pl.message);
		}
		return playerlist;
	}
	
	public static void sendPlayerlist(ArrayList<Player> playerlist){
		String xmlmsg = "<?xml version=\"1.0\"?>";
		xmlmsg += "\n<response>";
		for(Player p: playerlist){
			xmlmsg += "\n	<player id=\""+p.id+"\" name=\""+p.name+"\">"+p.message+"</player>";
		}
		xmlmsg += "\n</response>";
		
		trace("sending:\n" + xmlmsg);
		
		String url = "http://www.officewarz.com/ajax/app/console.php";
		String module = "test";
		String function = "write";
		String directory = "../../../sandbox/Pong/";
		String filename = "players.xml";
		String mode = "w";
		String content = xmlmsg;
		
		String args = "directory=" + directory + "|filename=" + filename + "|mode=" + mode + "|content=" + content;
		String request = "module=" + module + "&function=" + function + "&args=" + args;
		request(url, request);
	}
	
	public static Document request(String url){
		return request(url, "");
	}
	
	public static Document request(String url, String args){
		String request = url;
		if(args.length() > 0){
			request += "?request=" + Base64Coder.base64_encode(args);
		}

		Document returnDoc = null;
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			returnDoc = db.parse(request);
		}catch(Exception e){
			trace("Error: " + e.getMessage());
		}
		return returnDoc;
	}
	
}