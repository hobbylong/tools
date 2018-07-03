

import java.util.regex.Matcher;
import java.util.regex.Pattern;



//http://www.jb51.net/article/31251.htm

//\d 数字 
//\D 非数字 
//\w 单字字符(0-9,A-Z,a-z) 
//\W 非单字字符 
//\s 空白(空格符,换行符,回车符,制表符) 
//\S 非空白 
//[] 由方括号内的一个字符列表创建的自定义字符类 
//. 匹配任何单个字符 
//下面的字符将用于控制将一个子模式应用到匹配次数的过程. 
//? 重复前面的子模式0次到一次 
//* 重复前面的子模式0次或多次 
//+ 重复前面的子模式一次到多次

public class RegexUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegexUtil regex = new RegexUtil();
		regex.dbTable();
	}

	public boolean classRegex(String input) {
		String input1 = "public class About extends Activity implements OnScrollListener {\n";

		String regex = "(public|private)(\\s+)class(\\s+)(\\w+)([\\s\\S]*)";
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			System.out.println(matcher.group());
			return true;
		}
		return false;
	}

	public String functionRegex(String input) {
		String input1 = "public void About(String a){\n";

		String regex = "(public|private)(\\s+)(\\w+)(\\s+)(\\w+)(\\s*)[\\(](.*)[\\)](\\s*)([\\s\\S]*)";
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			String name = matcher.group(5);
			System.out.println(name);
			return name;
		}
		return null;
	}

	/** 构造函数 */
	public String constructFunctionRegex(String input) {
		String input1 = "public  About(String a){\n";

		String regex = "(public|private)(\\s+)(\\w+)(\\s*)[\\(](.*)[\\)]([\\s\\S]*)";
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			System.out.println(matcher.group(3));
			return matcher.group(3);
		}
		return null;
	}

	/** 变量 */
	public void variable() {
		String input1 = " private String maintain;//期望";

		String regex = "(public|private)(\\s+)(String|int|float|long)(\\s+)(\\w+)(\\s*)(;)(\\s*)(//)(\\w*)([\\s\\S]*)";
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(input1);
		if (matcher.find()) {
			System.out.println(matcher.group(3));
			System.out.println(matcher.group(5));
			System.out.println(matcher.group(11));

		}
	}

	public boolean importRegex(String input) {
		String input1 = "import com.a ;";

		String regex = "import(\\s+)(\\w+)(\\.+)([\\s\\S]*)";
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			System.out.println(matcher.group());
			return true;
		}
		return false;
	}

	public boolean leftBraceRegex(String input) {
		String input1 = "{";

		String regex = "([\\s\\S]*)(\\{)";
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			System.out.println(matcher.group());
			return true;
		}
		return false;
	}

	public boolean rightBraceRegex(String input) {
		String input1 = "}";

		String regex = "([\\s\\S]*)(\\})([\\s\\S]*)";
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			System.out.println(matcher.group());
			return true;
		}
		return false;
	}

	public ControllerBean findViewByIdRegex(String input) {
		// String input1 =
		// "queryButton = (Button) containView.findViewById(R.id.queryButton);ddddddddddddd";

		String regex = "[(](\\w+)[)](\\s*)(\\w+).findViewById[(]R.id.(\\w+)[)];";
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			System.out.println(input.length() + " " + matcher.end()
					+ matcher.group(1));

			ControllerBean regexBean = new ControllerBean();
			regexBean.end = matcher.end();
			regexBean.name = matcher.group(4);
			regexBean.type = matcher.group(1);
			return regexBean;
		}
		return null;
	}
	
	public void json()
	{
		String line="\"id\": 0,\"name\":\"zhan\"";
		String regex1 = "(\")(\\s*)(\\w+)(\\s*)(\")(\\s*)(:)(\\s*)(\"?)(\\w+)(\"?)(\\s*)(,*)";
		Pattern pattern1 = Pattern.compile(regex1);

		Matcher matcher1 = pattern1.matcher(line);
		while (matcher1.find()) {
			System.out.println(matcher1.group(3));
		}
	}
	
	
	public void dbTable()
	{
		String line=" CREATE TABLE pedometer_app_log (app_log_type   VARCHAR2(25)  NOT  null,app_start_time   VARCHAR2(25) NOT NULL PRIMARY KEY,dd";
		String regex2 = "(,?)(\\w+)(\\s+)(VARCHAR2|INTEGER|TEXT|REAL)(\\w*)(,?)";
		Pattern pattern2 = Pattern.compile(regex2);

		Matcher matcher2 = pattern2.matcher(line);
		while (matcher2.find()) {
		
	
			System.out.println(matcher2.group(2));
			
		}
	}

	public class ControllerBean {
		public int end;
		public String name;
		public String type;

	}

}
