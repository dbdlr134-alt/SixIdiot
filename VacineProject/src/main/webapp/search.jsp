<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>접종예약 조회</title>
<link rel="stylesheet" href="css/css.css">
</head>
<body>
    <jsp:include page="header/header.jsp" />

    <main>
        <h2>접종예약 조회</h2>
        <form name="frmSearch" method="post" action="SearchServlet" class="form-container">
            <table class="form-table search-form">
                <tr>
                    <th>예약번호</th>
                    <td>
                        <input type="text" name="resvno" placeholder="예약번호를 입력 하시오.">
                    </td>
                </tr>
                <tr class="form-buttons">
                    <td colspan="2">
                        <button type="button" onclick="fn_search_check()">예약조회</button>
                    </td>
                </tr>
            </table>
        </form>
    </main>
    
    <jsp:include page="header/footer.jsp" />
    
    <script type="text/javascript">
        function fn_search_check() {
            const frm = document.frmSearch;
            
            // ① '예약번호' 입력항목 값이 입력되지 않는 경우
            if (frm.resvno.value === "") {
                alert("예약번호가 입력되지 않았습니다!");
                frm.resvno.focus(); // 포커스 이동
                return false;
            }
            
            // 검사 통과 시 서블릿으로 제출
            frm.submit();
        }
    </script>
</body>
</html>