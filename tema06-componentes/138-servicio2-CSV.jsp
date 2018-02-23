<% 
  // Alejandro Gascón
  String[][] polos =new String [][]{{
	  "Magnum Almendrado","Frigo","2.50"},{
	  "Mikolapiz","Miko","1.80"
	 }
  };
  
    for(int i=0; i<2; i++) {
		for(int j=0; j<3; j++) {
			if(j==2)
				out.println(polos[i][j]);
			else
				out.print("\""+polos[i][j]+"\",");
		}

    }
	
%>
