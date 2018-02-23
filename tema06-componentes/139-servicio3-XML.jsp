<% 
	java.util.ArrayList<java.util.ArrayList> polos = 
		new java.util.ArrayList<java.util.ArrayList>();

	java.util.ArrayList<String> polo1 = 
		new java.util.ArrayList<String>();
	polo1.add("Magnum");
	polo1.add("Frigo");
	polo1.add("2.50");
	polos.add(polo1);
	
	java.util.ArrayList<String> polo2 = 
		new java.util.ArrayList<String>();
	polo2.add("Mikolapiz");
	polo2.add("Miko");
	polo2.add("1.80");
	polos.add(polo2);
	
	out.println("<polos>");
	for(int i = 0; i<polos.size(); i++){
		out.println("  <polo>");
		out.println("    <nombre>"+polos.get(i).get(0)+"</nombre>");
		out.println("    <marca>"+polos.get(i).get(1)+"</marca>");
		out.println("    <precio>"+polos.get(i).get(2)+"</precio>");
		out.println("  </polo>");
	}
	out.print("</polos>");
  
%>
