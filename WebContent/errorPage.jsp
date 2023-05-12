<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>해당 페이지를 찾을 수 없습니다.</title>
	<meta name="subject" content="롯데글로벌로지스 벤치마킹 사이트">
    <meta name="keywords" content="롯데글로벌로지스, 택배, 운송, 서비스">
    <meta name="description" content="롯데글로벌로지스 홈페이지에 방문하신 것을 환영합니다.">
    <meta name="author" content="by. jeniy01">
    
    <link rel="shortcut icon" href="<%=path %>/images/favicon.ico">
    <!-- https://jeniy01.github.io/web1 -->
    <!-- 오픈그래프 설정 -->
    <meta name="og:site_name" content="롯데글로벌로지스">
    <meta name="og:title" content="포트폴리오 - 롯데글로벌로지스">
    <meta name="og:description" content="롯데글로벌로지스 홈페이지에 방문하신 것을 환영합니다.">
    <meta name="og:url" content="https://jeniy01.github.io/web1">
    <meta name="og:image" content="<%=path %>/images/thumbnail.jpg">
   
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&family=Noto+Sans+KR&display=swap" rel="stylesheet">

    <link href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=path %>/common.css">
    <link rel="stylesheet" href="<%=path %>/main.css">
    <link rel="stylesheet" href="<%=path %>/sub_common.css">
<style>
	.vs { height:40vh; }
	.content { background-image: url("./img/practice01.jpg"); }
	#page1 .page_tit { padding-top: 60px; }
	.table { width:680px; margin:4px auto; padding-top:20px; border-top:2px solid #333; }
	th {  text-align: justify;  line-height: 0; width:180px; padding-top:10px; padding-bottom: 10px;}
	td { padding-top:10px; padding-bottom: 10px; }
	th:after {  content: "";  display: inline-block;  width: 100%; }
	th:before {  content: "";  display: inline-block;  width: 100%; }
	.lb { display:block;  font-size:20px; }
	.indata { display:block; width:400px; height:24px; line-height:24px; padding:10px; }
	.btn { display:inline-block; outline:none; border:none; border-radius:8px; margin:16px; text-align: center; padding:10px 20px;  cursor:pointer; }
	.btn-primary { background: -moz-linear-gradient(top, #ca4747 0%, #c73333 100%); background: -webkit-linear-gradient(top, #ca4747 0%,#c73333 100%); background: linear-gradient(to bottom, #ca4747 0%,#c73333 100%); color:#fff; }
    .btn-cancle { background: -moz-linear-gradient(top, #afafaf 0%, #797979 44%, #a7a7a7 100%); background: -webkit-linear-gradient(top, #afafaf 0%,#797979 44%,#a7a7a7 100%); background: linear-gradient(to bottom, #afafaf 0%,#797979 44%,#a7a7a7 100%); color:#fff; }
	.page_tit { text-align:center; font-size:32px; }
	.title { text-align:center; }
	#tb1, .msg { width:600px; margin:0 auto; text-align:center; }
	.btn { margin-top:20px; }
	img { display:inline-block; max-width:100%; }
</style>
</head>
<body>
<div class="msg">
	<h1 class="title">죄송합니다. 점검중입니다.</h1>
	<img src="./img/practice01.jpg" alt="점검중입니다.">
	<button type="button" class="btn" onclick="javascript:history.back()">되돌아가기</button>
</div>
</body>
</html>