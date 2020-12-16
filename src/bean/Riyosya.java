package bean;

import java.io.Serializable;


public class Riyosya implements Serializable {

	// フィールド
	private int rseiid;
	private String kainumber;
	private String rname;
	private String jusho ;
	private String adress;
	private String rpassward;


	// アクセッサー
	public int getRseiid() {
		return rseiid;
	}
	public void setRseiid(int rseiid) {
		this.rseiid = rseiid;
	}
	public String getKainumber() {
		return kainumber;
	}
	public void setKainumber(String kainumber) {
		this.kainumber = kainumber;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getJusho() {
		return jusho;
	}
	public void setJusho(String jusho) {
		this.jusho = jusho;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getRpassward() {
		return rpassward;
	}
	public void setRpassward(String rpassward) {
		this.rpassward = rpassward;
	}

}
