<%-- /ranking_list.jsp (수정됨) --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/header/header.jsp" />

<h2>참가자 등수 조회</h2>

<table class="data-table">
    <thead>
        <tr>
            <th>선수번호</th>
            <th>선수명 / 국적</th>
            <th>기술난이도</th>
            <th>총점</th>
            <th>평균</th>
            <%-- [수정] 등수 컬럼에 스타일용 클래스 추가 --%>
            <th class="col-rank">등수</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="rank" items="${rankingList}">
            <tr>
                <td>${rank.player_no}</td>
                <td>${rank.name} / ${rank.nation_name}</td>
                <td>${rank.skill_name}</td>
                <td>${rank.total}</td>
                <td>
                    <fmt:formatNumber value="${rank.ave}" pattern="0.00" />
                </td>
                
                <%-- [수정] 등수 표시에 '배지' 스타일 적용 --%>
                <td class="col-rank">
                    <c:choose>
                        <c:when test="${rank.rank == '금'}">
                            <span class="rank-badge rank-gold">금</span>
                        </c:when>
                        <c:when test="${rank.rank == '은'}">
                            <span class="rank-badge rank-silver">은</span>
                        </c:when>
                        <c:when test="${rank.rank == '동'}">
                            <span class="rank-badge rank-bronze">동</span>
                        </c:when>
                        <c:otherwise>
                            <%-- 4등 이하는 다른 스타일 적용 --%>
                            <span class="rank-badge rank-other">${rank.rank}</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                
            </tr>
        </c:forEach>
        
        <c:if test="${empty rankingList}">
            <tr>
                <td colspan="6">채점된 선수가 없습니다.</td>
            </tr>
        </c:if>
    </tbody>
</table>

<jsp:include page="/header/footer.jsp" />