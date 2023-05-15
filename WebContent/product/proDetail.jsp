<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />  
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common.jsp" %>
<title>상품 상세 보기</title>
<style>
.container-fluid { width:1280px; }
</style>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="content" style="padding-top:30px; margin-top:30px; border-top:3px solid #333; min-height:500px;">
	<div class="container-fluid">
		<h3>상품 > <a href="${path1 }/ProductList.do?cate=${pro.cate }">${cateMap.catename }</a> > ${pro.gname }</h3>
		<br><hr><br>
		<fmt:setLocale value="ko_kr" />
		<table class="table">
			<tbody>
				<tr>
					<td colspan="2">
						<img src='${path1 }/product/${pro.pic1 }' alt="${pro.gname }"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<img src='${path1 }/product/${pro.pic2 }' alt="${pro.gname }"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<img src='${path1 }/product/${pro.pic3 }' alt="${pro.gname }"/>
					</td>
				</tr>
				<tr>
					<th>상품명(상품코드)</th>
					<td>${pro.gname }(${pro.gcode })</td>
				</tr>
				<tr>
					<th>중량</th>
					<td>${pro.gram }</td>
				</tr>
				<tr>
					<th>설명</th>
					<td>${pro.mung }</td>
				</tr>
				<tr>
					<th>가격</th>
					<td><fmt:formatNumber value="${pro.price }" type="currency" /></td>
					<%-- <td>${pro.price }</td> --%>
				</tr>
				<tr>
					<th>남은 수량</th>
					<td>${pro.amount }</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="btn-group">
							<c:if test="${pro.amount>0 && !sid.equals('admin')}">
								<c:if test="${!empty sid}">
									<a href="${path1 }/InsertBasket.do?gcode=${pro.gcode}" class="btn btn-primary" role="button">장바구니</a>
									<a href="${path1 }/AddSales.do?gcode=${pro.gcode}&id=${sid }" class="btn btn-danger" role="button">구매</a>
								</c:if>
								<a href="${path1 }/ProductList.do?cate=${pro.cate}" class="btn btn-primary" role="button">목록</a>
							</c:if>
							<c:if test="${sid.equals('admin') }">
								<a href="${path1 }/ReceiptProduct.do?gcode=${pro.gcode }" class="btn btn-primary" role="button">입고</a>
								<a href="${path1 }/UpdateProduct.do?gcode=${pro.gcode }" class="btn btn-primary" role="button">수정</a>
								<a href="${path1 }/DeleteProduct.do?gcode=${pro.gcode }" class="btn btn-danger" role="button">삭제</a>
								<a href="${path1 }/ProductList.do?cate=${pro.cate}" class="btn btn-primary" role="button">목록</a>
							</c:if>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		<c:if test="${sid.equals('admin') }">
		<div class="btn-group">
			<a href="${path1 }/InsertProduct.do" class="btn btn-primary">상품 등록</a>
		</div>
		</c:if>
	</div>
</div>
<%@ include file="../../footer.jsp" %>
</body>
</html>