package org.gmart.base.data.transform.string;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class StrUnaryOps {
	public static String guil(String str) {
		return "\"" + str + "\"";
	}
	public static String getSimpleNameOfQualified(String qualifiedName) {
		int lastIndexOf = qualifiedName.lastIndexOf(".");
		return lastIndexOf == -1 ? qualifiedName : qualifiedName.substring(lastIndexOf+1);
	}
	public static String uncapitalize(String str) {
		return str.substring(0,1).toLowerCase() + str.substring(1);
	}
	public static String capitalize(String str) {
		return str.substring(0,1).toUpperCase() + str.substring(1);
	}
	public static char getCloserDelimiterOf(char leftDelimiter) {
		return leftToRightDelimiter.get(Character.valueOf(leftDelimiter).toString()).charAt(0);
	}
	public static String getCloserDelimiterOf(String leftDelimiter) {
		return leftToRightDelimiter.get(leftDelimiter);
	}
	public final static Map<String, String> leftToRightDelimiter;
	static {
		leftToRightDelimiter = new HashMap<>();
		leftToRightDelimiter.put("(", ")");
		leftToRightDelimiter.put("[", "]");
		leftToRightDelimiter.put("{", "}");
		leftToRightDelimiter.put("<", ">");
		leftToRightDelimiter.put("\"", "\"");
		leftToRightDelimiter.put("'", "'");
		leftToRightDelimiter.put("|", "|");
		leftToRightDelimiter.put("```", "```");
		leftToRightDelimiter.put("/", "\\");
		leftToRightDelimiter.put("\\", "/");
	}
	public static String getSpaces(int spaceNb){
		return StringUtils.repeat(" ", spaceNb);
	}
}
