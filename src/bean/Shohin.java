package bean;

import java.io.Serializable;


public class Shohin implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((shoid == null) ? 0 : shoid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shohin other = (Shohin) obj;
		if (shoid == null) {
			if (other.shoid != null)
				return false;
		} else if (!shoid.equals(other.shoid))
			return false;
		return true;
	}

	// フィールド
	private String shoid;
	private String daib;
	private String chub;
	private String shob ;
	private String sname;
	private String sshokai;
	private String ssetsumei;
	private int stanka;
	private int zaikosuu;
	private String shashin;





	// アクセッサー

	//①
	public String getShoid() {
		return shoid;
	}

	public void setShoid(String shoid) {
		this.shoid = shoid;
	}


								//②
	public String getDaib() {
		return daib;
	}

	public void setDaib(String daib) {
		this.daib = daib;
	}



								//③
	public String getChub() {
		return chub;
	}

	public void setChub(String chub) {
		this.chub = chub;
	}



								//④
	public String getShob() {
		return shob;
	}

	public void setShob(String shob) {
		this.shob = shob;
	}



								//⑤
	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}


								//⑥
	public String getSshokai() {
		return sshokai;
	}

	public void setSshokai(String sshokai) {
		this.sshokai = sshokai;
	}

								//⑦
	public String getSsetsumei() {
		return ssetsumei;
	}

	public void setSsetsumei(String ssetsumei ) {
		this.ssetsumei = ssetsumei;
	}



								//⑧
	public int getStanka() {
			return stanka;
	}

	public void setStanka(int stanka) {
		this.stanka = stanka;
	}



								//⑨
	public int getZaikosuu() {
		return zaikosuu;
	}

	public void setZaikosuu(int zaikosuu) {
		this.zaikosuu = zaikosuu;
	}


								//⑩
	public String getShashin() {
		return shashin;
	}

	public void setShashin(String shashin) {
		this.shashin = shashin;
	}


}
