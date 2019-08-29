package JSON;

public class JqGridJSONObject {

	private String[] keyName;
	private String[] keyValue;

	public void putKey(String cols) {
		String[] str = cols.split(",");
		keyName = new String[str.length];
		for (int i = 0; i < str.length; i++) {
			keyName[i] = str[i];
		}
	}
	public void putVal(String cols) {
		String[] str = cols.split(",");
		keyValue = new String[str.length];
		for (int i = 0; i < str.length; i++) {
			keyValue[i] = str[i];
		}
	}

	public void setOption(String ops) {
		String[] option = ops.split(",");
		for (int i = 0; i < keyValue.length; i++) {
			switch (option[i]) {
			case "0":
				keyValue[i] += "'";
				break;
			case "1":
				keyValue[i] += "',align:'center'";
				break;
			case "2":
				keyValue[i] += "',align:'right'";
				break;
			case "3":
				keyValue[i] += "',sortable:false";
				break;
			}
		}
	}

	public String getValue() {
		String line = "[";
		for (String str : keyValue) {
			line += "{ name:'" + str + "},";
		}
		line += "{name:'btn',align:'center',formatter: delButton}";
		line += "]";
		return line;
	}

	public String getKey() {
		String keys = "[";
		for (String str : keyName) {
			keys += "\"" + str + "\",";
		}
		keys = keys.substring(0, keys.length() - 1);
		keys += "]";
		return keys;
	}
}
