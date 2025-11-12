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
    <title>강사 정보 조회</title>
    <link rel="stylesheet" href="${root}/css/style.css">
    
    <%-- list.jsp 전용 CSS (테이블 스타일) --%>
    <style>
        section {
            display: block;
            width: 80%;
            margin: 0 auto;
            padding-top: 120px;
        }
        
        section h2 {
            text-align: center;
            margin-bottom: 25px;
            font-size: 1.8em;
        }
        
        /* 테이블 스타일 */
        .list-table {
            width: 100%;
            border-collapse: collapse; /* 테두리 겹침 방지 */
            text-align: center;
        }
        
        .list-table th,
        .list-table td {
            border: 1px solid #ddd;
            padding: 12px;
            font-size: 1.0em;
        }
        
        .list-table th {
            background-color: #f8f8f8;
            font-weight: bold;
        }
        
        /* 수강료 (우측 정렬 - PDF에는 명시 안됐으나 통상적) */
        .list-table td:nth-child(4) {
            text-align: right;
        }
        
        /* 강사코드 링크 스타일 */
        .list-table td a {
            text-decoration: underline;
            color: #007bff;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <%-- 상단 헤더(메뉴) 포함 --%>
    <%@ include file="../layout/header.jsp" %>
    
    <section>
        <h2>강사 정보 조회</h2>
        
        <table class="list-table">
            <thead>
                <%-- PDF 3페이지 테이블 헤더 참조 [cite: 42] --%>
                <tr>
                    <th>강사코드</th>
                    <th>강의명</th>
                    <th>강사명</th>
                    <th>수강료</th>
                    <th>강사자격획득일</th>
                </tr>
            </thead>
            <tbody>
                <%-- 
                  서블릿에서 보낸 'teacherList'를 반복문(forEach)으로 순회 
                  var="dto" : 리스트의 각 항목을 'dto'라는 변수명으로 사용
                --%>
                <c:forEach var="dto" items="${teacherList}">
                <tr>
                    <td>
                        <%-- (요구사항 4-①) 강사코드를 누르면 수정 화면으로 이동 [cite: 46] --%>
                        <%-- 링크는 추후 만들 '수정 서블릿'을 바라보게 함 --%>
                        <a href="${root}/teacher/edit.do?code=${dto.teacher_code}">${dto.teacher_code}</a>
                    </td>
                    <td>${dto.class_name}</td>
                    <td>${dto.teacher_name}</td>
                    <td>
                        <%-- (요구사항 3-②) 수강료: ₩ 기호 및 3자리 콤마 [cite: 44] --%>
                        <fmt:formatNumber value="${dto.class_price}" 
                                      type="currency" 
                                      currencySymbol="₩" 
                                      maxFractionDigits="0" />
                    </td>
                    <td>
                        <%-- (요구사항 3-①) 날짜 형식: YYYY년 MM월 DD일 [cite: 43] --%>
                        <%-- '20220101'을 '2022', '01', '01'로 분리(substring) --%>
                        ${fn:substring(dto.teacher_regist_date, 0, 4)}년 
                        ${fn:substring(dto.teacher_regist_date, 4, 6)}월 
                        ${fn:substring(dto.teacher_regist_date, 6, 8)}일
                    </td>
                </tr>
                </c:forEach>
                
                <%-- 데이터가 없을 경우 --%>
                <c:if test="${empty teacherList}">
                    <tr>
                        <td colspan="5">등록된 강사 정보가 없습니다.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </section>

    <%-- 하단 푸터 포함 --%>
    <%@ include file="../layout/footer.jsp" %>
</body>
</html>