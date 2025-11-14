<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>백신접종예약 등록</title>
<link rel="stylesheet" href="css/css.css">
</head>
<body>
    <jsp:include page="header/header.jsp" />

    <main>
        <h2>백신접종예약 등록</h2>
        
        <form name="frm" method="post" action="ReservationServlet" class="form-container">
            <table class="form-table">
                <tr>
                    <th>접종예약번호</th>
                    <td><input type="text" name="resvno" placeholder="예) 20210001"></td>
                </tr>
                <tr>
                    <th>주민번호</th>
                    <td><input type="text" name="jumin" placeholder="예) 710101-1000001"></td>
                </tr>
                <tr>
                    <th>백신코드</th>
                    <td><input type="text" name="vcode" placeholder="V001-V003"></td>
                </tr>
                <tr>
                    <th>병원코드</th>
                    <td><input type="text" name="hospcode" placeholder="예) H001"></td>
                </tr>
                <tr>
                    <th>예약일자</th>
                    <td><input type="text" name="resvdate" placeholder="예) 20211231"></td>
                </tr>
                <tr>
                    <th>예약시간</th>
                    <td><input type="text" name="resvyime" placeholder="예) 1230"></td>
                </tr>
                <tr class="form-buttons">
                    <td colspan="2">
                        <button type="button" onclick="fn_submit()">등록</button>
                        <button type="button" onclick="fn_reset()">다시쓰기</button>
                    </td>
                </tr>
            </table>
        </form>
    </main>

    <jsp:include page="header/footer.jsp" />

    <script type="text/javascript">
        // 등록 버튼 클릭 시 유효성 검사 함수
        function fn_submit() {
            const frm = document.frm; // 폼 객체

            // 1. 접종예약번호 검사 [cite: 36, 37]
            if (frm.resvno.value === "") {
                alert("접종예약번호가 입력되지 않았습니다!");
                frm.resvno.focus(); [cite: 37]
                return false; // 제출 중단
            }

            // 2. 주민번호 검사 [cite: 39, 40]
            if (frm.jumin.value === "") {
                alert("주민번호가 입력되지 않았습니다!");
                frm.jumin.focus();
                return false;
            }

            // 3. 백신코드 검사 [cite: 39, 40]
            if (frm.vcode.value === "") {
                alert("백신코드가가 입력되지 않았습니다!");
                frm.vcode.focus();
                return false;
            }

            // 4. 병원코드 검사 [cite: 39, 40]
            if (frm.hospcode.value === "") {
                alert("병원코드가 입력되지 않았습니다!");
                frm.hospcode.focus();
                return false;
            }

            // 5. 예약일자 검사 [cite: 39, 40]
            if (frm.resvdate.value === "") {
                alert("예약일자가 입력되지 않았습니다!");
                frm.resvdate.focus();
                return false;
            }

            // 6. 예약시간 검사 [cite: 39, 40]
            if (frm.resvyime.value === "") {
                alert("예약시간이 입력되지 않았습니다!");
                frm.resvyime.focus();
                return false;
            }

            // 모든 검사를 통과하면 폼 제출
            frm.submit(); [cite: 41]
        }

        // 다시쓰기 버튼 클릭 시 함수
        function fn_reset() {
            // "정보를 지우고 처음부터 다시 입력 합니다" 확인창 띄우기 [cite: 42]
            const confirmed = confirm("정보를 지우고 처음부터 다시 입력 합니다"); [cite: 42]
            
            if (confirmed) {
                document.frm.reset(); // 폼의 모든 입력값 초기화
                document.frm.resvno.focus(); // "매출번호"(접종예약번호)로 포커스 이동 [cite: 43]
            }
        }
    </script>
</body>
</html>