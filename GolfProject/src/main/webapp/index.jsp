<%-- /index.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>골프연습장 회원관리</title>
    <%-- 
      CSS 파일 경로를 설정합니다. 
      ContextPath를 기준으로 절대 경로를 사용하는 것이 좋습니다.
    --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <%-- 상단 헤더(메뉴) 포함 --%>
    <%@ include file="layout/header.jsp" %>

    <%-- 메인 컨텐츠 영역 --%>
    <section>
 
        <div class="index-content">
            <h2>과정평가형 자격 CBQ</h2>
            <p>
                국가직무능력표준(NCS: National Competency Standards)으로 설계된 교육 훈련과정을 체계적으로 이수하고<br>
                내외부 평가를 거쳐 취득하는 국가기술자격입니다.
            </p>
            <br><br>
            <h3>산업현장 중심의 교육평가로 더 커지는 능력</h3>
            <p>알고 있는 것에 할 수 있는 것을 더하는</p>
            <p>과정평가형 자격은 현장 중심형 인재육성을 지원 합니다.</p>
        </div>
    </section>

    <%-- 하단 푸터 포함 --%>
    <%@ include file="layout/footer.jsp" %>

</body>
</html>