<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 가입일자 자동 입력을 위해 java.util.Date, java.text.SimpleDateFormat 임포트 --%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<%
    // (요구사항 5-①) 가입일자는 현재년월일이 자동 입력 [cite: 59]
    String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
    
    // ContextPath 설정
    request.setAttribute("root", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 등록</title>
    <link rel="stylesheet" href="${root}/css/style.css">
    
    <%-- 강사 등록 폼과 동일한 CSS 사용 --%>
    <style>
        section {
            padding-top: 120px;
            display: flex;
            justify-content: center;
        }
        
        .form-container {
            width: 500px;
            border: 1px solid #ccc;
            border-radius: 8px;
            padding: 30px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        
        .form-container h2 {
            text-align: center;
            margin-bottom: 25px;
            font-size: 1.8em;
        }
        
        .form-group { margin-bottom: 20px; }
        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            font-size: 1.1em;
        }
        
        .form-group input[type="text"],
        .form-group select { /* select 태그 스타일 추가 */
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1em;
        }
        
        .form-buttons {
            display: flex;
            justify-content: space-between;
            margin-top: 30px;
        }
        
        .form-buttons button {
            width: 48%;
            padding: 12px;
            font-size: 1.1em;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        
        .form-buttons button[type="submit"] { background-color: #007bff; color: white; }
        .form-buttons button[type="reset"] { background-color: #6c757d; color: white; }
    </style>
</head>
<body>

    <%-- 상단 헤더(메뉴) 포함 (경로 주의: ../) --%>
    <%@ include file="../layout/header.jsp" %>

    <section>
        <div class="form-container">
            <h2>회원 등록</h2>
            
            <%-- submit 시 MemberAddServlet으로 전송 --%>
            <form name="frm" action="${root}/member_add.do" method="post" onsubmit="return validateForm()">
                
                <%-- PDF 3페이지 '회원등록' 화면 참조 [cite: 47-58] --%>
                <div class="form-group">
                    <label for="c_no">회원번호</label>
                    <input type="text" id="c_no" name="c_no" placeholder="예: 10001">
                </div>
                
                <div class="form-group">
                    <label for="c_name">회원명</label>
                    <input type="text" id="c_name" name="c_name" placeholder="예: 이혜정">
                </div>
                
                <div class="form-group">
                    <label for="phone">전화번호</label>
                    <input type="text" id="phone" name="phone" placeholder="010-XXXX-XXXX ('-' 제외 11자리)">
                </div>
                
                <div class="form-group">
                    <label for="address">주소</label>
                    <input type="text" id="address" name="address" placeholder="예: 서울시 강남구">
                </div>
                
                <div class="form-group">
                    <label for="regist_date">가입일자</label>
                    <%-- (요구사항 5-①) 현재일 자동입력, 변경 가능 [cite: 59] --%>
                    <input type="text" id="regist_date" name="regist_date" value="<%= today %>">
                </div>
                
                <div class="form-group">
                    <label for="c_type">회원등급</label>
                    <%-- (요구사항 5-②) 일반, VIP만 가능하도록 선택상자 이용 [cite: 60] --%>
                    <select id="c_type" name="c_type">
                        <option value="일반">일반</option>
                        <option value="VIP">VIP</option>
                    </select>
                </div>
                
                <div class="form-buttons">
                    <button type="submit">회원등록</button>
                    <button type="reset">다시쓰기</button>
                </div>
            </form>
        </div>
    </section>

    <%-- 하단 푸터 포함 (경로 주의: ../) --%>
    <%@ include file="../layout/footer.jsp" %>
    
    <script>
        // PDF 3페이지 유효성 검사 요구사항 [cite: 61]
        function validateForm() {
            const form = document.frm;
            
            // (요구사항 5-③) 기타 모든 항목 (회원번호, 회원명, 전화번호, 주소) [cite: 61]
            if (form.c_no.value.trim() === "") {
                alert("회원번호를 입력해주세요.");
                form.c_no.focus();
                return false;
            }
            if (form.c_name.value.trim() === "") {
                alert("회원명을 입력해주세요.");
                form.c_name.focus();
                return false;
            }
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
            
            // 가입일자는 자동입력 [cite: 59], 회원등급은 기본선택 [cite: 60]이므로 제외
            
            return true; 
        }
    </script>
</body>
</html>