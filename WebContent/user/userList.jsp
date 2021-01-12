<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<table class="table table-bordered">
		<thead class="thead-light">
			<tr>
				<th class="text-center">유저네임</th>
				<th class="text-center">이메일</th>
				<th class="text-center" style="width: 8%"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${users }">
				<tr>
					<td class="text-center">${user.username }</td>
					<td class="text-center">${user.email }</td>
					<td class="text-center">
					<c:if test="${sessionScope.principal.id == user.id || sessionScope.principal.userRole =='admin'}">
					<form type="hidden"  action="/board/user?cmd=delete&id=${user.id }" method="POST">
						<button type="submit" class="btn btn-secondary btn-sm">삭제</button>
						</form>
					</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script src="/board/js/userDelete.js"></script>
</body>
</html>