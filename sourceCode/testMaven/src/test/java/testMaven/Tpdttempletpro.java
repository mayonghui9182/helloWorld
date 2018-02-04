package testMaven;

/**
 * TpdttempletproId entity. @author MyEclipse Persistence Tools
 */

public class Tpdttempletpro implements java.io.Serializable {

	// Fields
	private Long LTempletid;
	private Long LTableid;
	private String CProcode;
	private String CProtype;
	private String CProname;
	private String CViewname;
	private String CTablecode;
	private String CClasscode;
	private String CClassname;
	private String CNotnull;
	private String CRegexp;
	private Long LKeyno;
	private String CDefaultvalue;
	private String CSelectorurl;
	private Long LOrder;
	private String CDescunit;
	private String CStylecss;
	private String CCaption;
	private String CColspan;
	private String CDatatype;
	private String CSysname;
	private String CShowkeyvalue;
	private String CExtshowsql;
	private String CEventattr;
	private String CAttrauditstatus;
	private String CValueentertype;
	private String CReadonly;
	private String CTempletmod;
	private String CDictoptionignores;
	private String CLimitnotnulloperatorflag;
	private String CNotice;

	// Constructors

	/** default constructor */
	public Tpdttempletpro() {
	}

	/** minimal constructor */
	public Tpdttempletpro(String CProcode, String CProtype, String CProname, String CViewname, String CTablecode) {
		this.CProcode = CProcode;
		this.CProtype = CProtype;
		this.CProname = CProname;
		this.CViewname = CViewname;
		this.CTablecode = CTablecode;
	}

	/** full constructor */
	public Tpdttempletpro(Long LTempletid, Long LTableid, String CProcode, String CProtype, String CProname,
			String CViewname, String CTablecode, String CClasscode, String CClassname, String CNotnull, String CRegexp,
			Long LKeyno, String CDefaultvalue, String CSelectorurl, Long LOrder, String CDescunit, String CStylecss,
			String CCaption, String CColspan, String CDatatype, String CSysname, String CShowkeyvalue,
			String CExtshowsql, String CEventattr, String CAttrauditstatus, String CValueentertype, String CReadonly,
			String CTempletmod, String CDictoptionignores, String CLimitnotnulloperatorflag, String CNotice) {
		this.LTempletid = LTempletid;
		this.LTableid = LTableid;
		this.CProcode = CProcode;
		this.CProtype = CProtype;
		this.CProname = CProname;
		this.CViewname = CViewname;
		this.CTablecode = CTablecode;
		this.CClasscode = CClasscode;
		this.CClassname = CClassname;
		this.CNotnull = CNotnull;
		this.CRegexp = CRegexp;
		this.LKeyno = LKeyno;
		this.CDefaultvalue = CDefaultvalue;
		this.CSelectorurl = CSelectorurl;
		this.LOrder = LOrder;
		this.CDescunit = CDescunit;
		this.CStylecss = CStylecss;
		this.CCaption = CCaption;
		this.CColspan = CColspan;
		this.CDatatype = CDatatype;
		this.CSysname = CSysname;
		this.CShowkeyvalue = CShowkeyvalue;
		this.CExtshowsql = CExtshowsql;
		this.CEventattr = CEventattr;
		this.CAttrauditstatus = CAttrauditstatus;
		this.CValueentertype = CValueentertype;
		this.CReadonly = CReadonly;
		this.CTempletmod = CTempletmod;
		this.CDictoptionignores = CDictoptionignores;
		this.CLimitnotnulloperatorflag = CLimitnotnulloperatorflag;
		this.CNotice = CNotice;
	}

	// Property accessors

	public Long getLTempletid() {
		return this.LTempletid;
	}

	public void setLTempletid(Long LTempletid) {
		this.LTempletid = LTempletid;
	}

	public Long getLTableid() {
		return this.LTableid;
	}

	public void setLTableid(Long LTableid) {
		this.LTableid = LTableid;
	}

	public String getCProcode() {
		return this.CProcode;
	}

	public void setCProcode(String CProcode) {
		this.CProcode = CProcode;
	}

	public String getCProtype() {
		return this.CProtype;
	}

	public void setCProtype(String CProtype) {
		this.CProtype = CProtype;
	}

	public String getCProname() {
		return this.CProname;
	}

	public void setCProname(String CProname) {
		this.CProname = CProname;
	}

	public String getCViewname() {
		return this.CViewname;
	}

	public void setCViewname(String CViewname) {
		this.CViewname = CViewname;
	}

	public String getCTablecode() {
		return this.CTablecode;
	}

	public void setCTablecode(String CTablecode) {
		this.CTablecode = CTablecode;
	}

	public String getCClasscode() {
		return this.CClasscode;
	}

	public void setCClasscode(String CClasscode) {
		this.CClasscode = CClasscode;
	}

	public String getCClassname() {
		return this.CClassname;
	}

	public void setCClassname(String CClassname) {
		this.CClassname = CClassname;
	}

	public String getCNotnull() {
		return this.CNotnull;
	}

	public void setCNotnull(String CNotnull) {
		this.CNotnull = CNotnull;
	}

	public String getCRegexp() {
		return this.CRegexp;
	}

	public void setCRegexp(String CRegexp) {
		this.CRegexp = CRegexp;
	}

	public Long getLKeyno() {
		return this.LKeyno;
	}

	public void setLKeyno(Long LKeyno) {
		this.LKeyno = LKeyno;
	}

	public String getCDefaultvalue() {
		return this.CDefaultvalue;
	}

	public void setCDefaultvalue(String CDefaultvalue) {
		this.CDefaultvalue = CDefaultvalue;
	}

	public String getCSelectorurl() {
		return this.CSelectorurl;
	}

	public void setCSelectorurl(String CSelectorurl) {
		this.CSelectorurl = CSelectorurl;
	}

	public Long getLOrder() {
		return this.LOrder;
	}

	public void setLOrder(Long LOrder) {
		this.LOrder = LOrder;
	}

	public String getCDescunit() {
		return this.CDescunit;
	}

	public void setCDescunit(String CDescunit) {
		this.CDescunit = CDescunit;
	}

	public String getCStylecss() {
		return this.CStylecss;
	}

	public void setCStylecss(String CStylecss) {
		this.CStylecss = CStylecss;
	}

	public String getCCaption() {
		return this.CCaption;
	}

	public void setCCaption(String CCaption) {
		this.CCaption = CCaption;
	}

	public String getCColspan() {
		return this.CColspan;
	}

	public void setCColspan(String CColspan) {
		this.CColspan = CColspan;
	}

	public String getCDatatype() {
		return this.CDatatype;
	}

	public void setCDatatype(String CDatatype) {
		this.CDatatype = CDatatype;
	}

	public String getCSysname() {
		return this.CSysname;
	}

	public void setCSysname(String CSysname) {
		this.CSysname = CSysname;
	}

	public String getCShowkeyvalue() {
		return this.CShowkeyvalue;
	}

	public void setCShowkeyvalue(String CShowkeyvalue) {
		this.CShowkeyvalue = CShowkeyvalue;
	}

	public String getCExtshowsql() {
		return this.CExtshowsql;
	}

	public void setCExtshowsql(String CExtshowsql) {
		this.CExtshowsql = CExtshowsql;
	}

	public String getCEventattr() {
		return this.CEventattr;
	}

	public void setCEventattr(String CEventattr) {
		this.CEventattr = CEventattr;
	}

	public String getCAttrauditstatus() {
		return this.CAttrauditstatus;
	}

	public void setCAttrauditstatus(String CAttrauditstatus) {
		this.CAttrauditstatus = CAttrauditstatus;
	}

	public String getCValueentertype() {
		return this.CValueentertype;
	}

	public void setCValueentertype(String CValueentertype) {
		this.CValueentertype = CValueentertype;
	}

	public String getCReadonly() {
		return this.CReadonly;
	}

	public void setCReadonly(String CReadonly) {
		this.CReadonly = CReadonly;
	}

	public String getCTempletmod() {
		return this.CTempletmod;
	}

	public void setCTempletmod(String CTempletmod) {
		this.CTempletmod = CTempletmod;
	}

	public String getCDictoptionignores() {
		return this.CDictoptionignores;
	}

	public void setCDictoptionignores(String CDictoptionignores) {
		this.CDictoptionignores = CDictoptionignores;
	}

	public String getCLimitnotnulloperatorflag() {
		return this.CLimitnotnulloperatorflag;
	}

	public void setCLimitnotnulloperatorflag(String CLimitnotnulloperatorflag) {
		this.CLimitnotnulloperatorflag = CLimitnotnulloperatorflag;
	}

	public String getCNotice() {
		return this.CNotice;
	}

	public void setCNotice(String CNotice) {
		this.CNotice = CNotice;
	}
}