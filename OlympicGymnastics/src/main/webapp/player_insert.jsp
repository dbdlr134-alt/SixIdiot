<%-- /player_insert.jsp (상단 DAO/DTO 임포트 코드는 동일) --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.PlayerDAO" %>
<%@ page import="model.NationDTO" %>
<%@ page import="model.SkillDTO" %>
<%@ page import="java.util.List" %>
<%
    // (이 부분은 이전과 동일)
    PlayerDAO dao = new PlayerDAO();
    List<NationDTO> nationList = dao.getNationList();
    List<SkillDTO> skillList = dao.getSkillList();
%>

<jsp:include page="/header/header.jsp" />

<script>
    // (JavaScript 함수 부분은 이전과 동일)
    function checkForm() {
        const frm = document.frm;
        
        if (frm.player_no.value.length === 0) {
            alert("선수번호가 입력되지 않았습니다.");
            frm.player_no.focus();
            return false;
        }
        if (frm.name.value.length === 0) {
            alert("선수이름이 입력되지 않았습니다.");
            frm.name.focus();
            return false;
        }
        if (frm.birth.value.length === 0) {
            alert("생년월일이 입력되지 않았습니다.");
            frm.birth.focus();
            return false;
        }
        if (frm.nation_code.value.length === 0) {
            alert("국적코드가 선택되지 않았습니다.");
            frm.nation_code.focus();
            return false;
        }
        if (frm.skill_level.value.length === 0) {
            alert("기술난이도가 선택되지 않았습니다.");
            frm.skill_level.focus();
            return false;
        }
        
        // (요구사항대로 등록 알림 후 submit)
        alert("선수등록이 정상적으로 완료되었습니다.");
        frm.submit(); 
    }
    
    function resetForm() {
        alert("정보를 지우고 처음부터 다시 입력합니다.");
        document.frm.reset();
        document.frm.player_no.focus();
    }
</script>

<h2>체조경기 참가자 등록</h2>

<%-- 
  [수정됨] 
  1. action: PlayerServlet을 호출하도록 변경
  2. method: POST 방식 지정
--%>
<form name="frm" method="post" action="${pageContext.request.contextPath}/PlayerInsertServlet">

   

    <table class="form-table">
        <%-- (테이블 내용은 이전과 동일) --%>
        <tr>
            <th>선수번호</th>
            <td><input type="text" name="player_no" maxlength="4"></td>
        </tr>
        <tr>
            <th>선수이름</th>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <th>생년월일</th>
            <td><input type="text" name="birth" placeholder="예) 19970101" maxlength="8"></td>
        </tr>
        <tr>
            <th>국적코드</th>
            <td>
                <select name="nation_code">
                    <option value="">-- 국적 선택 --</option>
                    <% for (NationDTO nation : nationList) { %>
                        <option value="<%= nation.getNation_code() %>">
                            <%= nation.getNation_name() %> (<%= nation.getNation_code() %>)
                        </option>
                    <% } %>
                </select>
            </td>
        </tr>
        <tr>
            <th>기술난이도</th>
            <td>
                <select name="skill_level">
                    <option value="">-- 난이도 선택 --</option>
                    <% for (SkillDTO skill : skillList) { %>
                        <option value="<%= skill.getSkill_level() %>">
                            <%= skill.getSkill_name() %> (레벨 <%= skill.getSkill_level() %>)
                        </option>
                    <% } %>
                </select>
            </td>
        </tr>
    </table>
    
    <div class="btn-group">
        <%-- (버튼 부분은 이전과 동일) --%>
        <button type="button" class="btn-submit" onclick="checkForm()">선수등록</button>
        <button type="button" class="btn-reset" onclick="resetForm()">다시쓰기</button>
    </div>
</form>

<jsp:include page="/header/footer.jsp" />