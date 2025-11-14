<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>접종인원통계 조회</title>
<link rel="stylesheet" href="css/css.css">
</head>
<body>
    <jsp:include page="header/header.jsp" />

    <main>
        <h2>접종인원통계 조회</h2>
        
        <table class="stat-table">
            <thead>
                <tr>
                    <th>병원 코드</th>
                    <th>병원명</th>
                    <th>접종건수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${statList}">
                
                    <c:choose>
                        <%-- '총누계' 행일 경우 (DAO에서 병원명을 ''(빈칸)으로 줌) --%>
                        <c:when test="${item.hospcode == '총누계'}">
                            <tr class="total-row">
                                <td>${item.hospcode}</td> <%-- 1. '총누계' --%>
                                <td>${item.hospname}</td> <%-- 2. 빈 칸 (DAO에서 처리) --%>
                                <td>${item.count}</td> <%-- 3. 건수 --%>
                            </tr>
                        </c:when>
                        
                        <%-- 일반 병원 행일 경우 --%>
                        <c:otherwise>
                            <tr>
                                <td>${item.hospcode}</td>
                                <td>${item.hospname}</td>
                                <td>${item.count}</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tbody>
        </table>

    </main>
    
    <jsp:include page="header/footer.jsp" />
</body>
</html>