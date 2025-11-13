<%-- /player_list.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- JSTL 사용을 위한 taglib 선언 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/header/header.jsp" />

<h2>참가자 조회</h2>

<%-- 
  CSS에서 정의한 .data-table 스타일 적용 
  PDF 의 모호한 헤더 대신, 명확하게 6개 컬럼으로 분리합니다.
--%>
<table class="data-table">
    <thead>
        <tr>
            <th>선수번호</th>
            <th>선수명</th>
            <th>국적</th>
            <th>생년월일</th>
            <th>성별</th>
            <th>기술난이도</th>
        </tr>
    </thead>
    <tbody>
        <%-- 
          서블릿에서 보낸 "playerList"를 <c:forEach>로 반복
          var="player"는 각 항목을 의미하는 변수명
        --%>
        <c:forEach var="player" items="${playerList}">
            <tr>
                <td>${player.player_no}</td>
                <td>${player.name}</td>
                <td>${player.nation_name}</td>
                <td>${player.birth_formatted}</td> <%-- DAO에서 가공한 값 --%>
                <td>${player.gender}</td>          <%-- DAO에서 가공한 값 --%>
                <td>${player.skill_name}</td>
            </tr>
        </c:forEach>
        
        <%-- 목록이 비어있을 경우 --%>
        <c:if test="${empty playerList}">
            <tr>
                <td colspan="6">등록된 선수가 없습니다.</td>
            </tr>
        </c:if>
    </tbody>
</table>

<jsp:include page="/header/footer.jsp" />