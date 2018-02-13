<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Perspective.*, java.util.*, java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="results.css">
<title>Perspective</title>
</head>
<body>
	<%
		String path = getServletContext().getRealPath("biasData.txt");
		BingNewsSearch a = new BingNewsSearch();
		a.run(request.getParameter("query"), path);
	%>

	<div id = "content">
		<div id = "left">
			<%
				out.println(a.getArticle(0).getSource());
				out.println("<br/>");
				out.println(a.getArticle(0).getTitle());
				out.println("<br/>");
				out.println(a.getArticle(0).getURL());
				out.println("<br/>");
				out.println("<br/>");
				out.println(a.getArticleText(0));
				out.println("<br/>");
				out.println("<br/>");
			%>
		</div>
		<div id = "right">
			<%
			out.println(a.getArticle(1).getSource());
			out.println("<br/>");
			out.println(a.getArticle(1).getTitle());
			out.println("<br/>");
			out.println(a.getArticle(1).getURL());
			out.println("<br/>");
			out.println("<br/>");
			out.println(a.getArticleText(1));
			out.println("<br/>");
			out.println("<br/>");
			%>
		</div>
	</div>
</body>
</html>