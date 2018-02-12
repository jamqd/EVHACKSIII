<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Perspective.*, java.util.*, java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Perspective</title>
</head>
<body>
	<%
	String path = getServletContext().getRealPath("biasData.txt");	
	BingNewsSearch.run(request.getParameter("query"), path);
	%>
	
	<table>
		<tr>
			<th>Article 1</th>
			<th>Article 2</th>
		</tr>
		<tr>
			<td>
				<%
					out.println(BingNewsSearch.getArticle(0).getSource() + "\n" + BingNewsSearch.getArticle(0).getTitle() + "\n" + BingNewsSearch.getArticle(0).getURL() + "\n");
				%>
			</td>
			<td>
				<%
				out.println(BingNewsSearch.getArticle(1).getSource() + "\n" + BingNewsSearch.getArticle(1).getTitle() + "\n" + BingNewsSearch.getArticle(1).getURL() + "\n");
				%>
			</td>
		</tr>
		<tr>
			<td>
				<%
					out.println(BingNewsSearch.getArticleText(0) + "\n");
				%>
			</td>
			<td>
				<%
					out.println(BingNewsSearch.getArticleText(1) + "\n");
				%>
			</td>
		</tr>
	</table>
</body>
</html>