
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class Seacher {

	public final static int DEFAULT_PORT=43;
	public final static String DEFAULT_HOST="whois.internic.net";
	public final static String DEFAULT_SEARCH_HOST="mark";
	
	private int port=DEFAULT_PORT;
	private String host=DEFAULT_HOST;
	private String searchHost="mark";
	
	private Scanner sc=null;
	
	public Seacher(String host,int port,String searchHost){
		this.host=host;
		this.port=port;
		this.searchHost=searchHost;
		init();
	}
	
	public Seacher()
	{
		this(DEFAULT_HOST,DEFAULT_PORT,DEFAULT_SEARCH_HOST);
	}
	public Seacher(String host){
		this(host,DEFAULT_PORT,DEFAULT_SEARCH_HOST);
	}
	public Seacher(String host,int port){
		this(host,port,DEFAULT_SEARCH_HOST);
	}
	
	private void init(){
		this.sc=new Scanner(System.in);
		waiting();
	}
	private void waiting(){
		
		System.out.println("Input search host name,'quit' to exit!");
		
		
		while(this.sc.hasNext()){
			String ss=this.sc.nextLine();
			if(ss.equals("quit")){
				quit();
				break;
			}else{
				this.searchHost=ss;
				createSocket();
				break;
			}
		}
	}
	private void createSocket(){
		
		try(Socket socket=new Socket()){
				SocketAddress address=new InetSocketAddress(this.host,this.port);
				socket.connect(address);
			
			Writer out=new OutputStreamWriter(socket.getOutputStream(),"ASCII");	//prepare
			
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream(),"ASCII"));
			
			
			out.write(this.searchHost+"\r\n");
			out.flush();
			
			StringBuilder response=new StringBuilder();
			String theLine=null;
			while((theLine=in.readLine()) !=null){
				response.append(theLine);
				response.append("\r\n");
				
			}
			System.out.print(response);
			waiting();

		}catch(IOException e){
			System.err.println(e);
			quit();
		}
		
	}

	private void quit(){
		if(this.sc!=null) this.sc.close();
		System.exit(0);
	}
	
}
