<%-- /index.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- 
    공통 헤더 포함 (경로 수정됨) 
    webapp 폴더 바로 아래의 'header' 폴더를 의미하도록 맨 앞에 / 를 추가합니다.
--%>
<jsp:include page="/header/header.jsp" />

<%-- index.jsp 고유의 메인 컨텐츠 --%>
<div style="text-align: center;">
    <h2>과정평가형 자격 CBQ</h2>
    <p>국가직무능력표준(NCS:National Competency Standards)으로 설계된 교육 훈련과정을 체계적으로 이수하고 내외부 평가를 거쳐 취득하는 국가기술자격입니다.</p>
    <p>산업현장 중심의 교육평가로 더 커지는 능력!</p>
    <p>앓고 있는 것에 할 수 있는 것을 더하는</p>
    <p>과정평가형 자격은 현장 중심형 인재육성을 지원 합니다.</p>
</div>

<%-- 
    공통 푸터 포함 (경로 수정됨) 
--%>
<jsp:include page="/header/footer.jsp" />