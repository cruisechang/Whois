


public class Whois {

	public static void main(String[] args){
		Seacher whois=null;
		
		if(args.length>1){
			whois=new Seacher(args[0],Integer.parseInt(args[1]));
			
		}else if(args.length>0){
			whois=new Seacher(args[0]);
		}else {
			whois=new Seacher();
		}
		
	}
}
