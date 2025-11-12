<%-- /layout/header.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL <c:set> 태그를 사용하기 위해 core taglib 선언 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- 
  ContextPath를 'root' 변수에 저장합니다.
  / (루트) 경로를 기준으로 링크를 생성하여 
  어느 페이지에서 include 되어도 경로가 깨지지 않게 합니다.
--%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<header>
    <h1>
        <%-- PDF 2페이지 제목 --%>
        <a href="${root}/">골프연습장 회원관리 프로그램 Ver 1.0</a>
    </h1>
    
    <%-- PDF 요구사항 및 서블릿 매핑이 적용된 최종 메뉴 --%>
    <nav>
        <ul>
            <%-- 1. 강사등록 (TeacherAddServlet) --%>
            <li><a href="${root}/teacher_add.do">강사등록</a></li>
            
            <%-- 2. 강사조회 (TeacherListServlet) --%>
            <li><a href="${root}/teacher/list.do">강사조회</a></li>
            
            <%-- 3. 회원등록 (MemberAddServlet) --%>
            <li><a href="${root}/member_add.do">회원등록</a></li>
            
            <%-- 4. 회원조회 (MemberListServlet) - [사용자 요청으로 추가] --%>
            <li><a href="${root}/member/list.do">회원조회</a></li>
            
            <%-- 5. 수강신청 (ClassAddServlet) --%>
            <li><a href="${root}/class_add.do">수강신청</a></li>
            
            <%-- 6. 회원별 수강조회 (MemberClassListServlet) --%>
            <li><a href="${root}/member/classList.do">회원별 수강조회</a></li>
            
            <%-- 7. 강사별매출통계 (TeacherSalesServlet) --%>
            <li><a href="${root}/stats/sales.do">강사별매출통계</a></li>
            
            <%-- 8. 홈으로 (index.jsp) --%>
            <li><a href="${root}/">홈으로</a></li>
        </ul>
    </nav>
</header>