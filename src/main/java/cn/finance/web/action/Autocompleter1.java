package cn.finance.web.action;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

public class Autocompleter1 extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private static String[] staticLanguages = { "Actionscript (Flash)",
			"ABAP Objects", "Ada", "Aleph", "AppleScript", "Beta", "BlitzMax",
			"Boo", "C++", "C#", "Cecil", "Clarion", "Cobol ISO 2002",
			"CoDeSys", "CFML (ColdFusion Markup Language)",
			"Common Lisp Object System (CLOS)", "Component Pascal",
			"CorbaScript", "D", "Delphi", "Eiffel", "Fpii",
			"Fortran - ab Fortran 2003", "Gambas", "IDL", "IDLscript",
			"incr Tcl", "Java", "JavaScript / ECMAScript", "Lexico", "Lingo",
			"Modula-3", "Modelica", "NewtonScript", "Oberon", "Objective-C",
			"Objective CAML", "Object Pascal", "Perl", "PHP", "PowerBuilder",
			"Progress OpenEdge", "Python", "Ruby", "R", "Sather", "Scala",
			"Seed7", "Self", "Simula", "Smalltalk", "SuperCollider",
			"Superx++", "STOOOP", "Visual Basic", "Visual Basic .NET (VB.NET)",
			"Visual Basic Script", "Visual Objects", "XBase", "XOTcl", "Zonnon" };

	private String term;
	private String[] languages = Autocompleter1.staticLanguages;

	// @Actions( { @Action(value = "/autocompleter1", results = { @Result(location = "autocompleter1.jsp", name = "success") }) })
	public String execute() throws Exception {
		if (term != null && term.length() > 1) {
			ArrayList<String> tmp = new ArrayList<String>();
			for (int i = 0; i < staticLanguages.length; i++) {
				if (StringUtils.contains(staticLanguages[i].toLowerCase(), term
						.toLowerCase())) {
					tmp.add(staticLanguages[i]);
				}
			}
			languages = tmp.toArray(new String[tmp.size()]);
		}
		return SUCCESS;
	}

	public String[] getLanguages() {
		return languages;
	}

	public void setTerm(String term) {
		this.term = term;
	}
}
