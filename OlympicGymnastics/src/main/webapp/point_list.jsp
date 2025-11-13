<%-- /point_list.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- JSTL Core 태그 라이브러리 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- JSTL Formatting 태그 라이브러리 (숫자 형식 지정용) --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/header/header.jsp" />

<h2>심사점수 조회</h2>

<table class="data-table"> <%-- 공통 데이터 테이블 CSS 적용 --%>
    <thead>
        <tr>
            <th>선수번호</th>
            <th>선수명</th>
            <th>심사원1</th>
            <th>심사원2</th>
            <th>심사원3</th>
            <th>심사원4</th>
            <th>총점</th>
            <th>평균</th>
        </tr>
    </thead>
    <tbody>
        <%-- 서블릿에서 보낸 "pointList"를 반복 --%>
        <c:forEach var="point" items="${pointList}">
            <tr>
                <td>${point.player_no}</td>
                <td>${point.name}</td>
                <td>${point.point_1}</td>
                <td>${point.point_2}</td>
                <td>${point.point_3}</td>
                <td>${point.point_4}</td>
                <td>${point.total}</td>
                <td>
                    <%-- 
                      평균을 소수점 둘째 자리까지 표시 (예: 9.0 -> 9.00) 
                      
                    --%>
                    <fmt:formatNumber value="${point.ave}" pattern="0.00" />
                </td>
            </tr>
        </c:forEach>
        
        <%-- 목록이 비어있을 경우 --%>
        <c:if test="${empty pointList}">
            <tr>
                <td colspan="8">채점된 선수가 없습니다.</td>
            </tr>
        </c:if>
    </tbody>
</table>

<jsp:include page="/header/footer.jsp" />