<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setAttribute("root", request.getContextPath()); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>강사 정보 수정</title>
    <link rel="stylesheet" href="${root}/css/style.css">
    
    <%-- add.jsp와 동일한 폼 스타일 사용 --%>
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
        .form-group input[type="number"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1em;
        }
        
        /* [중요] readonly 필드 스타일 */
        .form-group input[readonly] {
            background-color: #eee; /* 회색 배경 */
            cursor: not-allowed; /* 마우스 커서 변경 */
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
        
        .form-buttons button[type="button"] { /* '목록으로' 버튼 스타일 */
            background-color: #6c757d;
            color: white;
        }
    </style>
</head>
<body>

    <%@ include file="../layout/header.jsp" %>

    <section>
        <div class="form-container">
            <h2>강사 정보 수정</h2>
            
            <%-- submit 시 TeacherUpdateServlet으로 전송 --%>
            <form name="frm" action="${root}/teacher_update.do" method="post" onsubmit="return validateForm()">
                
                <div class="form-group">
                    <label for="teacher_code">강사코드</label>
                    <%-- 
                      (요구사항 4-②) 강사코드는 변경 불가 [cite: 46]
                      value="${teacher.teacher_code}" : 서블릿이 넘겨준 값
                      readonly : 수정 불가 속성
                    --%>
                    <input type="text" id="teacher_code" name="teacher_code" 
                           value="${teacher.teacher_code}" readonly>
                </div>
                
                <div class="form-group">
                    <label for="teacher_name">강사명</label>
                    <input type="text" id="teacher_name" name="teacher_name" 
                           value="${teacher.teacher_name}">
                </div>
                
                <div class="form-group">
                    <label for="class_name">강의명</label>
                    <input type="text" id="class_name" name="class_name" 
                           value="${teacher.class_name}">
                </div>
                
                <div class="form-group">
                    <label for="class_price">수강료</label>
                    <input type="number" id="class_price" name="class_price" 
                           value="${teacher.class_price}">
                </div>
                
                <div class="form-group">
                    <label for="teacher_regist_date">강사자격취득일</label>
                    <input type="text" id="teacher_regist_date" name="teacher_regist_date" 
                           value="${teacher.teacher_regist_date}">
                </div>
                
                <div class="form-buttons">
                    <button type="submit">정보수정</button>
                    <%-- '다시쓰기' 대신 '목록으로' 버튼 제공 --%>
                    <button type="button" onclick="location.href='${root}/teacher/list.do'">목록으로</button>
                </div>
            </form>
        </div>
    </section>

    <%@ include file="../layout/footer.jsp" %>
    
    <script>
        // add.jsp와 동일한 유효성 검사 (강사코드 제외)
        function validateForm() {
            const form = document.frm;
            
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
            return true; 
        }
    </script>
</body>
</html>