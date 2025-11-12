<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 정보 수정</title>
    <link rel="stylesheet" href="${root}/css/style.css">
    
    <%-- 회원 등록(add.jsp)과 동일한 폼 스타일 --%>
    <style>
        section { padding-top: 120px; display: flex; justify-content: center; }
        .form-container { width: 500px; border: 1px solid #ccc; border-radius: 8px; padding: 30px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        .form-container h2 { text-align: center; margin-bottom: 25px; }
        .form-group { margin-bottom: 20px; }
        .form-group label { display: block; margin-bottom: 8px; font-weight: bold; }
        .form-group input[type="text"], .form-group select {
            width: 100%; padding: 12px; border: 1px solid #ddd;
            border-radius: 5px; font-size: 1em;
        }
        
        /* [중요] readonly 필드 스타일 */
        .form-group input[readonly] {
            background-color: #eee;
            cursor: not-allowed;
        }
        
        .form-buttons { display: flex; justify-content: space-between; margin-top: 30px; }
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
            <h2>회원 정보 수정</h2>
            
            <%-- submit 시 MemberUpdateServlet으로 전송 --%>
            <form name="frm" action="${root}/member_update.do" method="post" onsubmit="return validateForm()">
                
                <div class="form-group">
                    <label for="c_no">회원번호</label>
                    <%-- (요구사항 7-②) 회원번호 변경 불가 [cite: 76] --%>
                    <input type="text" id="c_no" name="c_no" 
                           value="${member.c_no}" readonly>
                </div>
                
                <div class="form-group">
                    <label for="c_name">회원명</label>
                    <%-- (요구사항 7-②) 회원명 변경 불가 [cite: 76] --%>
                    <input type="text" id="c_name" name="c_name" 
                           value="${member.c_name}" readonly>
                </div>
                
                <div class="form-group">
                    <label for="phone">전화번호</label>
                    <input type="text" id="phone" name="phone" 
                           value="${member.phone}">
                </div>
                
                <div class="form-group">
                    <label for="address">주소</label>
                    <input type="text" id="address" name="address" 
                           value="${member.address}">
                </div>
                
                <div class="form-group">
                    <label for="regist_date">가입일자</label>
                    <input type="text" id="regist_date" name="regist_date" 
                           value="${member.regist_date}">
                </div>
                
                <div class="form-group">
                    <label for="c_type">회원등급</label>
                    <select id="c_type" name="c_type">
                        <%-- JSTL을 사용하여 DTO의 값과 일치하는 옵션을 'selected'로 --%>
                        <option value="일반" ${member.c_type == '일반' ? 'selected' : ''}>일반</option>
                        <option value="VIP" ${member.c_type == 'VIP' ? 'selected' : ''}>VIP</option>
                    </select>
                </div>
                
                <div class="form-buttons">
                    <button type="submit">정보수정</button>
                    <button type="button" onclick="location.href='${root}/member/list.do'">목록으로</button>
                </div>
            </form>
        </div>
    </section>

    <%-- 하단 푸터 (경로 ../) --%>
    <%@ include file="../layout/footer.jsp" %>
    
    <script>
        // 유효성 검사 (수정 가능한 항목만)
        function validateForm() {
            const form = document.frm;
            
            if (form.phone.value.trim() === "") {
                alert("전화번호를 입력해주세요.");
                form.phone.focus();
                return false;
            }
            if (form.address.value.trim() === "") {
                alert("주소를 입력해주세요.");
                form.address.focus();
                return false;
            }
             if (form.regist_date.value.trim() === "") {
                alert("가입일자를 입력해주세요.");
                form.regist_date.focus();
                return false;
            }
            return true; 
        }
    </script>
</body>
</html>