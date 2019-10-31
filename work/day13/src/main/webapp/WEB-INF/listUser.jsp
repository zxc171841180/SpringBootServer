<%@ page pageEncoding="utf-8" 
contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>listUsers</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<body>
		<div id="wrap">
			<div id="top_content"> 
				<%@ include file="header.jsp" %>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						用户列表
					</h1>
					<table class="table">
						<tr class="table_header">
							<td>
								ID
							</td>
							<td>
								用户名
							</td>
							<td>
								密码
							</td>
							<td>
								邮箱
							</td>
							<td>
								操作
							</td>
						</tr>
						<c:forEach items="${users}" 
						var="u" varStatus="s">
						<tr class="row${s.index % 2 + 1}">
							<td>
								${u.id}
							</td>
							<td>
								${u.username}
							</td>
							<td>
								${u.pwd}
							</td>
							<td>
								${u.email}
							</td>
							<td>
								<a href="del.do?id=${u.id}" 
								onclick="return confirm('确定删除${u.username}吗?');">删除</a>&nbsp;
							</td>
						</tr>
						</c:forEach>
					</table>
					<p>
						<input type="button" class="button" 
						value="添加用户" 
						onclick="location='toAdd.do'"/>
					</p>
				</div>
			</div>
			<%@ include file="footer.jsp" %>
		</div>
	</body>
</html>


