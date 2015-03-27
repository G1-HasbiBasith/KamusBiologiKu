package adapter_dan_entitas;

public class EntitasKamus {
    String arti = "";
    String istilah = "";
    //clien
    String id_client = "";
    String nm_client = "";
    String pass_client = "";


	public void setArti(String ar) {
		this.arti = ar;
	}

	public String getArti() {
		return arti;
	}

    public void setIstilah(String is) {
        this.istilah = is;
    }

    public String getIstilah() {
        return istilah;
    }

    //client
    public  void setId_client(String ic){
        this.id_client = ic;
    }
    public String getId_client(){
        return id_client;
    }

    public  void setNm_client(String nc){
        this.nm_client = nc;
    }
    public String getNm_client(){
        return nm_client;
    }
    public  void setPass_client(String pc){
        this.pass_client = pc;
    }
    public String getPass_client(){
        return pass_client;
    }
}




