<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-ajax.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style type="text/css">
#dialog-delete-form p {
	padding: 10px;
	font-weight: bold;
	font-size:1.0em;
}

#dialog-delete-form input[type="password"] {
	padding: 5px;	
	outline: none;
	width: 180px;
	border:1px solid black;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
//jquery plug-in
(function($){
	$.fn.hello = function(){
		console.log($(this).attr("id") + "---> hello");	
	}
})(jQuery);

var deleteNo = 0;
var page = 0;
var isEnd = false;

var messageBox = function(title, message){
	$("#dialog-message").attr("title",title);
	$("#dialog-message p").text(message);
	$("#dialog-message").dialog({
		modal : true,
		buttons:{
			"확인": function(){
				$(this).dialog("close");
			}
		}
	});
}

var render = function(vo, mode){
	
	// 현업에 가면 이렇게 안한다.
	var htmls = 
		"<li data-no='"+vo.no+"'>" +
		"<strong>"+vo.name+"</strong>"+
		"<p>"+vo.message+"</p>"+
		"<strong></strong>"+
		"<a href='' data-no='"+vo.no+"'>삭제</a>"+ 
		"</li>";
		
	if(mode == true)
	{
		$("#list-guestbook").prepend(htmls);
	} else{
		$("#list-guestbook").append(htmls);
	}
	
}

var fetchList = function()
{
	if(isEnd == true) {
		return;
	}
	
	++page,
	$.ajax({
		url: "${pageContext.request.contextPath }/api/guestbook/ajax?p=" + page,
		type: "get",
		dataType: "json",
		data:"",
		success: function(response){
			if(response.result == "fail"){
				console.warn(repsonse.data);
				return;
			}
			
			console.log(response.data);
			// 페이지 끝 검사
			if(response.data.length < 5){
				isEnd = true;
				$("#btn-next").prop("disalbed", true);
			} 
			
			// rendering
			$.each(response.data, function(index, vo){
				render(vo, false);
			}); 
		},
		error: function(xhr, status, e){
			console.error(status + ":"+ e);
		}
	});
}

$(function(){
	var dialogDelete = $("#dialog-delete-form").dialog({
		autoOpen: false,
		modal: true,
		buttons: {
			// 삭제시 작업
			"삭제": function(){
				$.ajax({
					url: "${pageContext.request.contextPath }/api/guestbook/ajax/delete?no="+deleteNo,
					type: "get",
					dataType: "json",
					data: "password="+$("#password-delete").val(),
					success: function(response){
						
						if(response.data == true){
							messageBox("삭제 완료","삭제가 완료되었습니다.");
							$("#list-guestbook li[data-no="+deleteNo+"]").remove();
							$("#password-delete").val("");
							
							dialogDelete.dialog("close");
						} else{
							$('p[class="validateTips error"]').css("display","");
							$("#password-delete").val("");
						}
						
					},
					error: function(xhr, status, e){
						console.error(status + ":"+ e);
					}
				});
			},
			"취소": function(){
				dialogDelete.dialog("close");
			}
		},
		close: function(){
			console.log("취소버튼시 처리");
			$('p[class="validateTips error"]').css("display","none");
			$("#password-delete").val("");
		}

	});
	
	// live event
	$(document).on("click","#list-guestbook li a", function(){
		event.preventDefault();
		console.log("clicked:"+$(this).data("no"));
		deleteNo = $(this).data("no");
		dialogDelete.dialog("open");
	});
	
	
	// 메시지 등록 폼 submit 동작
	$("#add-form").submit(function(){
		// submit의 기본동작(post) 막아야 한다.
		event.preventDefault();
		
		// validate form data
		var name = $("#input-name").val();
		if(name == "")
		{
			messageBox("글남기기","이름은 필수 입력 항목입니다.");
			$("#input-name").focus();
			return;	
		}
		
		$.ajax({
			url: "${pageContext.request.contextPath }/api/guestbook/ajax/insert",
			type: "get",
			dataType: "json",
			data: encodeURI("name="+$("#input-name").val()+"&message="+$("#tx-content").val()+"&password="+$("#input-password").val()),
			success: function(response){
				
				messageBox("게시글 등록","게시글이 등록되었습니다.");
								
				render(response.data, true);
				$("#input-name").val("");
				$("#input-password").val("");
				$("#tx-content").val("");				
			},
			error: function(xhr, status, e){
				console.error(status + ":"+ e);
			}
		});
	});
	
	// 스크롤 이벤트
	$(window).scroll(function(){
		var $window = $(this);
		var scrollTop = $window.scrollTop();
		var windowHeight = $window.height();
		var documentHeight = $(document).height();
		
		//console.log(scrollTop + ":" + windowHeight + ":" + documentHeight);
		if((scrollTop + windowHeight + 10) > documentHeight)
		{
			console.log("fetch ajax");
		}
	});
	
	
	
	$("#btn-next").click(function(){
		$(this).hello();
		fetchList();
	});
		
	// 최초 리스트가져오기
	fetchList();
});

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름">
					<input type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook"></ul>
				<button id="btn-next">다음</button>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none; color:red">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<div id="dialog-message" title="" style="display:none">
  				<p style="padding:30px 0"></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>