package bean;

import java.io.Serializable;


public class Konyu implements Serializable {

	// フィールド
	private int konyuid;
	private String rirekikainumber;
	private String konyuday ;
	private String rirekisid;
	private int konyusu ;
	private int tanka;
	private int shuko;


	// アクセッサー

	public int getKonyuid() {
		return konyuid;
	}
	public void setKonyuid(int konyuid) {
		this.konyuid = konyuid;
	}


	public String getRirekikainumber() {
		return rirekikainumber;
	}

	public void setRirekikainumber(String rirekikainumber) {
		this.rirekikainumber = rirekikainumber;
	}
	public String getKonyuday() {
		return konyuday;
	}
	public void setKonyuday(String konyuday) {
		this.konyuday = konyuday;
	}
	public String getRirekisid() {
		return rirekisid;
	}
	public void setRirekisid(String rirekisid) {
		this.rirekisid = rirekisid;
	}
	public int getKonyusu() {
		return konyusu;
	}
	public void setKonyusu(int konyusu) {
		this.konyusu = konyusu;
	}
	public int getTanka() {
		return tanka;
	}
	public void setTanka(int tanka) {
		this.tanka = tanka;
	}
	public int getShuko() {
		return shuko;
	}
	public void setShuko(int shuko) {
		this.shuko = shuko;
	}


























}
