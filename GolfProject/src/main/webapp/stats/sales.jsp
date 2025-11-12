<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL 사용을 위한 taglib 선언 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<% request.setAttribute("root", request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>강사별 매출 통계</title>
    <link rel="stylesheet" href="${root}/css/style.css">
    
    <%-- 다른 list.jsp들과 동일한 테이블 스타일 사용 --%>
    <style>
        section {
            display: block;
            width: 80%;
            margin: 0 auto;
            padding-top: 120px;
        }
        section h2 { text-align: center; margin-bottom: 25px; font-size: 1.8em; }
        .list-table { width: 100%; border-collapse: collapse; text-align: center; }
        .list-table th, .list-table td { border: 1px solid #ddd; padding: 12px; }
        .list-table th { background-color: #f8f8f8; }
        
        /* (요구사항 10-①) 총 매출액 우측 정렬 */
        .sales-amount {
            text-align: right;
        }
    </style>
</head>
<body>
    <%-- 상단 헤더 (경로 ../) --%>
    <%@ include file="../layout/header.jsp" %>
    
    <section>
        <h2>강사별 매출 통계</h2>
        
        <table class="list-table">
            <thead>
                <%-- PDF 5페이지 테이블 헤더 참조 --%>
                <tr>
                    <th>강사코드</th>
                    <th>강사명</th>
                    <th>강의명</th>
                    <th>총매출액</th>
                </tr>
            </thead>
            <tbody>
                <%-- 
                  서블릿에서 보낸 'salesList'를 반복문(forEach)으로 순회 
                  (var="dto" : TeacherSalesDTO)
                --%>
                <c:forEach var="dto" items="${salesList}">
                <tr>
                    <td>${dto.teacher_code}</td>
                    <td>${dto.teacher_name}</td>
                    <td>${dto.class_name}</td>
                    
                    <%-- (요구사항 10-①) 우측 정렬 CSS 적용 --%>
                    <td class="sales-amount">
                        <%-- (요구사항 10-①) 화폐단위(₩) + 3자리 콤마 --%>
                        <fmt:formatNumber value="${dto.total_sales}" 
                                      type="currency" 
                                      currencySymbol="₩" 
                                      maxFractionDigits="0" />
                    </td>
                </tr>
                </c:forEach>
                
                <%-- 데이터가 없을 경우 --%>
                <c:if test="${empty salesList}">
                    <tr>
                        <td colspan="4">매출 통계 데이터가 없습니다.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </section>

    <%-- 하단 푸터 (경로 ../) --%>
    <%@ include file="../layout/footer.jsp" %>
</body>
</html>