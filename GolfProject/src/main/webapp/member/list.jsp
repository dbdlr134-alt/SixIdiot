<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% request.setAttribute("root", request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 정보 조회</title>
    <link rel="stylesheet" href="${root}/css/style.css">
    
    <%-- 강사 조회(list.jsp)와 동일한 테이블 스타일 사용 --%>
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
        
        /* 회원번호, 회원명 링크 스타일 */
        .list-table td a {
            text-decoration: underline;
            color: #007bff;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <%-- 상단 헤더 (경로 ../) --%>
    <%@ include file="../layout/header.jsp" %>
    
    <section>
        <h2>회원 정보 조회</h2>
        
        <table class="list-table">
            <thead>
                <%-- PDF 4페이지 테이블 헤더 참조 --%>
                <tr>
                    <th>회원번호</th>
                    <th>회원명</th>
                    <th>전화번호</th>
                    <th>주소</th>
                    <th>가입일자</th>
                    <th>회원등급</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dto" items="${memberList}">
                <tr>
                    <td>
                        <%-- (요구사항 7-①) 회원번호 선택하면 수정 화면으로 이동 --%>
                        <a href="${root}/member/edit.do?no=${dto.c_no}">${dto.c_no}</a>
                    </td>
                    <td>
                        <%-- (요구사항 7-①) 회원명 선택하면 수정 화면으로 이동 --%>
                        <a href="${root}/member/edit.do?no=${dto.c_no}">${dto.c_name}</a>
                    </td>
                    <td>
                        <%-- PDF 4페이지 전화번호 형식 (010-1111-2222) [cite: 70] --%>
                        <%-- 01011112222 -> 010-1111-2222 --%>
                        ${fn:substring(dto.phone, 0, 3)}-${fn:substring(dto.phone, 3, 7)}-${fn:substring(dto.phone, 7, 11)}
                    </td>
                    <td>${dto.address}</td>
                    <td>
                        <%-- PDF 4페이지 가입일자 형식 (YYYY년MM월DD일) [cite: 72] --%>
                        ${fn:substring(dto.regist_date, 0, 4)}년 
                        ${fn:substring(dto.regist_date, 4, 6)}월 
                        ${fn:substring(dto.regist_date, 6, 8)}일
                    </td>
                    <td>${dto.c_type}</td>
                </tr>
                </c:forEach>
                
                <c:if test="${empty memberList}">
                    <tr>
                        <td colspan="6">등록된 회원 정보가 없습니다.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </section>

    <%-- 하단 푸터 (경로 ../) --%>
    <%@ include file="../layout/footer.jsp" %>
</body>
</html>