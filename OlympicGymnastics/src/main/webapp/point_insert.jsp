<%-- /point_insert.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/header/header.jsp" />

<script>
    // 1. 유효성 검사 (공통 함수) 
    function validateForm() {
        const frm = document.frmPoint;
        
        // 1-1. 선수번호
        if (frm.player_no.value.trim() === "") {
            alert("선수번호가 입력되지 않았습니다.");
            frm.player_no.focus();
            return false;
        }
        
        // 1-2. 심사위원 점수 (1~4)
        const points = [frm.point_1, frm.point_2, frm.point_3, frm.point_4];
        for (let i = 0; i < points.length; i++) {
            let p = points[i];
            if (p.value.trim() === "") {
                alert("심사원" + (i+1) + " 점수가 입력되지 않았습니다.");
                p.focus();
                return false;
            }
            
            let score = parseInt(p.value);
            if (isNaN(score) || score < 1 || score > 10) {
                alert("심사원" + (i+1) + " 점수는 1과 10 사이의 숫자여야 합니다."); 
                p.focus();
                return false;
            }
        }
        return true; // 모든 검사 통과
    }
    
    // 2. 폼 제출 (등록/수정 분기)
    function submitAction(actionType) {
        const frm = document.frmPoint;
        
        // 2-1. 유효성 검사
        if (!validateForm()) {
            return; // 제출 중단
        }
        
        // 2-2. actionType에 따라 폼의 action URL 변경 및 알림
        if (actionType === 'insert') {
            // "등록" 버튼 클릭 시
            frm.action = "${pageContext.request.contextPath}/PointInsertServlet";
            alert("심사점수가 정상적으로 등록 되었습니다."); 
            
        } else if (actionType === 'update') {
            // "수정" 버튼 클릭 시
            frm.action = "${pageContext.request.contextPath}/PointUpdateServlet";
            alert("심사점수가 정상적으로 수정되었습니다.");
        }
        
        // 2-3. 폼 제출
        frm.submit();
    }
    
    // 3. 다시쓰기
    function resetForm() {
        if (confirm("정보를 지우고 처음부터 다시 입력 합니다.")) { 
            document.frmPoint.reset();
            document.frmPoint.player_no.focus();
        }
    }
</script>

<h2>심사점수 등록</h2>

<%-- 폼의 action은 스크립트에서 동적으로 설정 --%>
<form name="frmPoint" method="post">
    <table class="form-table">
        <tr>
            <th>선수번호</th>
            <td><input type="text" name="player_no" maxlength="4"></td>
        </tr>
        <tr>
            <th>심사원1 점수</th>
            <td><input type="number" name="point_1" min="1" max="10"> (최소1, 최대 10)</td>
        </tr>
        <tr>
            <th>심사원2 점수</th>
            <td><input type="number" name="point_2" min="1" max="10"> (최소1, 최대 10)</td>
        </tr>
        <tr>
            <th>심사원3 점수</th>
            <td><input type="number" name="point_3" min="1" max="10"> (최소1, 최대 10)</td>
        </tr>
        <tr>
            <th>심사원4 점수</th>
            <td><input type="number" name="point_4" min="1" max="10"> (최소1, 최대 10)</td>
        </tr>
    </table>
    
    <div class="btn-group">
        <button type="button" class="btn-submit" onclick="submitAction('insert')">등록</button> 
        <button type="button" class="btn-submit" onclick="submitAction('update')">수정</button>
        <button type="button" class="btn-reset" onclick="resetForm()">다시쓰기</button>
    </div>
</form>

<jsp:include page="/header/footer.jsp" />