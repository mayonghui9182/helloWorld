package testMaven;

/**
 * Tdictionary entity. @author MyEclipse Persistence Tools
 */

public class Tdictionary implements java.io.Serializable {

	// Fields

	private TdictionaryId id;
	private String CCaption;
	private String CModify;
	private String CMemo;
	private String CEnglish;
	private Long LOrder;

	// Constructors

	/** default constructor */
	public Tdictionary() {
	}

	/** minimal constructor */
	public Tdictionary(TdictionaryId id) {
		this.id = id;
	}

	/** full constructor */
	public Tdictionary(TdictionaryId id, String CCaption, String CModify, String CMemo, String CEnglish, Long LOrder) {
		this.id = id;
		this.CCaption = CCaption;
		this.CModify = CModify;
		this.CMemo = CMemo;
		this.CEnglish = CEnglish;
		this.LOrder = LOrder;
	}

	// Property accessors

	public TdictionaryId getId() {
		return this.id;
	}

	public void setId(TdictionaryId id) {
		this.id = id;
	}

	public String getCCaption() {
		return this.CCaption;
	}

	public void setCCaption(String CCaption) {
		this.CCaption = CCaption;
	}

	public String getCModify() {
		return this.CModify;
	}

	public void setCModify(String CModify) {
		this.CModify = CModify;
	}

	public String getCMemo() {
		return this.CMemo;
	}

	public void setCMemo(String CMemo) {
		this.CMemo = CMemo;
	}

	public String getCEnglish() {
		return this.CEnglish;
	}

	public void setCEnglish(String CEnglish) {
		this.CEnglish = CEnglish;
	}

	public Long getLOrder() {
		return this.LOrder;
	}

	public void setLOrder(Long LOrder) {
		this.LOrder = LOrder;
	}

}