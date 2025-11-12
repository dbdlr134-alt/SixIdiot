<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL 사용을 위한 taglib 선언 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("root", request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>수강 신청</title>
    <link rel="stylesheet" href="${root}/css/style.css">
    
    <%-- 폼 스타일 (다른 add.jsp와 유사) --%>
    <style>
        section { padding-top: 120px; display: flex; justify-content: center; }
        .form-container { width: 600px; border: 1px solid #ccc; border-radius: 8px; padding: 30px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        .form-container h2 { text-align: center; margin-bottom: 25px; }
        
        /* 폼을 2단으로 나누기 (Grid) */
        .form-grid {
            display: grid;
            grid-template-columns: 1fr 1fr; /* 2열 */
            gap: 20px; /* 열/행 사이 간격 */
        }
        
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 8px; font-weight: bold; }
        .form-group input[type="text"],
        .form-group input[type="number"],
        .form-group select {
            width: 100%; padding: 12px; border: 1px solid #ddd;
            border-radius: 5px; font-size: 1em;
        }
        
        /* [중요] readonly 필드 스타일 */
        .form-group input[readonly] {
            background-color: #eee;
            cursor: not-allowed;
        }
        
        /* '강의장소'는 2열 전체 사용 */
        .full-width {
            grid-column: 1 / -1; /* 1번 열에서 마지막 열까지 */
        }
        
        .form-buttons { display: flex; justify-content: space-between; margin-top: 30px; grid-column: 1 / -1; }
        .form-buttons button { width: 48%; padding: 12px; font-size: 1.1em; font-weight: bold; border: none; border-radius: 5px; cursor: pointer; }
        .form-buttons button[type="submit"] { background-color: #007bff; color: white; }
        .form-buttons button[type="button"] { background-color: #6c757d; color: white; }
    </style>
</head>
<body>

    <%-- 상단 헤더 (경로 ../) --%>
    <%@ include file="../layout/header.jsp" %>

    <section>
        <div class="form-container">
            <h2>수강 신청</h2>
            
            <%-- submit 시 ClassAddServlet으로 전송 --%>
            <form name="frm" action="${root}/class_add.do" method="post" onsubmit="return validateForm()">
                
                <%-- PDF 4페이지 '수강신청등록' 화면 참조 [cite: 78] --%>
                <div class="form-grid">
                    
                    <div class="form-group">
                        <label for="regist_month">수강월</label>
                        <input type="text" id="regist_month" name="regist_month" placeholder="예: 202203 (6자리)">
                    </div>

                    <div class="form-group">
                        <label for="member_select">회원명</label>
                        <%-- (Req 8-①, 8-②) 회원명 선택 시 JS(updateMemberInfo) 호출 --%>
                        <select id="member_select" name="member_select" onchange="updateMemberInfo(this)">
                            <option value="">회원명 선택</option>
                            <%-- 
                              서블릿이 보낸 memberList를 JSTL로 반복
                              (Req 8-④ VIP 할인)을 위해 회원의 등급(c_type)을 data-type 속성에 저장 
                            --%>
                            <c:forEach var="m" items="${memberList}">
                                <option value="${m.c_no}" data-type="${m.c_type}">
                                    [${m.c_no}]${m.c_name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="c_no">회원번호</label>
                        <%-- (Req 8-②) 자동 입력되도록 readonly --%>
                        <input type="text" id="c_no" name="c_no" readonly>
                    </div>

                    <div class="form-group">
                        <label for="class_select">강의선택</label>
                        <%-- (Req 8-③, 8-④) 강의 선택 시 JS(updateTuition) 호출 --%>
                        <select id="class_select" name="teacher_code" onchange="updateTuition()">
                            <option value="">강의 선택</option>
                            <%--
                              서블릿이 보낸 teacherList를 JSTL로 반복
                              (Req 8-④ 수강료 자동입력)을 위해 수강료(class_price)를 data-price 속성에 저장
                            --%>
                            <c:forEach var="t" items="${teacherList}">
                                <option value="${t.teacher_code}" data-price="${t.class_price}">
                                    [${t.teacher_code}]${t.class_name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="form-group full-width">
                        <label for="class_area">강의장소</label>
                        <input type="text" id="class_area" name="class_area" placeholder="예: 서울본원">
                    </div>

                    <div class="form-group full-width">
                        <label for="tuition">수강료</label>
                        <%-- (Req 8-④) 자동 입력되도록 readonly --%>
                        <input type="number" id="tuition" name="tuition" readonly>
                    </div>

                    <div class="form-buttons">
                        <button type="submit">수강신청</button>
                        <%-- (Req 8-⑦) 다시쓰기 버튼 --%>
                        <button type="button" onclick="resetForm()">다시쓰기</button>
                    </div>
                </div>
            </form>
        </div>
    </section>

    <%-- 하단 푸터 (경로 ../) --%>
    <%@ include file="../layout/footer.jsp" %>
    
    <script>
        const form = document.frm;
        const memberSelect = form.member_select;
        const classSelect = form.class_select;

        // (Req 8-②) 회원명 선택 시 회원번호 자동 입력
        function updateMemberInfo(select) {
            form.c_no.value = select.value; // <option>의 value (c_no)를 회원번호 input에
            
            // 회원이 변경되었으므로 수강료도 다시 계산
            updateTuition();
        }

        // (Req 8-④) 강의 또는 회원 선택 시 수강료 자동 계산 (VIP 할인 포함)
        function updateTuition() {
            // 1. 선택된 '강의'의 원 수강료(data-price) 가져오기
            const selectedClassOption = classSelect.options[classSelect.selectedIndex];
            const basePrice = parseInt(selectedClassOption.dataset.price || 0);

            // 2. 선택된 '회원'의 등급(data-type) 가져오기
            const selectedMemberOption = memberSelect.options[memberSelect.selectedIndex];
            const memberType = selectedMemberOption.dataset.type || '일반';

            let finalPrice = 0;

            if (basePrice > 0) {
                // (Req 8-④ VIP 할인) 회원등급이 VIP인 경우 50% 할인
                if (memberType === 'VIP') {
                    finalPrice = basePrice * 0.5;
                } else {
                    finalPrice = basePrice;
                }
            }
            
            // 3. 수강료 input에 계산된 값 입력
            form.tuition.value = finalPrice;
        }

        // (Req 8-⑤) 유효성 검사
        function validateForm() {
            if (form.regist_month.value.trim() === "") {
                alert("수강월이 입력되지 않았습니다!");
                form.regist_month.focus();
                return false;
            }
            if (form.member_select.value === "") { // 회원명 <select>
                alert("회원명이 선택되지 않았습니다!");
                form.member_select.focus();
                return false;
            }
            // (Req 8-②)에 따라 회원번호는 회원명 선택 시 자동 입력되므로 c_no 필드로 검사
            if (form.c_no.value.trim() === "") {
                alert("회원번호가 입력되지 않았습니다!");
                form.member_select.focus();
                return false;
            }
            if (form.class_area.value.trim() === "") {
                alert("강의장소가 입력되지 않았습니다!");
                form.class_area.focus();
                return false;
            }
            if (form.teacher_code.value === "") { // 강의선택 <select> (name="teacher_code")
                alert("강의선택이 선택되지 않았습니다!");
                form.class_select.focus();
                return false;
            }
            // 수강료는 자동계산이므로 0이 아닌지 (강의가 선택되었는지) 확인
            if (form.tuition.value === "" || form.tuition.value === "0") {
                alert("수강료가 입력되지 않았습니다!");
                form.class_select.focus();
                return false;
            }
            
            return true; // 모든 검사 통과
        }

        // (Req 8-⑦) 다시쓰기 (reset)
        function resetForm() {
            // "정보를 지우고 처음부터 다시 입력 합니다" 확인창 [cite: 112]
            if (confirm("정보를 지우고 처음부터 다시 입력 합니다")) {
                form.reset(); // 폼 모든 내용 초기화
                form.regist_month.focus();
            }
        }
    </script>
</body>
</html>