<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>접종예약 조회결과</title>
<link rel="stylesheet" href="css/css.css">
</head>
<body>
    <jsp:include page="header/header.jsp" />

    <main>
        
        <c:if test="${detail != null}">
            <h2>예약번호: ${param.resvno}의 접종예약조회</h2>
            
            <table class="result-table">
                <tr>
                    <th>이름</th>
                    <td>${detail.pname}</td>
                    <th>주민번호</th>
                    <td>${detail.jumin}</td>
                    <th>성별</th>
                    <td>${detail.gender}</td>
                </tr>
                <tr>
                    <th>전화번호</th>
                    <td>${detail.tel}</td>
                    <th>예약일자</th>
                    <td>${detail.resvdate}</td>
                    <th>예약시간</th>
                    <td>${detail.resvyime}</td>
                </tr>
                 <tr>
                    <th>병원명</th>
                    <td>${detail.hospname}</td>
                    <th>대표전화</th>
                    <td>${detail.hosptel}</td>
                    <th>병원주소</th>
                    <td>${detail.hospaddr}</td>
                </tr>
                <tr>
                    <th>백신종류</th>
                    <td colspan="5">${detail.vname}</td>
                </tr>
            </table>
        </c:if>

        <c:if test="${detail == null}">
            <h2>접종예약정보가 존재하지않습니다.</h2>
            <p>입력한 예약번호: ${searchedResvno}</p>
        </c:if>

        <div class="form-buttons" style="text-align: center; margin-top: 20px;">
             <button type="button" onclick="history.back()">돌아가기</button>
        </div>

    </main>
    
    <jsp:include page="header/footer.jsp" />
</body>
</html>