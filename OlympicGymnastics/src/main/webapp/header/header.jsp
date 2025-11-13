<%-- /header/header.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>올림픽 체조경기 관리</title>

<%-- 
    /css/style.css 파일을 절대 경로로 링크합니다.
--%>
<link rel="stylesheet" type="text/css" 
      href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>

<header>
    <h1>올림픽 체조경기 관리 프로그램 Ver 1.0</h1>
</header>

<%-- 
  <nav> 태그는 /css/style.css 에 의해 
  position: sticky; (상단 고정)가 적용된 상태입니다. 
--%>
<nav>
    <ul>
        <%-- 모든 링크를 ${pageContext.request.contextPath} (루트) 기준으로 설정 --%>
        
        <%-- 1. 홈 --%>
        <li><a href="${pageContext.request.contextPath}/index.jsp">홈</a></li>
        
        <%-- 2. 참가자 등록 (폼 JSP로 이동) --%>
        <li><a href="${pageContext.request.contextPath}/player_insert.jsp">참가자 등록</a></li>
        
        <%-- 3. 참가자 조회 (목록 서블릿 호출) --%>
        <li><a href="${pageContext.request.contextPath}/PlayerListServlet">참가자 조회</a></li>
        
        <%-- 4. 심사점수 등록 (폼 JSP로 이동) --%>
        <li><a href="${pageContext.request.contextPath}/point_insert.jsp">심사점수 등록</a></li>
        
        <%-- 5. 심사점수 조회 (목록 서블릿 호출) --%>
        <li><a href="${pageContext.request.contextPath}/PointListServlet">심사점수 조회</a></li>
        
        <%-- 6. 참가자 등수 조회 (목록 서블릿 호출) --%>
        <li><a href="${pageContext.request.contextPath}/RankingListServlet">참가자 등수 조회</a></li>
    </ul>
</nav>

<%-- 
  메인 컨텐츠 영역 시작 태그
  (닫는 </section> 태그는 footer.jsp에 있습니다) 
--%>
<section>