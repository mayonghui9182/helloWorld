package testMaven;

/**
 * TdictionaryId entity. @author MyEclipse Persistence Tools
 */

public class TdictionaryId implements java.io.Serializable {

	// Fields

	private String CSysname;
	private Long LKeyno;
	private String CKeyvalue;

	// Constructors

	/** default constructor */
	public TdictionaryId() {
	}

	/** full constructor */
	public TdictionaryId(String CSysname, Long LKeyno, String CKeyvalue) {
		this.CSysname = CSysname;
		this.LKeyno = LKeyno;
		this.CKeyvalue = CKeyvalue;
	}

	// Property accessors

	public String getCSysname() {
		return this.CSysname;
	}

	public void setCSysname(String CSysname) {
		this.CSysname = CSysname;
	}

	public Long getLKeyno() {
		return this.LKeyno;
	}

	public void setLKeyno(Long LKeyno) {
		this.LKeyno = LKeyno;
	}

	public String getCKeyvalue() {
		return this.CKeyvalue;
	}

	public void setCKeyvalue(String CKeyvalue) {
		this.CKeyvalue = CKeyvalue;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TdictionaryId))
			return false;
		TdictionaryId castOther = (TdictionaryId) other;

		return ((this.getCSysname() == castOther.getCSysname()) || (this.getCSysname() != null
				&& castOther.getCSysname() != null && this.getCSysname().equals(castOther.getCSysname())))
				&& ((this.getLKeyno() == castOther.getLKeyno()) || (this.getLKeyno() != null
						&& castOther.getLKeyno() != null && this.getLKeyno().equals(castOther.getLKeyno())))
				&& ((this.getCKeyvalue() == castOther.getCKeyvalue()) || (this.getCKeyvalue() != null
						&& castOther.getCKeyvalue() != null && this.getCKeyvalue().equals(castOther.getCKeyvalue())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCSysname() == null ? 0 : this.getCSysname().hashCode());
		result = 37 * result + (getLKeyno() == null ? 0 : this.getLKeyno().hashCode());
		result = 37 * result + (getCKeyvalue() == null ? 0 : this.getCKeyvalue().hashCode());
		return result;
	}

}