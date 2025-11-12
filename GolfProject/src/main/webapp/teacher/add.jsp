<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- ContextPath를 변수에 저장 (JSTL을 사용하지 않을 경우) --%>
<% request.setAttribute("root", request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>강사 정보 등록</title>
    <%-- 공통 CSS 링크 --%>
    <link rel="stylesheet" href="${root}/css/style.css">
    
    <%-- add.jsp 전용 CSS (폼 스타일) --%>
    <style>
        section {
            padding-top: 120px; /* 헤더 높이만큼 확보 */
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
            color: #333;
        }
        
        .form-group {
            margin-bottom: 20px;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            font-size: 1.1em;
        }
        
        .form-group input[type="text"],
        .form-group input[type="number"] {
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
        
        .form-buttons button[type="submit"] {
            background-color: #007bff;
            color: white;
        }
        
        .form-buttons button[type="reset"] {
            background-color: #6c757d;
            color: white;
        }
    </style>
</head>
<body>

    <%-- 상단 헤더(메뉴) 포함 --%>
    <%@ include file="../layout/header.jsp" %>

    <section>
        <div class="form-container">
            <h2>강사 정보 등록</h2>
            
            <%-- 서블릿 (TeacherAddServlet)으로 폼 데이터 전송 --%>
            <form name="frm" action="${root}/teacher_add.do" method="post" onsubmit="return validateForm()">
                
                <%-- PDF 3페이지 '강사정보등록' 화면 참조 [cite: 38] --%>
                <div class="form-group">
                    <label for="teacher_code">강사코드</label>
                    <input type="text" id="teacher_code" name="teacher_code" placeholder="예: 100">
                </div>
                
                <div class="form-group">
                    <label for="teacher_name">강사명</label>
                    <input type="text" id="teacher_name" name="teacher_name" placeholder="예: 김초급">
                </div>
                
                <div class="form-group">
                    <label for="class_name">강의명</label>
                    <input type="text" id="class_name" name="class_name" placeholder="예: 초급반">
                </div>
                
                <div class="form-group">
                    <label for="class_price">수강료</label>
                    <input type="number" id="class_price" name="class_price" placeholder="예: 100000">
                </div>
                
                <div class="form-group">
                    <label for="teacher_regist_date">강사자격취득일</label>
                    <input type="text" id="teacher_regist_date" name="teacher_regist_date" placeholder="예: 20220101 (8자리)">
                </div>
                
                <div class="form-buttons">
                    <button type="submit">강사등록</button>
                    <button type="reset">다시쓰기</button>
                </div>
            </form>
        </div>
    </section>

    <%-- 하단 푸터 포함 --%>
    <%@ include file="../layout/footer.jsp" %>
    
    <script>
        // PDF 3페이지 유효성 검사 요구사항 
        function validateForm() {
            const form = document.frm;
            
            if (form.teacher_code.value.trim() === "") {
                alert("강사코드를 입력해주세요.");
                form.teacher_code.focus();
                return false;
            }
            if (form.teacher_name.value.trim() === "") {
                alert("강사명을 입력해주세요.");
                form.teacher_name.focus();
                return false;
            }
            if (form.class_name.value.trim() === "") {
                alert("강의명을 입력해주세요.");
                form.class_name.focus();
                return false;
            }
            if (form.class_price.value.trim() === "") {
                alert("수강료를 입력해주세요.");
                form.class_price.focus();
                return false;
            }
            if (form.teacher_regist_date.value.trim() === "") {
                alert("강사자격취득일을 입력해주세요.");
                form.teacher_regist_date.focus();
                return false;
            }
            
            // 모든 검사 통과
            return true; 
        }
    </script>
</body>
</html>