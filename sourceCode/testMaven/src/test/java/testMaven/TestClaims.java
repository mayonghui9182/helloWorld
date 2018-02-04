package testMaven;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ma.test.testOfficeParse.ReadWriteExcel;

public class TestClaims {

	private static String path = "E:\\document\\工作\\业务需求-债权产品要素_20180123.xlsx";

	public static void main(String[] args) {
		// 获取excel文件流
		Workbook workbook = ReadWriteExcel.getWorkbok(new File(path));
		Sheet sheet = workbook.getSheet("default");
		List<List<String>> rows = ReadWriteExcel.readSheet(sheet);
		Map<String, Tpdttempletpro> proMap = getPro("default");
		Tpdttempletpro pro = null;
		int rowLen = rows.size();
		for (int i = 1; i < rowLen; i++) {
			List<String> cells = rows.get(i);
			pro = proMap.get(cells.get(5));
			if (pro != null) {
				validatePro(pro, cells);
			}
		}
	}

	private static void validatePro(Tpdttempletpro pro, List<String> cells) {
		String name = cells.get(5);
		String dictionary = cells.get(6);
		String type = cells.get(7);
		String isNotnull = cells.get(8);
		if (!validateType(pro.getCProtype(), cells.get(7))) {
			System.out.print(name + "类型不对：" + pro.getCProtype() + ":" + cells.get(7));
		}
		if (!validateNotnull(pro.getCNotnull(), cells.get(8))) {
			System.out.print(name + "是否必填不对：" + pro.getCNotnull() + ":" + cells.get(9));
		}
		if (type.equals("多选") || type.equals("单选") || type.equals("下拉选项")) {
			Long Keyno = pro.getLKeyno();
			if (Keyno != 0) {
				Map<String,Tdictionary> dic = getDic(Keyno);
				String[] dicExcel = cells.get(6).split(",");
				for (String string : dicExcel) {
					if(dic.get(string)==null)
						System.out.print(name + "字典项不对：" + string + ":" + Keyno);

				}
				
			} else {
				System.out.print(name + "少字典：");
			}
		}
	}

	public static boolean validateType(String tableType, String excelType) {
		switch (tableType) {
		case "TEXT":
			if (excelType.equals("文本") || excelType.equals("数字"))
				return true;
			else
				return false;
		case "TEXTARES":
			if (excelType.equals("文本"))
				return true;
			else
				return false;
		case "DICTMULT":
			if (excelType.equals("多选"))
				return true;
			else
				return false;
		case "SELECTOR":
			if (excelType.equals("文本"))
				return true;
			else
				return false;
		case "DICT":
			if (excelType.equals("单选") || excelType.equals("下拉选项"))
				return true;
			else
				return false;
		case "DATE":
			if (excelType.equals("日期"))
				return true;
			else
				return false;
		case "SELECTORUPLOAD":
			if (excelType.equals("弹出框"))
				return true;
			else
				return false;
		default:
			break;
		}
		return false;
	}

	public static boolean validateNotnull(String table, String excel) {
		String excel_ = null;
		switch (table) {
		case "是":
			excel_ = "1";
			break;
		default:
			excel_ = "0";
			break;
		}
		if (excel_.equals(table)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validateDictionary(String keyno, String name) {
		return false;
	}

	public static Map<String, Tpdttempletpro> getPro(String templateCode) {
		SessionFactory SessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = SessionFactory.openSession();
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select * from tpdttempletpro p where l_templetid = 6 and c_templetmod = ? or l_tableid in (select tb.l_tableid from tpdttemplettable tb where tb.l_templetid = 6 and tb.c_templetmod = ？) order by p.l_tableid DESC,p.c_classcode,p.l_order ");
		List<Tpdttempletpro> resultList = session.createNativeQuery(sql.toString()).setParameter(0, templateCode)
				.setParameter(0, templateCode).addEntity(Tpdttempletpro.class).getResultList();
		Map<String, Tpdttempletpro> proMap = new HashMap<String, Tpdttempletpro>();
		for (Tpdttempletpro object : resultList) {
			proMap.put(object.getCViewname(), object);
		}
		return proMap;
	}

	public static Map<String,Tdictionary> getDic(long keyNo) {
		SessionFactory SessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = SessionFactory.openSession();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM tdictionary t WHERE t.l_keyno=? AND t.c_sysname='FUNDCRM' AND t.c_keyvalue!='#'");
		List<Tdictionary> resultList = session.createNativeQuery(sql.toString()).setParameter(0, keyNo)
				.addEntity(Tdictionary.class).getResultList();
		Map<String,Tdictionary> dicMap=new HashMap<String, Tdictionary>();
		for (Tdictionary tdictionary : resultList) {
			dicMap.put(tdictionary.getCCaption(), tdictionary);
		}
		return dicMap;
	}
}
