<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL 사용을 위한 taglib 선언 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<% request.setAttribute("root", request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원별 수강정보 조회</title>
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
        
        /* 수강료 (우측 정렬) */
        .list-table td:last-child {
            text-align: right;
        }
    </style>
</head>
<body>
    <%-- 상단 헤더 (경로 ../) --%>
    <%@ include file="../layout/header.jsp" %>
    
    <section>
        <h2>회원별 수강정보 조회</h2> [cite: 114]
        
        <table class="list-table">
            <thead>
                <tr>
                    <th>수강월</th>
                    <th>회원번호</th>
                    <th>회원명</th>
                    <th>강의명</th>
                    <th>강사명</th>
                    <th>강의장소</th>
                    <th>수강료</th>
                </tr>
            </thead>
            <tbody>
                <%-- 
                  서블릿에서 보낸 'classList'를 반복문(forEach)으로 순회 
                  (var="dto" : 리스트의 각 항목(MemberClassViewDTO)을 'dto' 변수로 사용)
                --%>
                <c:forEach var="dto" items="${classList}">
                <tr>
                    <td>
                        <%-- (요구사항 9-①) 수강월: YYYY년 MM월 [cite: 116] --%>
                        <%-- '202203' -> '2022', '03' --%>
                        ${fn:substring(dto.regist_month, 0, 4)}년 
                        ${fn:substring(dto.regist_month, 4, 6)}월
                    </td>
                    <td>${dto.c_no}</td>
                    <td>${dto.c_name}</td>
                    <td>${dto.class_name}</td>
                    <td>${dto.teacher_name}</td>
                    <td>${dto.class_area}</td>
                    <td>
                        <%-- (요구사항 9-②) 수강료: ₩ 기호 및 3자리 콤마 [cite: 117] --%>
                        <fmt:formatNumber value="${dto.tuition}" 
                                      type="currency" 
                                      currencySymbol="₩" 
                                      maxFractionDigits="0" />
                    </td>
                </tr>
                </c:forEach>
                
                <%-- 데이터가 없을 경우 --%>
                <c:if test="${empty classList}">
                    <tr>
                        <td colspan="7">수강 내역이 없습니다.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </section>

    <%-- 하단 푸터 (경로 ../) --%>
    <%@ include file="../layout/footer.jsp" %>
</body>
</html>